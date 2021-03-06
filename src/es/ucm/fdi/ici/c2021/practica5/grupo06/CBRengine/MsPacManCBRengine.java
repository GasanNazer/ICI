package es.ucm.fdi.ici.c2021.practica5.grupo06.CBRengine;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.util.SystemOutLogger;

import es.ucm.fdi.gaia.jcolibri.cbraplications.StandardCBRApplication;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCaseBase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRQuery;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;
import es.ucm.fdi.gaia.jcolibri.connector.PlainTextConnector;
import es.ucm.fdi.gaia.jcolibri.exception.ExecutionException;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.RetrievalResult;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.selection.SelectCases;
import es.ucm.fdi.gaia.jcolibri.util.FileIO;
import es.ucm.fdi.ici.c2021.practica5.grupo06.Action;
import es.ucm.fdi.ici.c2021.practica5.grupo06.MsPacManActionSelector;
public class MsPacManCBRengine implements StandardCBRApplication {

	private String casebaseFile;
	private Action action;
	private MsPacManActionSelector actionSelector;
	private MsPacManStorageManager storageManager;

	CustomPlainTextConnector connector;
	CBRCaseBase caseBase;
	NNConfig simConfig;
	
	
	
	//final static String CONNECTOR_FILE_PATH = "es\\ucm\\fdi\\ici\\c2021\\practica5\\grupo06\\CBRengine\\plaintextconfig.xml"; //Cuidado!! poner el grupo aquí
	final static String CONNECTOR_FILE_PATH = "es/ucm/fdi/ici/c2021/practica5/grupo06/CBRengine/plaintextconfig.xml"; //Cuidado!! poner el grupo aquí
	
	/**
	 * Simple extension to allow custom case base files. It also creates a new empty file if it does not exist.
	 */
	public class CustomPlainTextConnector extends PlainTextConnector {
		public void setCaseBaseFile(String casebaseFile) {
			super.PROP_FILEPATH = casebaseFile;
			try {
		         File file = new File(casebaseFile);
		         System.out.println(file.getAbsolutePath());
		         if(!file.exists())
		        	 file.createNewFile();
		      } catch(Exception e) {
		         e.printStackTrace();
		      }
		}
	}
	
	
	public MsPacManCBRengine(MsPacManActionSelector actionSelector, MsPacManStorageManager storageManager)
	{
		this.actionSelector = actionSelector;
		this.storageManager = storageManager;
	}
	
	public void setCaseBaseFile(String casebaseFile) {
		this.casebaseFile = casebaseFile;
	}
	
	@Override
	public void configure() throws ExecutionException {
		connector = new CustomPlainTextConnector();
		caseBase = new CachedLinearCaseBase();
		
		connector.initFromXMLfile(FileIO.findFile(CONNECTOR_FILE_PATH));
		connector.setCaseBaseFile(this.casebaseFile);
		this.storageManager.setCaseBase(caseBase);
		
		simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());
		simConfig.addMapping(new Attribute("score",MsPacManDescription.class), new Interval(15000));
		simConfig.addMapping(new Attribute("time",MsPacManDescription.class), new Interval(4000));
		simConfig.addMapping(new Attribute("timeEdibleLeft",MsPacManDescription.class), new Interval(4000));
		simConfig.addMapping(new Attribute("nearestPPill",MsPacManDescription.class), new Interval(650));
		simConfig.addMapping(new Attribute("nearestPill",MsPacManDescription.class), new Interval(650));
		simConfig.addMapping(new Attribute("nearestNonEdibleGhostDist",MsPacManDescription.class), new Interval(650));
		simConfig.addMapping(new Attribute("nearestEdibleGhostDist",MsPacManDescription.class), new Interval(650));
		simConfig.addMapping(new Attribute("leftPills",MsPacManDescription.class), new Interval(230));
		simConfig.addMapping(new Attribute("leftPPills",MsPacManDescription.class), new Interval(4));
		simConfig.addMapping(new Attribute("edibleGhost",MsPacManDescription.class), new Equal());
	}

	@Override
	public CBRCaseBase preCycle() throws ExecutionException {
		caseBase.init(connector);
		return caseBase;
	}

	@Override
	public void cycle(CBRQuery query) throws ExecutionException {
		if(caseBase.getCases().isEmpty()) {
			this.action = actionSelector.findAction((MsPacManDescription) query.getDescription());
		}else {
			//Compute NN
			Collection<RetrievalResult> eval = customNN(caseBase.getCases(), query);
			
			//TODO implementation of majority voting
			Collection<RetrievalResult> results = SelectCases.selectTopKRR(eval, 5);
			
			double similarity = 0;
			double bestScore = 0;
			CBRCase mostSimilarCase = results.iterator().next().get_case();
			
			for(RetrievalResult first : results) {
				MsPacManResult result2 = (MsPacManResult)first.get_case().getResult();
				if(result2.getScore() > bestScore && first.getEval() >= 0.9){
					bestScore = result2.getScore();
					similarity = first.getEval();
					mostSimilarCase = first.get_case();
				}
			}
	
			MsPacManResult result = (MsPacManResult) mostSimilarCase.getResult();
			MsPacManSolution solution = (MsPacManSolution) mostSimilarCase.getSolution();
			
			
			//Now compute a solution for the query
			this.action = actionSelector.getAction(solution.getAction());
			if(similarity<0.9) //Sorry not enough similarity, ask actionSelector for an action
				this.action = actionSelector.findAction((MsPacManDescription) query.getDescription());
			
			else if(result.getScore()<0) //This was a bad case, ask actionSelector for another one.
				this.action = actionSelector.findAnotherAction(solution.getAction());
		}
		CBRCase newCase = createNewCase(query);
		this.storageManager.storeCase(newCase);
		
	}

	/**
	 * Creates a new case using the query as description, 
	 * storing the action into the solution and 
	 * setting the proper id number
	 */
	private CBRCase createNewCase(CBRQuery query) {
		CBRCase newCase = new CBRCase();
		MsPacManDescription newDescription = (MsPacManDescription) query.getDescription();
		MsPacManResult newResult = new MsPacManResult();
		MsPacManSolution newSolution = new MsPacManSolution();
		int newId = this.caseBase.getCases().size();
		newId+= storageManager.getPendingCases();
		newDescription.setId(newId);
		newResult.setId(newId);
		newSolution.setId(newId);
		newSolution.setAction(this.action.getActionId());
		newCase.setDescription(newDescription);
		newCase.setResult(newResult);
		newCase.setSolution(newSolution);
		return newCase;
	}
	
	public Action getSolution() {
		return this.action;
	}

	@Override
	public void postCycle() throws ExecutionException {
		this.storageManager.close();
		this.caseBase.close();
	}
	
	private Collection<RetrievalResult> customNN(Collection<CBRCase> cases, CBRQuery query) {

		// Parallel stream

		List<RetrievalResult> res = cases.parallelStream()

				.map(c -> new RetrievalResult(c, computeSimilarity(query.getDescription(), c.getDescription())))

				.collect(Collectors.toList());

		// Sort the result

		res.sort(RetrievalResult::compareTo);

		return res;

	}
	
	private Double computeSimilarity(CaseComponent description, CaseComponent description2) {

		MsPacManDescription _query = (MsPacManDescription) description;

		MsPacManDescription _case = (MsPacManDescription) description2;

		double simil = 0;
		double aux = Math.abs(_query.getScore() - _case.getScore());
		
		double nums = 11.0;
		simil += aux / 5000;
		
		aux = Math.abs(_query.getTime() - _case.getTime());
		simil += aux / 4000;
		
		aux = Math.abs(_query.getTimeEdibleLeft() - _case.getTimeEdibleLeft());
		simil += aux / 4000;
		
		aux = 3 * Math.abs(_query.getNearestPPill() - _case.getNearestPPill());
		if(_query.getNearestPPill() != Integer.MAX_VALUE && _case.getNearestPPill() != Integer.MAX_VALUE) {
			simil += aux / 650;
			nums += 3.0;
		}
		
		aux = 2 * Math.abs(_query.getNearestPill() - _case.getNearestPill());
		if(_query.getNearestPill() != Integer.MAX_VALUE && _case.getNearestPill() != Integer.MAX_VALUE) {
			simil += aux / 650;
			nums += 2.0;
		}
		
		aux = 3 * Math.abs(_query.getNearestNonEdibleGhostDist() - _case.getNearestNonEdibleGhostDist());
		if(_query.getNearestNonEdibleGhostDist() != Integer.MAX_VALUE && _case.getNearestNonEdibleGhostDist() != Integer.MAX_VALUE) {
			simil += aux / 650;
			nums += 3.0;
		}
		
		aux = 2 * Math.abs(_query.getNearestEdibleGhostDist() - _case.getNearestEdibleGhostDist());
		if(_query.getNearestEdibleGhostDist() != Integer.MAX_VALUE && _case.getNearestEdibleGhostDist() != Integer.MAX_VALUE) {
			simil += aux / 650;
			nums += 2.0;
		}
		
		aux = 3 * Math.abs(_query.getLeftPPills() - _case.getLeftPPills());
		simil += aux / 4;
		
		aux = 3 * Math.abs(_query.getLeftPills() - _case.getLeftPills());
		simil += aux / 230;
		
		simil += 2 * (_query.getEdibleGhost().equals(_case.getEdibleGhost()) ? 1.0 : 0.0);
		
		return 1.0 - (simil / nums);

	}

}

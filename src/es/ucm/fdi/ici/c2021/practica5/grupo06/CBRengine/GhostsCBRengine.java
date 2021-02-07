package es.ucm.fdi.ici.c2021.practica5.grupo06.CBRengine;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
import es.ucm.fdi.ici.c2021.practica5.grupo06.GhostsActionSelector;

public class GhostsCBRengine implements StandardCBRApplication {

	private String casebaseFile;
	private Action action;
	private GhostsActionSelector actionSelector;
	private GhostsStorageManager storageManager;

	CustomPlainTextConnector connector;
	CBRCaseBase caseBase;
	NNConfig simConfig;

	final static String CONNECTOR_FILE_PATH = "es/ucm/fdi/ici/c2021/practica5/grupo06/CBRengine/ghostsplaintextconfig.xml";
	/**
	 * Simple extension to allow custom case base files. It also creates a new empty
	 * file if it does not exist.
	 */
	public class CustomPlainTextConnector extends PlainTextConnector {
		public void setCaseBaseFile(String casebaseFile) {
			super.PROP_FILEPATH = casebaseFile;
			try {
				File file = new File(casebaseFile);
				System.out.println(file.getAbsolutePath());
				if (!file.exists())
					file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public GhostsCBRengine(GhostsActionSelector actionSelector, GhostsStorageManager storageManager) {
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

		simConfig.addMapping(new Attribute("distanceToNearestPowerPillBlinky", GhostsDescription.class),
				new Interval(650));
		simConfig.addMapping(new Attribute("distanceToNearestPowerPillPinky", GhostsDescription.class),
				new Interval(650));
		simConfig.addMapping(new Attribute("distanceToNearestPowerPillInky", GhostsDescription.class),
				new Interval(650));
		simConfig.addMapping(new Attribute("distanceToNearestPowerPillSue", GhostsDescription.class),
				new Interval(650));

		simConfig.addMapping(new Attribute("distanceToPacmanBlinky", GhostsDescription.class), new Interval(650));
		simConfig.addMapping(new Attribute("distanceToPacmanPinky", GhostsDescription.class), new Interval(650));
		simConfig.addMapping(new Attribute("distanceToPacmanInky", GhostsDescription.class), new Interval(650));
		simConfig.addMapping(new Attribute("distanceToPacmanSue", GhostsDescription.class), new Interval(650));

		simConfig.addMapping(new Attribute("ghostEdibleBlinky", GhostsDescription.class), new Equal());
		simConfig.addMapping(new Attribute("ghostEdiblePinky", GhostsDescription.class), new Equal());
		simConfig.addMapping(new Attribute("ghostEdibleInky", GhostsDescription.class), new Equal());
		simConfig.addMapping(new Attribute("ghostEdibleSue", GhostsDescription.class), new Equal());

		simConfig.addMapping(new Attribute("distanceToNearestPowerPillPacMan", GhostsDescription.class),
				new Interval(650));

		simConfig.addMapping(new Attribute("positionOfNearestPowerPillPacMan", GhostsDescription.class),
				new Interval(650));

		simConfig.addMapping(new Attribute("nearestGhostToPacMan", GhostsDescription.class), new Interval(650));

		simConfig.addMapping(new Attribute("distanceNearestGhostToPacMan", GhostsDescription.class), new Interval(650));

		simConfig.addMapping(new Attribute("distanceToNearestGhostBlinky", GhostsDescription.class), new Interval(650));
		simConfig.addMapping(new Attribute("distanceToNearestGhostPinky", GhostsDescription.class), new Interval(650));
		simConfig.addMapping(new Attribute("distanceToNearestGhostInky", GhostsDescription.class), new Interval(650));
		simConfig.addMapping(new Attribute("distanceToNearestGhostSue", GhostsDescription.class), new Interval(650));
		
		simConfig.addMapping(new Attribute("distanceToNearestEdibleGhostBlinky", GhostsDescription.class), new Interval(650));
		simConfig.addMapping(new Attribute("distanceToNearestEdibleGhostPinky", GhostsDescription.class), new Interval(650));
		simConfig.addMapping(new Attribute("distanceToNearestEdibleGhostInky", GhostsDescription.class), new Interval(650));
		simConfig.addMapping(new Attribute("distanceToNearestEdibleGhostSue", GhostsDescription.class), new Interval(650));
		
		simConfig.addMapping(new Attribute("distanceToNearestNoEdibleGhostBlinky", GhostsDescription.class), new Interval(650));
		simConfig.addMapping(new Attribute("distanceToNearestNoEdibleGhostPinky", GhostsDescription.class), new Interval(650));
		simConfig.addMapping(new Attribute("distanceToNearestNoEdibleGhostInky", GhostsDescription.class), new Interval(650));
		simConfig.addMapping(new Attribute("distanceToNearestNoEdibleGhostSue", GhostsDescription.class), new Interval(650));
		
		simConfig.addMapping(new Attribute("edibleTimeLeftBlinky", GhostsDescription.class), new Interval(50));
		simConfig.addMapping(new Attribute("edibleTimeLeftPinky", GhostsDescription.class), new Interval(50));
		simConfig.addMapping(new Attribute("edibleTimeLeftInky", GhostsDescription.class), new Interval(50));
		simConfig.addMapping(new Attribute("edibleTimeLeftSue", GhostsDescription.class), new Interval(50));
		
		simConfig.addMapping(new Attribute("directionLastMoveBlinky", GhostsDescription.class), new Interval(5));
		simConfig.addMapping(new Attribute("directionLastMovePinky", GhostsDescription.class), new Interval(5));
		simConfig.addMapping(new Attribute("directionLastMoveInky", GhostsDescription.class), new Interval(5));
		simConfig.addMapping(new Attribute("directionLastMoveSue", GhostsDescription.class), new Interval(5));
		
		simConfig.addMapping(new Attribute("numberOfPowerPillsLeft", GhostsDescription.class), new Interval(650));
		
		simConfig.addMapping(new Attribute("score", GhostsDescription.class), new Interval(15000));
		simConfig.addMapping(new Attribute("time", GhostsDescription.class), new Interval(4000));
	}

	@Override
	public CBRCaseBase preCycle() throws ExecutionException {
		caseBase.init(connector);
		return caseBase;
	}

	@Override
	public void cycle(CBRQuery query) throws ExecutionException {
		if (caseBase.getCases().isEmpty()) {
			this.action = actionSelector.findAction((GhostsDescription)query.getDescription());
		} else {
			// Compute NN
			Collection<RetrievalResult> eval = customNN(caseBase.getCases(), query);

			double similarity = 0;
			double lowestScore = Double.MAX_VALUE;
			
			RetrievalResult res_initial = SelectCases.selectTopKRR(eval, 1).iterator().next();
			CBRCase mostSimilarCase = res_initial.get_case();
			
			for(RetrievalResult first : SelectCases.selectTopKRR(eval, 5)) {
				GhostsResult res = (GhostsResult)first.get_case().getResult();
				if(res.getScore() < lowestScore && first.getEval() >= 0.7) {
					similarity = first.getEval();
					lowestScore = res.getScore();
					mostSimilarCase = first.get_case();
				}
			}
			
			GhostsSolution solution = (GhostsSolution) mostSimilarCase.getSolution();

			// Now compute a solution for the query
			this.action = actionSelector.getAction(solution.getAction());
			
			GhostsDescription queryDescription = (GhostsDescription)query.getDescription();
			
			if (similarity < 0.7) {
				this.action = actionSelector.findAction(queryDescription);
			}
		}
		CBRCase newCase = createNewCase(query);
		this.storageManager.storeCase(newCase);

	}

	/**
	 * Creates a new case using the query as description, storing the action into
	 * the solution and setting the proper id number
	 */
	private CBRCase createNewCase(CBRQuery query) {
		CBRCase newCase = new CBRCase();
		GhostsDescription newDescription = (GhostsDescription) query.getDescription();
		GhostsResult newResult = new GhostsResult();
		GhostsSolution newSolution = new GhostsSolution();
		int newId = this.caseBase.getCases().size();
		newId += storageManager.getPendingCases();
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

		GhostsDescription _query = (GhostsDescription) description;

		GhostsDescription _case = (GhostsDescription) description2;

		double simil = 0;

		simil += Math.abs(_query.getScore() - _case.getScore()) / 15000;

		simil += Math.abs(_query.getTime() - _case.getTime()) / 4000;
		
		
		//distanceToPacman
		simil += Math.abs(_query.getDistanceToPacmanBlinky() - _case.getDistanceToPacmanBlinky()) / 650;
		simil += Math.abs(_query.getDistanceToPacmanPinky() - _case.getDistanceToPacmanPinky()) / 650;
		simil += Math.abs(_query.getDistanceToPacmanInky() - _case.getDistanceToPacmanInky()) / 650;
		simil += Math.abs(_query.getDistanceToPacmanSue() - _case.getDistanceToPacmanSue()) / 650;
		
		//edibleTimeLeft
		simil += Math.abs(_query.getEdibleTimeLeftBlinky() - _case.getEdibleTimeLeftBlinky()) / 60;
		simil += Math.abs(_query.getEdibleTimeLeftPinky() - _case.getEdibleTimeLeftPinky()) / 60;
		simil += Math.abs(_query.getEdibleTimeLeftInky() - _case.getEdibleTimeLeftInky()) / 60;
		simil += Math.abs(_query.getEdibleTimeLeftSue() - _case.getEdibleTimeLeftSue()) / 60;
		
		//ghostEdible
		simil += _query.getGhostEdibleBlinky().equals(_case.getGhostEdibleBlinky()) ? 1.0 : 0.0;
		simil += _query.getGhostEdiblePinky().equals(_case.getGhostEdiblePinky()) ? 1.0 : 0.0;
		simil += _query.getGhostEdibleInky().equals(_case.getGhostEdibleInky()) ? 1.0 : 0.0;
		simil += _query.getGhostEdibleSue().equals(_case.getGhostEdibleSue()) ? 1.0 : 0.0;
		
		// distancePacmanPP
		simil += Math.abs(_query.getDistanceToNearestPowerPillPacMan() - _case.getDistanceToNearestPowerPillPacMan()) / 650;

		return simil / 15.0;

	}

}
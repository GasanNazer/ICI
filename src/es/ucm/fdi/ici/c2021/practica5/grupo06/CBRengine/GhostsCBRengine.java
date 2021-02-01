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

	final static String CONNECTOR_FILE_PATH = "es\\ucm\\fdi\\ici\\c2021\\practica5\\grupo06\\CBRengine\\ghostsplaintextconfig.xml"; // Cuidado!!
																																	// poner
																																	// el
																																	// grupo
																																	// aquí

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
			this.action = actionSelector.findAction();
		} else {
			// Compute NN
			// Collection<RetrievalResult> eval =
			// NNScoringMethod.evaluateSimilarity(caseBase.getCases(), query,
			// simConfig);
			Collection<RetrievalResult> eval = customNN(caseBase.getCases(), query);

			// This simple implementation only uses 1NN
			// Consider using kNNs with majority voting
			RetrievalResult first = SelectCases.selectTopKRR(eval, 1).iterator().next();
			CBRCase mostSimilarCase = first.get_case();
			double similarity = first.getEval();

			GhostsResult result = (GhostsResult) mostSimilarCase.getResult();
			GhostsSolution solution = (GhostsSolution) mostSimilarCase.getSolution();

			// Now compute a solution for the query
			this.action = actionSelector.getAction(solution.getAction());

			if (similarity < 0.7) // Sorry not enough similarity, ask actionSelector for an action
				this.action = actionSelector.findAction();

			else if (result.getScore() < 0) // This was a bad case, ask actionSelector for another one.
				this.action = actionSelector.findAnotherAction(solution.getAction());
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

		simil += Math.abs(_query.getScore() - _case.getScore()) / 150000;

		simil += Math.abs(_query.getTime() - _case.getTime()) / 4000;

		/*
		 * simil += Math.abs(_query.getNearestPPill() - _case.getNearestPPill()) / 650;
		 * 
		 * simil += Math.abs(_query.getNearestGhost() - _case.getNearestGhost()) / 650;
		 * 
		 * simil += _query.getEdibleGhost().equals(_case.getEdibleGhost()) ? 1.0 : 0.0;
		 * 
		 */
		return simil / 5.0;

	}

}
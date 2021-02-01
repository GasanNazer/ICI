package es.ucm.fdi.ici.c2021.practica5.grupo06;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import es.ucm.fdi.gaia.jcolibri.exception.ExecutionException;
import es.ucm.fdi.ici.c2021.practica5.grupo06.CBRengine.GhostsCBRengine;
import es.ucm.fdi.ici.c2021.practica5.grupo06.CBRengine.GhostsStorageManager;
import es.ucm.fdi.ici.c2021.practica5.grupo06.actions.ChasePacManAction;
import es.ucm.fdi.ici.c2021.practica5.grupo06.actions.GoToPPillAction;
import es.ucm.fdi.ici.c2021.practica5.grupo06.actions.GoToPacmanStartingPointAction;
import es.ucm.fdi.ici.c2021.practica5.grupo06.actions.RunAwayAction;
import es.ucm.fdi.ici.c2021.practica5.grupo06.actions.RunAwayFromPacManAction;
import pacman.controllers.GhostController;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class Ghosts extends GhostController {

	GhostsInput input;
	GhostsCBRengine cbrEngine;
	GhostsActionSelector actionSelector;
	GhostsStorageManager storageManager;

	final static String FILE_PATH = "src\\es\\ucm\\fdi\\ici\\c2021\\practica5\\grupo06\\data\\grupo06\\%s.csv"; // Cuidado!! poner el grupo aquí

	public Ghosts() {
		this.input = new GhostsInput();

		List<Action> actions = new ArrayList<Action>();
		
		//new actions
		actions.add(new ChasePacManAction(GHOST.BLINKY));
		actions.add(new ChasePacManAction(GHOST.PINKY));
		actions.add(new ChasePacManAction(GHOST.INKY));
		actions.add(new ChasePacManAction(GHOST.SUE));
		
		actions.add(new RunAwayFromPacManAction(GHOST.BLINKY));
		actions.add(new RunAwayFromPacManAction(GHOST.PINKY));
		actions.add(new RunAwayFromPacManAction(GHOST.INKY));
		actions.add(new RunAwayFromPacManAction(GHOST.SUE));
		
		this.actionSelector = new GhostsActionSelector(actions);
		this.storageManager = new GhostsStorageManager();

		cbrEngine = new GhostsCBRengine(actionSelector, storageManager);
	}

	@Override
	public void preCompute(String opponent) {
		cbrEngine.setCaseBaseFile(String.format(FILE_PATH, opponent));
		try {
			cbrEngine.configure();
			cbrEngine.preCycle();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void postCompute() {
		try {
			cbrEngine.postCycle();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue) {
		EnumMap<GHOST, MOVE> result = new EnumMap<GHOST,MOVE>(GHOST.class);

		for(GHOST g : GHOST.values()) {
			// This implementation only computes a new action when MsPacMan is in a
			// junction.
			// This is relevant for the case storage policy
			if (!game.isJunction(game.getGhostCurrentNodeIndex(g)))
				result.put(g, MOVE.NEUTRAL);

			try {
				input.parseInput(game);
				actionSelector.setGame(game);
				storageManager.setGame(game);
				cbrEngine.cycle(input.getQuery());
				Action action = cbrEngine.getSolution();
				result.put(g, action.execute(game));
			} catch (Exception e) {
				e.printStackTrace();
			}
			result.put(g, MOVE.NEUTRAL);
		}
		
		return result;
	}

}

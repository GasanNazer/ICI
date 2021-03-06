package es.ucm.fdi.ici.c2021.practica5.grupo06;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.gaia.jcolibri.exception.ExecutionException;
import es.ucm.fdi.ici.c2021.practica5.grupo06.CBRengine.MsPacManCBRengine;
import es.ucm.fdi.ici.c2021.practica5.grupo06.CBRengine.MsPacManStorageManager;
import es.ucm.fdi.ici.c2021.practica5.grupo06.actions.RunAwayFromClosestGhosts;
import es.ucm.fdi.ici.c2021.practica5.grupo06.actions.RunTowardsNearestGhost;
import es.ucm.fdi.ici.c2021.practica5.grupo06.actions.RunTowardsPill;
import es.ucm.fdi.ici.c2021.practica5.grupo06.actions.RunTowardsPowerPill;
import pacman.controllers.PacmanController;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class MsPacMan extends PacmanController {

	MsPacManInput input;
	MsPacManCBRengine cbrEngine;
	MsPacManActionSelector actionSelector;
	MsPacManStorageManager storageManager;

	//final static String FILE_PATH = "src\\es\\ucm\\fdi\\ici\\c2021\\practica5\\grupo06\\data\\grupo06\\%s.csv"; // Cuidado!! poner el grupo aquí
	final static String FILE_PATH = "src/es/ucm/fdi/ici/c2021/practica5/grupo06/data/grupo06/%s.csv"; // Cuidado!! poner el grupo aquí

	public MsPacMan() {
		this.input = new MsPacManInput();

		List<Action> actions = new ArrayList<Action>();
		actions.add(new RunAwayFromClosestGhosts());
		actions.add(new RunTowardsPill());
		actions.add(new RunTowardsPowerPill());
		actions.add(new RunTowardsNearestGhost());
		this.actionSelector = new MsPacManActionSelector(actions);

		this.storageManager = new MsPacManStorageManager();

		cbrEngine = new MsPacManCBRengine(actionSelector, storageManager);
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
	public MOVE getMove(Game game, long timeDue) {

		// This implementation only computes a new action when MsPacMan is in a
		// junction.
		// This is relevant for the case storage policy
		if (!game.isJunction(game.getPacmanCurrentNodeIndex()))
			return MOVE.NEUTRAL;

		try {
			input.parseInput(game);
			actionSelector.setGame(game);
			storageManager.setGame(game);
			cbrEngine.cycle(input.getQuery());
			Action action = cbrEngine.getSolution();
			return action.execute(game);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MOVE.NEUTRAL;
	}

}

package es.ucm.fdi.ici.c2021.practica4.grupo06.actions;

import es.ucm.fdi.ici.fuzzy.Action;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class RunAwayFromSpecificGhost implements Action {

	public static int thresold = 30;
	private GHOST ghost;

	public RunAwayFromSpecificGhost(GHOST ghost) {
		this.ghost = ghost;
	}

	public MOVE execute(Game game) {
		return game.getNextMoveAwayFromTarget(game.getPacmanCurrentNodeIndex(),
				game.getGhostCurrentNodeIndex(this.ghost), DM.PATH);
	}
}

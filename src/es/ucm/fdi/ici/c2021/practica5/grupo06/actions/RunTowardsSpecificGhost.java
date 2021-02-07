package es.ucm.fdi.ici.c2021.practica5.grupo06.actions;

import es.ucm.fdi.ici.c2021.practica5.grupo06.Action;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class RunTowardsSpecificGhost implements Action {

	public static int thresold = 30;
	private GHOST ghost;

	public RunTowardsSpecificGhost(GHOST ghost) {
		this.ghost = ghost;
	}

	public MOVE execute(Game game) {
		return game.getApproximateNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(),
				game.getGhostCurrentNodeIndex(this.ghost), game.getPacmanLastMoveMade(), DM.PATH);
	}

	@Override
	public String getActionId() {
		return "RunTowardsSpecificGhost action";
	}
}

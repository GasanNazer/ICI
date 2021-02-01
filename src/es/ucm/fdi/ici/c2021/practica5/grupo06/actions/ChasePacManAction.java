package es.ucm.fdi.ici.c2021.practica5.grupo06.actions;

import es.ucm.fdi.ici.c2021.practica5.grupo06.Action;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class ChasePacManAction implements Action{
	private GHOST ghost;

	public ChasePacManAction(GHOST ghost) {
		this.ghost = ghost;
	}

	@Override
	public MOVE execute(Game game) {
		return	game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghost), game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghost), DM.PATH);
	}
	
	@Override
	public String getActionId() {
		return "ChasePacMan action " + ghost.name();
	}
}

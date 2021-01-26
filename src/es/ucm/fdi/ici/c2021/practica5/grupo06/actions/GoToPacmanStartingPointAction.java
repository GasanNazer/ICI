package es.ucm.fdi.ici.c2021.practica5.grupo06.actions;

import es.ucm.fdi.ici.c2021.practica5.grupo06.Action;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class GoToPacmanStartingPointAction implements Action {

	GHOST ghost;
	
	public GoToPacmanStartingPointAction(GHOST ghost) {
		this.ghost = ghost;
	}

	@Override
	public MOVE execute(Game game) {
		if(game.doesGhostRequireAction(ghost)) 
			return game.getNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghost), game.getPacManInitialNodeIndex(), game.getGhostLastMoveMade(ghost), DM.PATH);
		return MOVE.NEUTRAL;
	}

	@Override
	public String getActionId() {
		return "GoToPacManStartingPointAction";
	}

}

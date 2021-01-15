package es.ucm.fdi.ici.c2021.practica4.grupo06.actions;

import es.ucm.fdi.ici.fuzzy.Action;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class StayAwayFromOtherGhostsAction implements Action {
	
	GHOST ghost;
	
	public StayAwayFromOtherGhostsAction(GHOST ghost) {
		this.ghost = ghost;
	}

	@Override
	public MOVE execute(Game game) {
		if(game.doesGhostRequireAction(ghost)) {
			int dist = Integer.MAX_VALUE;
			GHOST closest = null;
			for(GHOST g : GHOST.values()) {
				if(!g.equals(ghost) && game.getGhostLairTime(g) <= 0 && game.getGhostLairTime(ghost) <= 0) {
					if(game.getShortestPathDistance(game.getGhostCurrentNodeIndex(ghost), game.getGhostCurrentNodeIndex(g), game.getGhostLastMoveMade(ghost)) < dist)
						closest = g;
				}
			}
			MOVE TowardsPacman = game.getNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghost), game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghost), DM.PATH);
			MOVE awayFromGhost = game.getNextMoveAwayFromTarget(game.getGhostCurrentNodeIndex(ghost), game.getGhostCurrentNodeIndex(closest), game.getGhostLastMoveMade(ghost), DM.PATH);
			if(closest == null || TowardsPacman.equals(awayFromGhost))
				return game.getNextMoveAwayFromTarget(game.getGhostCurrentNodeIndex(ghost), game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghost), DM.PATH);
			
			return awayFromGhost;
		}
		return MOVE.NEUTRAL;
	}

}
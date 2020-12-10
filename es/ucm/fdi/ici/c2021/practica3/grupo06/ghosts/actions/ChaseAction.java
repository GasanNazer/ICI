package es.ucm.fdi.ici.c2021.practica3.grupo06.ghosts.actions;

import es.ucm.fdi.ici.rules.Action;
import jess.Fact;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class ChaseAction implements Action {

    GHOST ghost;
	public ChaseAction( GHOST ghost) {
		this.ghost = ghost;
	}

	@Override
	public MOVE execute(Game game) {
        if (game.doesGhostRequireAction(ghost))        //if it requires an action
        {
        	int pill = getClosestPPillToPacman(game);
        	
        	if(ghost.equals(GHOST.INKY) && pill != -1) {
        		return nextMoveToMsPackManPath(game);
			}
			
			if(ghost.equals(GHOST.BLINKY)) {
				return nextMoveToMsPackManEuclid(game);
			}
			if(ghost.equals(GHOST.PINKY))
				return nextMoveToMsPackManPath(game);
        	
        	return game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghost),
                    game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghost), DM.MANHATTAN);
        }
        return MOVE.NEUTRAL;
	}

	@Override
	public void parseFact(Fact actionFact) {
		// Nothing to parse
		
	}
	
	private MOVE nextMoveToMsPackManPath(Game game) {
		return game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghost),
				game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghost), DM.PATH);
	}
	
	private MOVE nextMoveToMsPackManEuclid(Game game) {
		return game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghost),
				game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghost), DM.EUCLID);
	}

	private int getClosestPPillToPacman(Game game) {
		int distanceMin = Integer.MAX_VALUE;
		int index = -1;
		for(int p : game.getActivePowerPillsIndices()) {
			int dist = game.getShortestPathDistance(game.getGhostCurrentNodeIndex(ghost), game.getPowerPillIndex(p), game.getGhostLastMoveMade(ghost));
			if(dist < distanceMin) {
				distanceMin = dist;
				index = p;
			}
		}
		return index;
	}
	

}

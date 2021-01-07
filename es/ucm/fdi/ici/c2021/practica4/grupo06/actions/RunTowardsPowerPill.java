package es.ucm.fdi.ici.c2021.practica4.grupo06.actions;

import es.ucm.fdi.ici.fuzzy.Action;
import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class RunTowardsPowerPill implements Action {

	@Override
	public MOVE execute(Game game) {
		MOVE[] moves = game.getPossibleMoves(game.getPacmanCurrentNodeIndex(), game.getPacmanLastMoveMade());

		if (moves.length == 1)
			return moves[0];

		MOVE nextMove = moves[0];
		
		int distanceToPills = getDistanceToPP(moves[0], game);
		
		for (int i = 1; i < moves.length; i++) {
			int newDistance = getDistanceToPP(moves[i], game);
			if (newDistance < distanceToPills) {
				nextMove = moves[i];
				distanceToPills = newDistance;
			}

		}
		
		return nextMove;
	}
	
	public int getDistanceToPP(MOVE move, Game game) {
		int[] activePowerPills = {};
		int neighbourCell = -5, closestNode =  -5;
		try {
			activePowerPills = game.getActivePowerPillsIndices();
			neighbourCell = game.getNeighbour(game.getPacmanCurrentNodeIndex(), move);
			closestNode = game.getClosestNodeIndexFromNodeIndex(neighbourCell, activePowerPills, DM.PATH);
			return game.getShortestPathDistance(neighbourCell, closestNode, move);
			
		} catch (Exception ex) {
		}
		return 10000;
	}
}

package es.ucm.fdi.ici.c2021.practica1.grupo06;

import java.util.ArrayList;
import java.util.HashSet;

import pacman.controllers.PacmanController;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class MsPacMan extends PacmanController {
	int limit = 30;
	int eadibleLimit = 80;
	int powerPillLimit = 30;

	@Override
	public MOVE getMove(Game game, long timeDue) {
		GHOST nearestGhost = getNearestChasingGhost(game);
		if (nearestGhost != null) {
			return nextMove(game);
		}

		nearestGhost = getNearestEdibleGhost(game);
		if (nearestGhost != null) {
			return game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(),
					game.getGhostCurrentNodeIndex(nearestGhost), DM.PATH);
		}

		int nearestPill = getNearestPill(game);
		MOVE move = game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(), nearestPill, DM.PATH);
		return move;
	}

	private MOVE nextMove(Game game) {
		MOVE[] moves = game.getPossibleMoves(game.getPacmanCurrentNodeIndex(), game.getPacmanLastMoveMade());
		if(moves.length == 1)
			return moves[0];
		
		HashSet<MOVE> forbiddenMoves = new HashSet<MOVE>();
		for (GHOST ghostType : GHOST.values()) {

			try {
				if (!game.isGhostEdible(ghostType) && game.getDistance(game.getPacmanCurrentNodeIndex(),
						game.getGhostCurrentNodeIndex(ghostType), game.getPacmanLastMoveMade(), DM.PATH) < limit) {
					MOVE ghostMove = game.getMoveToMakeToReachDirectNeighbour(game.getPacmanCurrentNodeIndex(), game.getShortestPath(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(ghostType), game.getPacmanLastMoveMade())[0]);
					if(ghostMove != null)
						forbiddenMoves.add(ghostMove.opposite());
				}
			} catch (ArrayIndexOutOfBoundsException e) {

			}

		}
		if (forbiddenMoves.size() == 1)
			return game.getNextMoveAwayFromTarget(game.getPacmanCurrentNodeIndex(),
					game.getGhostCurrentNodeIndex(getNearestChasingGhost(game)), DM.PATH);
		
		MOVE nextMove = moves[0];
		int distanceToPills = game.getShortestPathDistance(game.getNeighbour(game.getPacmanCurrentNodeIndex(), moves[0]), game.getClosestNodeIndexFromNodeIndex(game.getNeighbour(game.getPacmanCurrentNodeIndex(), moves[0]), game.getActivePillsIndices(), DM.PATH), moves[0]);
		for(int i = 1; i < moves.length; i++) {
			if (!forbiddenMoves.contains(moves[i])) {
				int newDistance = game.getShortestPathDistance(game.getNeighbour(game.getPacmanCurrentNodeIndex(), moves[i]), game.getClosestNodeIndexFromNodeIndex(game.getNeighbour(game.getPacmanCurrentNodeIndex(), moves[i]), game.getActivePillsIndices(), DM.PATH), moves[i]);
				if(newDistance < distanceToPills) {
					nextMove = moves[i];
					distanceToPills = newDistance;
				}
			}
		}
		
		for (MOVE m : moves) {
			
		}
		return nextMove;
	}

	private GHOST getNearestChasingGhost(Game game) {
		int pacManNode = game.getPacmanCurrentNodeIndex();
		GHOST nearestGhost = null;

		for (GHOST ghostType : GHOST.values()) {
			if (game.getGhostEdibleTime(ghostType) < 50 && game.getGhostLairTime(ghostType) == 0
					&& !game.isGhostEdible(ghostType)) {
				int ghostNode = game.getGhostCurrentNodeIndex(ghostType);
				if (game.getShortestPathDistance(pacManNode, ghostNode, game.getPacmanLastMoveMade()) < limit) {
					nearestGhost = ghostType;
				}
			}
		}

		return nearestGhost;
	}

	private GHOST getNearestEdibleGhost(Game game) {
		int minDistance = eadibleLimit;
		int pacManNode = game.getPacmanCurrentNodeIndex();
		GHOST nearestGhost = null;
		ArrayList<GHOST>[] nearestGhostsNum = new ArrayList[4];
		int indexMax=0;
 
		for (GHOST ghostType : GHOST.values()) {
			if (game.getGhostEdibleTime(ghostType) > 0 && game.isGhostEdible(ghostType)) {
				//with distance we can have the same result
				int distance = game.getShortestPathDistance(pacManNode, game.getGhostCurrentNodeIndex(ghostType ));
				if (distance < minDistance) {
					minDistance = distance;
					nearestGhost = ghostType;
				}
				if(distance < eadibleLimit) {
					MOVE m = game.getGhostLastMoveMade(ghostType);
					if(nearestGhostsNum[m.ordinal()] == null) {
						nearestGhostsNum[m.ordinal()] = new ArrayList<>();
					}
					nearestGhostsNum[m.ordinal()].add(ghostType);
					if(nearestGhostsNum[indexMax] != null && nearestGhostsNum[m.ordinal()].size() >= nearestGhostsNum[indexMax].size())
						indexMax = m.ordinal();
				}
				
					
			}
		}
		
		if(nearestGhostsNum[indexMax] != null && nearestGhostsNum[indexMax].size() > 1)
			nearestGhost = nearestGhostsNum[indexMax].get(0);

		return nearestGhost;
	}

	private int getNearestPill(Game game) {
		// if ghost close - power pill, if not - normal pill
		int[] targetNodeIndices = game.getActivePillsIndices();
		GHOST nearestGhost = getNearestChasingGhost(game);
		if (nearestGhost != null && getNearestEdibleGhost(game) == null)
			if (game.getShortestPathDistance(game.getPacmanCurrentNodeIndex(),
					game.getGhostCurrentNodeIndex(nearestGhost), game.getPacmanLastMoveMade()) < powerPillLimit)
				targetNodeIndices = game.getActivePowerPillsIndices();

		return game.getClosestNodeIndexFromNodeIndex(game.getPacmanCurrentNodeIndex(), targetNodeIndices, DM.PATH);
	}

}

package es.ucm.fdi.ici.c2021.practica1.grupo06;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import pacman.controllers.PacmanController;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class MsPacMan extends PacmanController {
	int limit = 40;
	int eadibleLimit = 60;
	int powerPillLimit = 20;

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
		HashSet<MOVE> forbiddenMoves = new HashSet<MOVE>();
		for (GHOST ghostType : GHOST.values()) {

			try {
				if (!game.isGhostEdible(ghostType) && game.getDistance(game.getPacmanCurrentNodeIndex(),
						game.getGhostCurrentNodeIndex(ghostType), game.getPacmanLastMoveMade(), DM.PATH) < limit) {
					forbiddenMoves.add(game.getGhostLastMoveMade(ghostType).opposite());
				}
			} catch (ArrayIndexOutOfBoundsException e) {

			}

		}
		if (forbiddenMoves.size() == 1)
			return game.getNextMoveAwayFromTarget(game.getPacmanCurrentNodeIndex(),
					game.getGhostCurrentNodeIndex(getNearestChasingGhost(game)), DM.PATH);
		MOVE nextMove = moves[0];
		for (MOVE m : moves) {
			if (!forbiddenMoves.contains(m)) {
				nextMove = m;
				break;
			}
		}
		return nextMove;
	}

	private GHOST getNearestChasingGhost(Game game) {
		int pacManNode = game.getPacmanCurrentNodeIndex();
		GHOST nearestGhost = null;

		for (GHOST ghostType : GHOST.values()) {
			if (game.getGhostEdibleTime(ghostType) == 0 && game.getGhostLairTime(ghostType) == 0
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
		int minDistance = limit;
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

package es.ucm.fdi.ici.c2021.practica0.grupo06;

import java.util.ArrayList;

import pacman.controllers.PacmanController;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class MsPacMan extends PacmanController {

	@Override
	public MOVE getMove(Game game, long timeDue) {
		int limit = 20;

		GHOST nearestGhost = getNearestChasingGhost(game, limit);
		if (nearestGhost != null) {
			return game.getNextMoveAwayFromTarget(game.getPacmanCurrentNodeIndex(),
					game.getGhostCurrentNodeIndex(nearestGhost), DM.PATH);
		}

		nearestGhost = getNearestEdibleGhost(game, limit);
		if (nearestGhost != null) {
			return game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(),
					game.getGhostCurrentNodeIndex(nearestGhost), DM.PATH);
		}

		int nearestPill = getNearestPill(game);
		MOVE move = game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(), nearestPill, DM.PATH);
		return move;
	}

	private GHOST getNearestChasingGhost(Game game, int limit) {
		int pacManNode = game.getPacmanCurrentNodeIndex();
		GHOST nearestGhost = null;

		for (GHOST ghostType : GHOST.values()) {
			if (game.getGhostEdibleTime(ghostType) == 0 && game.getGhostLairTime(ghostType) == 0 && !game.isGhostEdible(ghostType)) {
				int ghostNode = game.getGhostCurrentNodeIndex(ghostType);
				if (game.getShortestPathDistance(pacManNode, ghostNode) < limit) {
					nearestGhost = ghostType;
				}
			}
		}

		return nearestGhost;
	}

	private GHOST getNearestEdibleGhost(Game game, int limit) {
		int minDistance = limit;
		int pacManNode = game.getPacmanCurrentNodeIndex();
		GHOST nearestGhost = null;

		for (GHOST ghostType : GHOST.values())
			if (game.getGhostEdibleTime(ghostType) > 0 && game.isGhostEdible(ghostType)) {
				//with distance we can have the same result
				int distance = game.getShortestPathDistance(pacManNode, game.getGhostCurrentNodeIndex(ghostType));
				if (distance < minDistance) {
					minDistance = distance;
					nearestGhost = ghostType;
				}
			}

		return nearestGhost;

	}

	private int getNearestPill(Game game) {
		int[] activePills = game.getActivePillsIndices();
		int[] activePowerPills = game.getActivePowerPillsIndices();
		int[] targetNodeIndices = new int[activePills.length + activePowerPills.length];

		for (int i = 0; i < activePills.length; i++) {
			targetNodeIndices[i] = activePills[i];
		}

		for (int i = 0; i < activePowerPills.length; i++) {
			targetNodeIndices[activePills.length + i] = activePowerPills[i];
		}

		return game.getClosestNodeIndexFromNodeIndex(game.getPacmanCurrentNodeIndex(), targetNodeIndices, DM.PATH);
	}
}

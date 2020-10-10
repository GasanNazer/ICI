package es.ucm.fdi.ici.c2021.practica0.grupo06;

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
					game.getGhostCurrentNodeIndex(nearestGhost), DM.EUCLID);
		}

		nearestGhost = getNearestEdibleGhost(game, limit);
		if (nearestGhost != null) {
			return game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(),
					game.getGhostCurrentNodeIndex(nearestGhost), DM.EUCLID);
		}
		
		int nearestPill = getNearestPill(game, limit);
		return game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(), nearestPill, DM.EUCLID);
	}

	private GHOST getNearestChasingGhost(Game game, int limit) {
		GHOST nearestGhost = null;
		int nearestDistance = limit;

		for (GHOST ghostType : GHOST.values()) {
			if (!game.isGhostEdible(ghostType)) {
				int distance = Math.abs(game.getGhostCurrentNodeIndex(ghostType) - game.getPacmanCurrentNodeIndex());
				if (distance < nearestDistance) {
					nearestGhost = ghostType;
					nearestDistance = distance;
				}
			}
		}

		return nearestGhost;
	}

	private GHOST getNearestEdibleGhost(Game game, int limit) {
		GHOST nearestGhost = null;
		int nearestDistance = limit;

		for (GHOST ghostType : GHOST.values()) {
			if (game.isGhostEdible(ghostType)) {
				int distance = Math.abs(game.getGhostCurrentNodeIndex(ghostType) - game.getPacmanCurrentNodeIndex());
				if (distance < nearestDistance) {
					nearestGhost = ghostType;
					nearestDistance = distance;
				}
			}
		}

		return nearestGhost;
	}

	private int getNearestPill(Game game, int limit) {
		int nearestPill = 0; // could be pill or power pill
		int nearestDistance = limit;

		// checking if the nearest pill is of type pill
		for (int pill : game.getPillIndices()) {
			int distance = Math.abs(game.getPillIndex(pill) - game.getPacmanCurrentNodeIndex());
			if (distance < nearestDistance) {
				nearestPill = pill;
				nearestDistance = distance;
			}
		}

		// checking if the nearest pill is of type power pill
		for (int pill : game.getPowerPillIndices()) {
			int distance = Math.abs(game.getPowerPillIndex(pill) - game.getPacmanCurrentNodeIndex());
			if (distance < nearestDistance) {
				nearestPill = pill;
				nearestDistance = distance;
			}
		}

		return nearestPill;
	}
}

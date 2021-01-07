package es.ucm.fdi.ici.c2021.practica3.grupo06.mspacman.actions;

import java.util.ArrayList;

import es.ucm.fdi.ici.rules.Action;
import jess.Fact;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class RunTowardsNearestGhost implements Action {
	public static final int eadibleLimit = 800;

	@Override
	public MOVE execute(Game game) {
		GHOST ghost = this.getNearestEdibleGhost(game);
		
		try {
			return game.getApproximateNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(),
					game.getGhostCurrentNodeIndex(ghost), game.getPacmanLastMoveMade(), DM.PATH);
		} catch (NullPointerException ex) {
			System.out.println("Oh no error in RunTowardsNearestGhost.");
		}
		return MOVE.NEUTRAL;
	}

	private GHOST getNearestEdibleGhost(Game game) {
		int minDistance = eadibleLimit;
		int pacManNode = game.getPacmanCurrentNodeIndex();
		GHOST nearestGhost = null;
		ArrayList<GHOST>[] nearestGhostsNum = new ArrayList[4];
		int indexMax = 0;

		for (GHOST ghostType : GHOST.values()) {
			if (game.getGhostEdibleTime(ghostType) > 0 && game.isGhostEdible(ghostType)) {
				// with distance we can have the same result
				int distance = game.getShortestPathDistance(pacManNode, game.getGhostCurrentNodeIndex(ghostType));
				if (distance < minDistance) {
					minDistance = distance;
					nearestGhost = ghostType;
				}
				if (distance < eadibleLimit) {
					MOVE m = game.getGhostLastMoveMade(ghostType);
					if (nearestGhostsNum[m.ordinal()] == null) {
						nearestGhostsNum[m.ordinal()] = new ArrayList<>();
					}
					nearestGhostsNum[m.ordinal()].add(ghostType);
					if (nearestGhostsNum[indexMax] != null
							&& nearestGhostsNum[m.ordinal()].size() >= nearestGhostsNum[indexMax].size())
						indexMax = m.ordinal();
				}

			}
		}

		if (nearestGhostsNum[indexMax] != null && nearestGhostsNum[indexMax].size() > 1)
			nearestGhost = nearestGhostsNum[indexMax].get(0);

		return nearestGhost;
	}

	@Override
	public void parseFact(Fact actionFact) {
		// TODO Auto-generated method stub
		
	}

}

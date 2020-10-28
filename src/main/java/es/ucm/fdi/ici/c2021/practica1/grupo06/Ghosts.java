package es.ucm.fdi.ici.c2021.practica1.grupo06;

import java.util.EnumMap;

import pacman.controllers.GhostController;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class Ghosts extends GhostController {

	private EnumMap<GHOST, MOVE> moves = new EnumMap<GHOST, MOVE>(GHOST.class);
	private final static int LIMIT = 15;
	private final static int GHOST_EATABLE_TIME_LIMIT = 100;
	private final static int DISTANCE_TO_PACMAN = 100;

	@Override
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue) {
		moves.clear();
		for (GHOST ghostType : GHOST.values()) {
			if (game.doesGhostRequireAction(ghostType)) {

				if (game.isGhostEdible(ghostType) || isCloseToPill(game)) {
					int ghostTime = game.getGhostEdibleTime(ghostType);

					int distanceToPacman = game.getShortestPathDistance(game.getGhostCurrentNodeIndex(ghostType),
							game.getPacmanCurrentNodeIndex());

					if (ghostTime < GHOST_EATABLE_TIME_LIMIT && distanceToPacman < DISTANCE_TO_PACMAN) {
						moves.put(ghostType,
								game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghostType),
										game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghostType),
										DM.EUCLID));

						for (GHOST ghostType2 : GHOST.values()) {
							if (!ghostType2.equals(ghostType)) {
								moves.put(ghostType2,
										game.getApproximateNextMoveAwayFromTarget(
												game.getGhostCurrentNodeIndex(ghostType2),
												game.getGhostCurrentNodeIndex(ghostType),
												game.getGhostLastMoveMade(ghostType2), DM.EUCLID));
							}
						}
					} else
						moves.put(ghostType,
								game.getApproximateNextMoveAwayFromTarget(game.getGhostCurrentNodeIndex(ghostType),
										game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghostType),
										DM.EUCLID));

				} else {
					moves.put(ghostType,
							game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghostType),
									game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghostType), DM.EUCLID));
				}

			}
		}
		return moves;
	}

	private boolean isCloseToPill(Game game) {
		return game.getClosestNodeIndexFromNodeIndex(game.getPacmanCurrentNodeIndex(),
				game.getActivePowerPillsIndices(), DM.PATH) <= LIMIT;
	}

}

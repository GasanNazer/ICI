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

		GHOST first = GHOST.PINKY;

		//moveOneGhost(first, moves, game);
		moveOneGhostV3(first, first, moves, game);

		GHOST second = GHOST.INKY;

		//getAwayFromGhost(second, first, moves, game);
		moveOneGhostV3(second, first, moves, game);

		GHOST third = GHOST.BLINKY;
		//moveOneGhost(third, moves, game);
		moveOneGhostV2(third, first, moves, game);
		
		
		GHOST fourth = GHOST.SUE;
		//moveOneGhost(fourth, moves, game);
		//moveOneGhostV2(fourth, third, moves, game);
		moveOneGhostV2(fourth, third, moves, game);
		
		

		/*
		 * for (GHOST ghostType : GHOST.values()) { if(!ghostType.equals(first) &&
		 * !ghostType.equals(second)) if (game.doesGhostRequireAction(ghostType)) {
		 * 
		 * if (game.isGhostEdible(ghostType) || isCloseToPill(game)) { int ghostTime =
		 * game.getGhostEdibleTime(ghostType);
		 * 
		 * int distanceToPacman =
		 * game.getShortestPathDistance(game.getGhostCurrentNodeIndex(ghostType),
		 * game.getPacmanCurrentNodeIndex());
		 * 
		 * 
		 * if (ghostTime < GHOST_EATABLE_TIME_LIMIT && distanceToPacman <
		 * DISTANCE_TO_PACMAN) { moves.put(ghostType,
		 * game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(
		 * ghostType), game.getPacmanCurrentNodeIndex(),
		 * game.getGhostLastMoveMade(ghostType), DM.EUCLID)); } else
		 * moves.put(ghostType,
		 * game.getApproximateNextMoveAwayFromTarget(game.getGhostCurrentNodeIndex(
		 * ghostType), game.getPacmanCurrentNodeIndex(),
		 * game.getGhostLastMoveMade(ghostType), DM.EUCLID));
		 * 
		 * MOVE moveToPackman =
		 * game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(
		 * ghostType), game.getPacmanCurrentNodeIndex(),
		 * game.getGhostLastMoveMade(ghostType), DM.PATH);
		 * 
		 * MOVE moveToPinky =
		 * game.getApproximateNextMoveAwayFromTarget(game.getGhostCurrentNodeIndex(
		 * ghostType), game.getGhostCurrentNodeIndex(first),
		 * game.getGhostLastMoveMade(ghostType), DM.PATH);
		 * if(moveToPackman.equals(moveToPinky)) { moves.put(ghostType,
		 * moveToPackman.opposite()); } else { moves.put(ghostType, moveToPinky); }
		 * 
		 * } else { moves.put(ghostType,
		 * game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(
		 * ghostType), game.getPacmanCurrentNodeIndex(),
		 * game.getGhostLastMoveMade(ghostType), DM.PATH)); } first = ghostType; } }
		 */
		return moves;
	}
	
	private void moveOneGhostV3(GHOST ghostType, GHOST first, EnumMap<GHOST, MOVE> moves, Game game) {
		if (game.doesGhostRequireAction(ghostType)) {
			if (game.isGhostEdible(ghostType) || isCloseToPill(game)) {
				int ghostTime = game.getGhostEdibleTime(ghostType);

				int distanceToPacman = game.getShortestPathDistance(game.getGhostCurrentNodeIndex(ghostType),
						game.getPacmanCurrentNodeIndex());

				if (ghostTime < GHOST_EATABLE_TIME_LIMIT && distanceToPacman < DISTANCE_TO_PACMAN) {
					moves.put(ghostType,
							game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghostType),
									game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghostType), DM.EUCLID));
				} else
					moves.put(ghostType,
							game.getApproximateNextMoveAwayFromTarget(game.getGhostCurrentNodeIndex(ghostType),
									game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghostType), DM.EUCLID));
			} else {
				moves.put(ghostType, game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghostType),
						game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghostType), DM.PATH));
			}
		}
	}

	private void moveOneGhostV2(GHOST ghostType, GHOST first, EnumMap<GHOST, MOVE> moves, Game game) {
		if (game.doesGhostRequireAction(ghostType)) {
			if (game.isGhostEdible(ghostType) || isCloseToPill(game)) {
				int ghostTime = game.getGhostEdibleTime(ghostType);

				int distanceToPacman = game.getShortestPathDistance(game.getGhostCurrentNodeIndex(ghostType),
						game.getPacmanCurrentNodeIndex());

				if (ghostTime < GHOST_EATABLE_TIME_LIMIT && distanceToPacman < DISTANCE_TO_PACMAN) {
					moves.put(ghostType,
							game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghostType),
									game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghostType), DM.EUCLID));
				} else
					moves.put(ghostType,
							game.getApproximateNextMoveAwayFromTarget(game.getGhostCurrentNodeIndex(ghostType),
									game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghostType), DM.EUCLID));

				MOVE moveToPackman = game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghostType),
						game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghostType), DM.PATH);

				MOVE moveToPinky = game.getApproximateNextMoveAwayFromTarget(game.getGhostCurrentNodeIndex(ghostType),
						game.getGhostCurrentNodeIndex(first), game.getGhostLastMoveMade(ghostType), DM.PATH);
				if (moveToPackman.equals(moveToPinky)) {
					moves.put(ghostType, moveToPackman.opposite());
				} else {
					moves.put(ghostType, moveToPinky);
				}
			} else {
				moves.put(ghostType, game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghostType),
						game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghostType), DM.PATH));
			}
		}
	}
	
	private boolean isCloseToPill(Game game) {
		return game.getClosestNodeIndexFromNodeIndex(game.getPacmanCurrentNodeIndex(),
				game.getActivePowerPillsIndices(), DM.PATH) <= LIMIT;
	}

}

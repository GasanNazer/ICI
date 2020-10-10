package es.ucm.fdi.ici.c2021.practica0.grupo06;

import java.util.EnumMap;
import java.util.Random;

import pacman.controllers.GhostController;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class Ghosts extends GhostController {
	
	private EnumMap<GHOST, MOVE> moves = new EnumMap<GHOST, MOVE>(GHOST.class);
	private MOVE[] allMoves = MOVE.values();
	private Random rnd = new Random();
	private final static int LIMIT = 15;

	@Override
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue) {
		moves.clear();
		for (GHOST ghostType : GHOST.values()) {
			if (game.doesGhostRequireAction(ghostType)) {
				if(game.isGhostEdible(ghostType) || isCloseToPill(game))
					moves.put(ghostType, game.getApproximateNextMoveAwayFromTarget(game.getGhostCurrentNodeIndex(ghostType), game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghostType), DM.EUCLID));
				else {
					double randAux = rnd.nextFloat();
					System.out.println(randAux);
					if(randAux < 0.9)
						moves.put(ghostType, game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghostType), game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghostType), DM.EUCLID));
					else
						moves.put(ghostType, allMoves[rnd.nextInt(allMoves.length)]);
				}
			}	
		}
		return moves;
	}
	
	private boolean isCloseToPill(Game game) {
		boolean isClose = false;
		
		for(int i :game.getPowerPillIndices()) {
			if(game.isPillStillAvailable(i)) {
				if(game.getDistance(game.getPacmanCurrentNodeIndex(), i, DM.PATH) <= LIMIT) {
					isClose = true;
					break;
				}
			}
		}
		
		return isClose;
	}

}

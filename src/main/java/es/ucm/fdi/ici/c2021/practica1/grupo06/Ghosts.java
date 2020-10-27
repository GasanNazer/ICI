package es.ucm.fdi.ici.c2021.practica1.grupo06;

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
		boolean ghostnear=false;
		for (GHOST ghostType : GHOST.values()) {
			if (game.doesGhostRequireAction(ghostType)) {

				if (game.isGhostEdible(ghostType) || isCloseToPill(game)) {
					int ghostTime = game.getGhostEdibleTime(ghostType);

					int distanceToPacman = game.getShortestPathDistance(game.getGhostCurrentNodeIndex(ghostType),
							game.getPacmanCurrentNodeIndex());

					//System.out.println("Distance to Pacman: " + distanceToPacman);
					//System.out.println(ghostType.name());
					//System.out.println(ghostType.name() + " editable time: " + ghostTime);
					//Parte aNASS
                     int distanceToPill=game.getShortestPathDistance(game.getGhostCurrentNodeIndex(ghostType),
                    		 game.getClosestNodeIndexFromNodeIndex(game.getPacmanCurrentNodeIndex(),
                      				game.getActivePowerPillsIndices(), DM.PATH));
                     if(distanceToPill<distanceToPacman) {
                    	 moves.put(ghostType,
 								game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghostType),
 										game.getClosestNodeIndexFromNodeIndex(game.getPacmanCurrentNodeIndex(),
 			                      				game.getActivePowerPillsIndices(), DM.PATH), game.getGhostLastMoveMade(ghostType),
 										DM.EUCLID));
                     }
                     
                     //FIN PARTE ANASS
                     	if (ghostTime < 100 && distanceToPacman < 100) {
                     		
                     		/*if(ghostnear) {;//hacer acorralamiento 
                     		}else {}
                     		}*/
						// System.out.println(ghostType.name());
						// System.out.println(ghostType.name() + " editable time: " + ghostTime);
						moves.put(ghostType,
								game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghostType),
										game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghostType),
										DM.EUCLID));
						for (GHOST ghostType2 : GHOST.values()) {
							if (!ghostType2.equals(ghostType)) {
								moves.put(ghostType2, game.getApproximateNextMoveAwayFromTarget(
										game.getGhostCurrentNodeIndex(ghostType2), game.getGhostCurrentNodeIndex(ghostType),
										game.getGhostLastMoveMade(ghostType2), DM.EUCLID));
							}
						}
						ghostnear=true;

					} else
						moves.put(ghostType,
								game.getApproximateNextMoveAwayFromTarget(game.getGhostCurrentNodeIndex(ghostType),
										game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghostType),
										DM.EUCLID));
				} else {
					//double randAux = rnd.nextFloat();
					//if (randAux < 0.9)
						moves.put(ghostType,
								game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghostType),
										game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghostType),
										DM.EUCLID));
						//else
						//moves.put(ghostType, allMoves[rnd.nextInt(allMoves.length)]);
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

package es.ucm.fdi.ici.c2021.practica0.grupo06;

import java.util.EnumMap;

import pacman.controllers.GhostController;
import pacman.controllers.POGhostController;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public final class GhostsAggresive extends POGhostController {
	 
	@Override
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue) {
		game.getApproximateNextMoveTowardsTarget(fromNodeIndex, toNodeIndex, lastMoveMade, distanceMeasure);
		
		return null;
	}
	
	

}

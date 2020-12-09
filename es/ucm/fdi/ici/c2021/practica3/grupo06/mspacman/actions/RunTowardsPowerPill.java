package es.ucm.fdi.ici.c2021.practica3.grupo06.mspacman.actions;

import es.ucm.fdi.ici.rules.Action;
import jess.Fact;
import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class RunTowardsPowerPill implements Action {

	@Override
	public MOVE execute(Game game) {
		MOVE[] moves = game.getPossibleMoves(game.getPacmanCurrentNodeIndex(), game.getPacmanLastMoveMade());

		if (moves.length == 1)
			return moves[0];

		MOVE nextMove = moves[0];
		
		int distanceToPills = game.getShortestPathDistance(
				game.getNeighbour(game.getPacmanCurrentNodeIndex(), moves[0]),
				game.getClosestNodeIndexFromNodeIndex(game.getNeighbour(game.getPacmanCurrentNodeIndex(), moves[0]),
						game.getActivePowerPillsIndices(), DM.PATH),
				moves[0]);
		
		for (int i = 1; i < moves.length; i++) {
			int newDistance = game.getShortestPathDistance(
					game.getNeighbour(game.getPacmanCurrentNodeIndex(), moves[i]),
					game.getClosestNodeIndexFromNodeIndex(game.getNeighbour(game.getPacmanCurrentNodeIndex(), moves[i]),
							game.getActivePowerPillsIndices(), DM.PATH),
					moves[i]);
			if (newDistance < distanceToPills) {
				nextMove = moves[i];
				distanceToPills = newDistance;
			}

		}
		return nextMove;
	}

	@Override
	public void parseFact(Fact actionFact) {
		// TODO Auto-generated method stub
		
	}

}

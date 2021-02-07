package es.ucm.fdi.ici.c2021.practica5.grupo06.actions;

import java.util.Random;

import es.ucm.fdi.ici.c2021.practica5.grupo06.Action;
import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class RunTowardsPill implements Action {

	@Override
	public MOVE execute(Game game) {
		MOVE[] moves = game.getPossibleMoves(game.getPacmanCurrentNodeIndex(), game.getPacmanLastMoveMade());

		if (moves.length == 1)
			return moves[0];

		MOVE nextMove = moves[0];

		int distanceToPills = getDistanceToPill(moves[0], game);

		for (int i = 1; i < moves.length; i++) {
			int newDistance = getDistanceToPill(moves[i], game);
			if (newDistance < distanceToPills) {
				nextMove = moves[i];
				distanceToPills = newDistance;
			}

		}
		return nextMove;
	}

	public int getDistanceToPill(MOVE move, Game game) {
		Random rnd = new Random();
		int[] activePills = {};
		int neighbourCell = -5, closestNode =  -5;
		try {
			activePills = game.getActivePillsIndices();
			neighbourCell = game.getNeighbour(game.getPacmanCurrentNodeIndex(), move);
			closestNode = game.getClosestNodeIndexFromNodeIndex(neighbourCell, activePills, DM.PATH);
			return game.getShortestPathDistance(neighbourCell, closestNode, move);
			
		} catch (Exception ex) {
		}
		return rnd.nextInt(100);
	}

	@Override
	public String getActionId() {
		return "RunTowardsPill";
	}
}

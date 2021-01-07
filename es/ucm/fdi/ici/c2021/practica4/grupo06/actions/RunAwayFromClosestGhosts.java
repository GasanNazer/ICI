package es.ucm.fdi.ici.c2021.practica4.grupo06.actions;

import java.util.HashSet;

import es.ucm.fdi.ici.fuzzy.Action;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class RunAwayFromClosestGhosts implements Action {

	public static int thresold = 30;

	public RunAwayFromClosestGhosts() {
	}

	public MOVE execute(Game game) {

		MOVE[] moves = game.getPossibleMoves(game.getPacmanCurrentNodeIndex(), game.getPacmanLastMoveMade());
		if (moves.length == 1)
			return moves[0];

		HashSet<MOVE> forbiddenMoves = new HashSet<MOVE>();
		for (GHOST ghostType : GHOST.values()) {

			try {
				if (game.getDistance(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(ghostType),
						game.getPacmanLastMoveMade(), DM.PATH) < this.thresold) {
					MOVE ghostMove = game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(),
							game.getGhostCurrentNodeIndex(ghostType), game.getPacmanLastMoveMade(), DM.PATH);
					if (ghostMove != null)
						forbiddenMoves.add(ghostMove.opposite());
				}
			} catch (Exception e) {

			}

		}
		if (forbiddenMoves.size() == 1)
			return game.getNextMoveAwayFromTarget(game.getPacmanCurrentNodeIndex(),
					game.getGhostCurrentNodeIndex(getNearestChasingGhost(game)), DM.PATH);

		MOVE nextMove = moves[0];
		try {
			int distanceToPills = game.getShortestPathDistance(
					game.getNeighbour(game.getPacmanCurrentNodeIndex(), moves[0]),
					game.getClosestNodeIndexFromNodeIndex(game.getNeighbour(game.getPacmanCurrentNodeIndex(), moves[0]),
							game.getActivePillsIndices(), DM.PATH),
					moves[0]);
			for (int i = 1; i < moves.length; i++) {
				if (!forbiddenMoves.contains(moves[i])) {
					int newDistance = game.getShortestPathDistance(
							game.getNeighbour(game.getPacmanCurrentNodeIndex(), moves[i]),
							game.getClosestNodeIndexFromNodeIndex(
									game.getNeighbour(game.getPacmanCurrentNodeIndex(), moves[i]),
									game.getActivePillsIndices(), DM.PATH),
							moves[i]);
					if (newDistance < distanceToPills) {
						nextMove = moves[i];
						distanceToPills = newDistance;
					}
				}
			}
		} catch (IndexOutOfBoundsException ex) {

		}
		return nextMove;
	}

	private GHOST getNearestChasingGhost(Game game) {
		int pacManNode = game.getPacmanCurrentNodeIndex();
		GHOST nearestGhost = null;

		for (GHOST ghostType : GHOST.values()) {
			int ghostNode = game.getGhostCurrentNodeIndex(ghostType);
			try {
				if (game.getShortestPathDistance(pacManNode, ghostNode, game.getPacmanLastMoveMade()) < this.thresold) {
					nearestGhost = ghostType;
				}
			} catch (Exception ex) {
				System.out.println("Ghost consumed.");
			}

		}
		return nearestGhost;
	}
}

package es.ucm.fdi.ici.c2021.practica3.grupo06.ghosts.actions;

import es.ucm.fdi.ici.rules.Action;
import jess.Fact;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class RunAwayAction implements Action {

	GHOST ghost;

	public RunAwayAction(GHOST ghost) {
		this.ghost = ghost;
	}

	@Override
	public void parseFact(Fact actionFact) {
		// Nothing to parse
	}

	@Override
	public MOVE execute(Game game) {
		if (game.doesGhostRequireAction(ghost)) {
			/*if (ghost.equals(GHOST.PINKY) || ghost.equals(GHOST.INKY))
				return nextMoveAwayMsPackManPath(game);
			if (ghost.equals(GHOST.BLINKY)) {
				return nextMoveAwayMsPackManEuclid(game);
			}

			return nextMoveAwayMsPackManManhattan(game);
			*/
			return nextMoveAwayMsPackManPath(game);
		}
		
		return MOVE.NEUTRAL;
	}

	private MOVE nextMoveAwayMsPackManPath(Game game) {
		return game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghost),
				game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghost), DM.PATH);
	}

	private MOVE nextMoveAwayMsPackManEuclid(Game game) {
		return game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghost),
				game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghost), DM.EUCLID);
	}

	private MOVE nextMoveAwayMsPackManManhattan(Game game) {
		return game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghost),
				game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghost), DM.MANHATTAN);
	}
}

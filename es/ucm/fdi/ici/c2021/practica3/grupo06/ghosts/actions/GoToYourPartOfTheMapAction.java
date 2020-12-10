package es.ucm.fdi.ici.c2021.practica3.grupo06.ghosts.actions;

import es.ucm.fdi.ici.rules.Action;
import jess.Fact;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class GoToYourPartOfTheMapAction implements Action {

	GHOST ghost;
	private final static int LIMIT = 30;
	
	public GoToYourPartOfTheMapAction(GHOST ghost) {
		this.ghost = ghost;
	}

	@Override
	public MOVE execute(Game game) {
		if (game.doesGhostRequireAction(ghost)) {
			MOVE m = game.getNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghost), game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghost), DM.PATH);
			MOVE m2 = game.getNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghost), game.getPowerPillIndices()[ghost.ordinal()], DM.PATH);
			
			if(m.equals(m2))
				return game.getNextMoveAwayFromTarget(game.getGhostCurrentNodeIndex(ghost), game.getPacmanCurrentNodeIndex(), DM.PATH);
			else
				return m2;
		}

		return MOVE.NEUTRAL;
	}

	@Override
	public void parseFact(Fact actionFact) {
		
	}

}
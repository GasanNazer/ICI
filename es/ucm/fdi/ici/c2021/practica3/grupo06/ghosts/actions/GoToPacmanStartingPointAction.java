package es.ucm.fdi.ici.c2021.practica3.grupo06.ghosts.actions;

import es.ucm.fdi.ici.rules.Action;
import jess.Fact;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class GoToPacmanStartingPointAction implements Action {

	GHOST ghost;
	
	public GoToPacmanStartingPointAction(GHOST ghost) {
		this.ghost = ghost;
	}

	@Override
	public MOVE execute(Game game) {
		if(game.doesGhostRequireAction(ghost)) 
			return game.getNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghost), game.getPacManInitialNodeIndex(), game.getGhostLastMoveMade(ghost), DM.PATH);
		return MOVE.NEUTRAL;
	}

	@Override
	public void parseFact(Fact actionFact) {
		// TODO Auto-generated method stub
		
	}

}

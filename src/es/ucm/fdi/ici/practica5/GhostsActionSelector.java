package es.ucm.fdi.ici.c2021.practica4.grupo06;

import java.util.HashMap;

import es.ucm.fdi.ici.c2021.practica4.grupo06.actions.GoToPPillAction;
import es.ucm.fdi.ici.c2021.practica4.grupo06.actions.GoToYourPartOfTheMapAction;
import es.ucm.fdi.ici.c2021.practica4.grupo06.actions.RunAwayFromClosestGhosts;
import es.ucm.fdi.ici.c2021.practica4.grupo06.actions.ChaseAction;
import es.ucm.fdi.ici.c2021.practica4.grupo06.actions.GhostRunAwayAction;
import es.ucm.fdi.ici.fuzzy.Action;
import es.ucm.fdi.ici.fuzzy.ActionSelector;
import pacman.game.Constants.GHOST;

public class GhostsActionSelector implements ActionSelector {
	
	GHOST ghost;
	private final Double RUN_AWAY_LIMIT = 20.0;
	private final Double CHASE_LIMIT = 17.0;
	
	GhostsActionSelector(GHOST ghost){
		super();
		this.ghost = ghost;
	}

	@Override
	public Action selectAction(HashMap<String, Double> fuzzyOutput) {
		Double runAwayFromClosestGhost = fuzzyOutput.get("runAwayFromClosestGhost");
		Double runAwayFromPacMan = fuzzyOutput.get("runAwayFromPacMan");
		Double chase = fuzzyOutput.get("chase");
		Double goToYourPartOfMap = fuzzyOutput.get("goToYourPartOfMap");
		if(runAwayFromPacMan> this.RUN_AWAY_LIMIT)
			return new GhostRunAwayAction(ghost);
		else if(runAwayFromClosestGhost > this.RUN_AWAY_LIMIT)
			return new RunAwayFromClosestGhosts();
		else if(chase > this.CHASE_LIMIT)
			return new ChaseAction(ghost);
		else
			return new GoToYourPartOfTheMapAction(ghost);
	}

}

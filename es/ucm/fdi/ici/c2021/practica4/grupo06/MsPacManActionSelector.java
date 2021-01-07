package es.ucm.fdi.ici.c2021.practica4.grupo06;

import java.util.HashMap;

import es.ucm.fdi.ici.c2021.practica4.grupo06.actions.RunAwayAction;
import es.ucm.fdi.ici.c2021.practica4.grupo06.actions.RunAwayFromClosestGhosts;
import es.ucm.fdi.ici.c2021.practica4.grupo06.actions.RunAwayFromSpecificGhost;
import es.ucm.fdi.ici.c2021.practica4.grupo06.actions.RunTowardsPill;
import es.ucm.fdi.ici.c2021.practica4.grupo06.actions.RunTowardsPowerPill;
import es.ucm.fdi.ici.c2021.practica4.grupo06.actions.RunTowardsSpecificGhost;
import es.ucm.fdi.ici.fuzzy.Action;
import es.ucm.fdi.ici.fuzzy.ActionSelector;
import pacman.game.Constants.GHOST;

public class MsPacManActionSelector implements ActionSelector {

	private final Double RUN_AWAY_LIMIT = 26.0;
	private final Double RUN_AWAY_TO_PILL = 15.0;
	private final Double CONSUMABLE_LIMIT = 30.0;

	@Override
	public Action selectAction(HashMap<String, Double> fuzzyOutput) {
		//wait(2);
		
		
		
		Double runAway = fuzzyOutput.get("runAway");
		Double runAwayFromBlinky = fuzzyOutput.get("runAwayFromBlinky");
		Double runAwayFromPinky = fuzzyOutput.get("runAwayFromPinky");
		Double runAwayFromInky = fuzzyOutput.get("runAwayFromInky");
		Double runAwayFromSue = fuzzyOutput.get("runAwayFromSue");
		Double consumableGhosts = fuzzyOutput.get("consumableGhosts");
		Double consumableGhosts2 = fuzzyOutput.get("consumableGhosts2");
		Double consumableGhosts3 = fuzzyOutput.get("consumableGhosts3");
		Double consumableGhosts4 = fuzzyOutput.get("consumableGhosts4");
		
		// run to ghost as it is consumable
		
		if(consumableGhosts > CONSUMABLE_LIMIT)
			return new RunTowardsSpecificGhost(GHOST.BLINKY);
		if(consumableGhosts2 > CONSUMABLE_LIMIT)
			return new RunTowardsSpecificGhost(GHOST.PINKY);
		if(consumableGhosts3 > CONSUMABLE_LIMIT)
			return new RunTowardsSpecificGhost(GHOST.INKY);
		if(consumableGhosts4 > CONSUMABLE_LIMIT)
			return new RunTowardsSpecificGhost(GHOST.SUE);
		
		// run from specific ghost
		if(runAwayFromBlinky > this.RUN_AWAY_LIMIT)
			return new RunAwayFromSpecificGhost(GHOST.BLINKY);
		if(runAwayFromPinky > this.RUN_AWAY_LIMIT)
			return new RunAwayFromSpecificGhost(GHOST.PINKY);
		if(runAwayFromInky > this.RUN_AWAY_LIMIT)
			return new RunAwayFromSpecificGhost(GHOST.INKY);
		if(runAwayFromSue > this.RUN_AWAY_LIMIT)
			return new RunAwayFromSpecificGhost(GHOST.SUE);
		
		//general movement
		if(runAway < RUN_AWAY_TO_PILL)
			//run to Pill
			return new RunTowardsPill();
		else if(runAway > this.RUN_AWAY_LIMIT)
			//return new RunAwayAction();
			return new RunAwayFromClosestGhosts();
		else
			//return new GoToPPillAction();
			return new RunTowardsPowerPill();
		
	}
	
	private void wait(int sec) {
		try {
			System.out.println("sleep");
			Thread.sleep(sec * 10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

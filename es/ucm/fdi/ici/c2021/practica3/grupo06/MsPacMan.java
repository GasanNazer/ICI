package es.ucm.fdi.ici.c2021.practica3.grupo06;

import java.util.HashMap;

import es.ucm.fdi.ici.c2021.practica3.grupo06.mspacman.MsPacManInput;
import es.ucm.fdi.ici.c2021.practica3.grupo06.mspacman.actions.ContinueRunningInTheSameDirection;
import es.ucm.fdi.ici.c2021.practica3.grupo06.mspacman.actions.RunAwayFromClosestGhosts;
import es.ucm.fdi.ici.c2021.practica3.grupo06.mspacman.actions.RunTowardsNearestGhost;
import es.ucm.fdi.ici.c2021.practica3.grupo06.mspacman.actions.RunTowardsPill;
import es.ucm.fdi.ici.c2021.practica3.grupo06.mspacman.actions.RunTowardsPowerPill;
import es.ucm.fdi.ici.rules.Action;
import es.ucm.fdi.ici.rules.Input;
import es.ucm.fdi.ici.rules.RuleEngine;
import pacman.controllers.PacmanController;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class MsPacMan extends PacmanController {
	
	private static final String RULES_PATH = "es/ucm/fdi/ici/c2021/practica3/grupo06/";

	HashMap<String,Action> map;
	
	RuleEngine pacmanRuleEngine;
	
	public MsPacMan() {
		map = new HashMap<String,Action>();
		Action ContinueRunningInTheSameDirection = new ContinueRunningInTheSameDirection();
		Action RunAwayFromClosestGhosts = new RunAwayFromClosestGhosts();
		Action RunTowardsNearestGhost = new RunTowardsNearestGhost();
		Action RunTowardsPill = new RunTowardsPill();
		Action RunTowardsPowerPill = new RunTowardsPowerPill();
		
		map.put("ContinueRunningInTheSameDirection", ContinueRunningInTheSameDirection);
		map.put("RunAwayFromClosestGhosts", RunAwayFromClosestGhosts);
		map.put("RunTowardsNearestGhost", RunTowardsNearestGhost);
		map.put("RunTowardsPill", RunTowardsPill);
		map.put("RunTowardsPowerPill", RunTowardsPowerPill);
	
		String rulesFile = String.format("%s/%srules.clp", RULES_PATH, "mspacman");
		pacmanRuleEngine = new RuleEngine("pacmanRuleEngine", rulesFile, map);
	}
	
	@Override
	public MOVE getMove(Game game, long timeDue) {
		Input input = new MsPacManInput(game);
		pacmanRuleEngine.reset();
		pacmanRuleEngine.assertFacts(input.getFacts());
		MOVE result = pacmanRuleEngine.run(game);	
		return result;
	}

}

package es.ucm.fdi.ici.c2021.practica3.grupo06;

import java.util.EnumMap;
import java.util.HashMap;

import es.ucm.fdi.ici.c2021.practica3.grupo06.ghosts.GhostsInput;
import es.ucm.fdi.ici.c2021.practica3.grupo06.ghosts.actions.ChaseAction;
import es.ucm.fdi.ici.c2021.practica3.grupo06.ghosts.actions.GoToPacmanStartingPointAction;
import es.ucm.fdi.ici.c2021.practica3.grupo06.ghosts.actions.GoToYourPartOfTheMapAction;
import es.ucm.fdi.ici.c2021.practica3.grupo06.ghosts.actions.RunAwayAction;
import es.ucm.fdi.ici.c2021.practica3.grupo06.ghosts.actions.StayAwayFromOtherGhostsAction;
import es.ucm.fdi.ici.rules.Action;
import es.ucm.fdi.ici.rules.Input;
import es.ucm.fdi.ici.rules.RuleEngine;
import es.ucm.fdi.ici.rules.observers.ConsoleRuleEngineObserver;
import pacman.controllers.GhostController;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class Ghosts  extends GhostController  {

	private static final String RULES_PATH = "es/ucm/fdi/ici/c2021/practica3/grupo06";
	
	HashMap<String,Action> map;
	
	EnumMap<GHOST,RuleEngine> ghostRuleEngines;
	
	
	public Ghosts() {
		
		map = new HashMap<String,Action>();
		//Fill Actions
		Action BLINKYchases = new ChaseAction(GHOST.BLINKY);
		Action INKYchases = new ChaseAction(GHOST.INKY);
		Action PINKYchases = new ChaseAction(GHOST.PINKY);
		Action SUEchases = new ChaseAction(GHOST.SUE);
		Action BLINKYrunsAway = new RunAwayAction(GHOST.BLINKY);
		Action INKYrunsAway = new RunAwayAction(GHOST.INKY);
		Action PINKYrunsAway = new RunAwayAction(GHOST.PINKY);
		Action SUErunsAway = new RunAwayAction(GHOST.SUE);
		
		//should be deleted
		Action BLINKYgoesToInitialPacMan = new GoToPacmanStartingPointAction(GHOST.BLINKY);
		Action INKYgoesToInitialPacMan = new GoToPacmanStartingPointAction(GHOST.INKY);
		Action PINKYgoesToInitialPacMan = new GoToPacmanStartingPointAction(GHOST.PINKY);
		Action SUEgoesToInitialPacMan = new GoToPacmanStartingPointAction(GHOST.SUE);
		
		//
		Action BLINKYcloseToOthers = new StayAwayFromOtherGhostsAction(GHOST.BLINKY);
		Action INKYcloseToOthers = new StayAwayFromOtherGhostsAction(GHOST.INKY);
		Action PINKYcloseToOthers = new StayAwayFromOtherGhostsAction(GHOST.PINKY);
		Action SUEcloseToOthers = new StayAwayFromOtherGhostsAction(GHOST.SUE);
		
		//
		Action BLINKYfarFromOthers = new GoToYourPartOfTheMapAction(GHOST.BLINKY);
		Action INKYfarFromOthers = new GoToYourPartOfTheMapAction(GHOST.INKY);
		Action PINKYfarFromOthers = new GoToYourPartOfTheMapAction(GHOST.PINKY);
		Action SUEfarFromOthers = new GoToYourPartOfTheMapAction(GHOST.SUE);
		
		
		map.put("BLINKYchases", BLINKYchases);
		map.put("INKYchases", INKYchases);
		map.put("PINKYchases", PINKYchases);
		map.put("SUEchases", SUEchases);	
		map.put("BLINKYrunsAway", BLINKYrunsAway);
		map.put("INKYrunsAway", INKYrunsAway);
		map.put("PINKYrunsAway", PINKYrunsAway);
		map.put("SUErunsAway", SUErunsAway);
		
		//should be deleted
		
		map.put("BLINKYgoesToInitialPacMan", BLINKYgoesToInitialPacMan);
		map.put("INKYGoToPacmanStartingPointAction", INKYgoesToInitialPacMan);
		map.put("PINKYgoesToInitialPacMan", PINKYgoesToInitialPacMan);
		map.put("SUEgoesToInitialPacMan", SUEgoesToInitialPacMan);
		
		//
		
		map.put("BLINKYcloseToOthers", BLINKYcloseToOthers);
		map.put("INKYcloseToOthers", INKYcloseToOthers);
		map.put("PINKYcloseToOthers", PINKYcloseToOthers);
		map.put("SUEcloseToOthers", SUEcloseToOthers);
		
		//
		
		map.put("BLINKYfarFromOthers", BLINKYfarFromOthers);
		map.put("INKYfarFromOthers", INKYfarFromOthers);
		map.put("PINKYfarFromOthers", PINKYfarFromOthers);
		map.put("SUEfarFromOthers", SUEfarFromOthers);
		
		
		
		ghostRuleEngines = new EnumMap<GHOST,RuleEngine>(GHOST.class);
		for(GHOST ghost: GHOST.values())
		{
			String rulesFile = String.format("%s/%srules.clp", RULES_PATH, ghost.name().toLowerCase());
			RuleEngine engine  = new RuleEngine(ghost.name(),rulesFile, map);
			ghostRuleEngines.put(ghost, engine);
			
			//add observer to every Ghost
			//ConsoleRuleEngineObserver observer = new ConsoleRuleEngineObserver(ghost.name(), true);
			//engine.addObserver(observer);
		}
		
		//add observer only to BLINKY
		ConsoleRuleEngineObserver observer = new ConsoleRuleEngineObserver(GHOST.BLINKY.name(), true);
		ghostRuleEngines.get(GHOST.BLINKY).addObserver(observer);
		
	}

	@Override
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue) {
		
		//Process input
		Input input = new GhostsInput(game);
		//load facts
		//reset the rule engines
		for(RuleEngine engine: ghostRuleEngines.values()) {
			engine.reset();
			engine.assertFacts(input.getFacts());
		}
		
		EnumMap<GHOST,MOVE> result = new EnumMap<GHOST,MOVE>(GHOST.class);		
		for(GHOST ghost: GHOST.values())
		{
			RuleEngine engine = ghostRuleEngines.get(ghost);
			MOVE move = engine.run(game);
			result.put(ghost, move);
		}
		
		return result;
	}

}

package es.ucm.fdi.ici.c2021.practica4.grupo06;

import java.util.EnumMap;

import es.ucm.fdi.ici.fuzzy.ActionSelector;
import es.ucm.fdi.ici.fuzzy.FuzzyEngine;
import es.ucm.fdi.ici.fuzzy.observers.ConsoleFuzzyEngineObserver;
import pacman.controllers.GhostController;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class Ghosts extends GhostController {
	
	private static final String RULES_PATH = "es\\ucm\\fdi\\ici\\c2021\\practica4\\grupo06\\";
	EnumMap<GHOST,FuzzyEngine> ghostFuzzyEngines;
	GhostsInput input ;
	
	public Ghosts()
	{
		input = new GhostsInput();
		ghostFuzzyEngines = new EnumMap<GHOST,FuzzyEngine>(GHOST.class);
		for(GHOST ghost: GHOST.values())
		{
			ActionSelector actionSelector = new GhostsActionSelector(ghost);
			String rulesFile = String.format("%s%srules.fcl", RULES_PATH, ghost.name().toLowerCase());
			FuzzyEngine fuzzyEngine = new FuzzyEngine(ghost.name(),rulesFile,ghost.name(),actionSelector);
			ghostFuzzyEngines.put(ghost, fuzzyEngine);
		}
	}

	@Override
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue) {
		EnumMap<GHOST,MOVE> result = new EnumMap<GHOST,MOVE>(GHOST.class);		
		for(GHOST ghost: GHOST.values())
		{
			FuzzyEngine engine = ghostFuzzyEngines.get(ghost);
			MOVE move = engine.run(input.getFuzzyValues(),game);
			result.put(ghost, move);
		}
		
		return result;
	}

}

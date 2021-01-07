package es.ucm.fdi.ici.c2021.practica4.grupo06;

import es.ucm.fdi.ici.fuzzy.ActionSelector;
import es.ucm.fdi.ici.fuzzy.FuzzyEngine;
import es.ucm.fdi.ici.fuzzy.observers.ConsoleFuzzyEngineObserver;
import pacman.controllers.PacmanController;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class MsPacMan extends PacmanController {

	private static final String RULES_PATH = "es\\ucm\\fdi\\ici\\c2021\\practica4\\grupo06\\";
	FuzzyEngine fuzzyEngine;
	MsPacManInput input ;
	
	public MsPacMan()
	{
		ActionSelector actionSelector = new MsPacManActionSelector();
		input = new MsPacManInput();
		 
		/*
		ConsoleFuzzyEngineObserver observer = new ConsoleFuzzyEngineObserver("MsPacMan","MsPacManRules");
		ConsoleFuzzyEngineObserver observer2 = new ConsoleFuzzyEngineObserver("MsPacMan","MsPacManBlinkyRunAway");
		ConsoleFuzzyEngineObserver observer3 = new ConsoleFuzzyEngineObserver("MsPacMan","MsPacManPinkyRunAway");
		ConsoleFuzzyEngineObserver observer4 = new ConsoleFuzzyEngineObserver("MsPacMan","MsPacManInkyRunAway");
		ConsoleFuzzyEngineObserver observer5 = new ConsoleFuzzyEngineObserver("MsPacMan","MsPacManSueRunAway");
		
		ConsoleFuzzyEngineObserver observer6 = new ConsoleFuzzyEngineObserver("MsPacMan","MsPacManRulesConsume");

		*/
		
		fuzzyEngine = new FuzzyEngine("MsPacMan",RULES_PATH+"mspacman.fcl","FuzzyMsPacMan",actionSelector);
		/*
		fuzzyEngine.addObserver(observer);
		fuzzyEngine.addObserver(observer2);
		fuzzyEngine.addObserver(observer3);
		fuzzyEngine.addObserver(observer4);
		fuzzyEngine.addObserver(observer5);
		
		fuzzyEngine.addObserver(observer6);
		*/
	}
	
	
	@Override
	public MOVE getMove(Game game, long timeDue) {
		input.parseInput(game);
		return fuzzyEngine.run(input.getFuzzyValues(),game);
	}

}

package es.ucm.fdi.ici.c2021.practica3.grupo06.mspacman.actions;

import es.ucm.fdi.ici.rules.Action;
import jess.Fact;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class ContinueRunningInTheSameDirection implements Action{

	@Override
	public MOVE execute(Game game) {
		return game.getPacmanLastMoveMade();
	}

	@Override
	public void parseFact(Fact actionFact) {
		// TODO Auto-generated method stub
		
	}

}

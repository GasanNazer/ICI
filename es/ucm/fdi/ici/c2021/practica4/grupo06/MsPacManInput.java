package es.ucm.fdi.ici.c2021.practica4.grupo06;

import java.util.HashMap;

import es.ucm.fdi.ici.fuzzy.Input;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class MsPacManInput implements Input {

	double[] distance = {80,80,80,80};//{50,50,50,50};
	double[] confidence = {100,100,100,100};
	double[] consumableTime = {0,0,0,0};

	
	
	@Override
	public void parseInput(Game game) {
		for(GHOST g: GHOST.values()) {
			int index = g.ordinal();
			//distance and confidence
			int pos = game.getGhostCurrentNodeIndex(g);
			if(pos != -1) {
				distance[index] = game.getDistance(game.getPacmanCurrentNodeIndex(), pos, DM.PATH);
				confidence[index] = 100;
			} else if (confidence[index] > 0)
				confidence[index]-= .5;//.1;
			
			//consumable time
			
			if(game.isGhostEdible(g) != null && game.isGhostEdible(g)) {
				consumableTime[index] = game.getGhostEdibleTime(g);
			}
			else
				consumableTime[index] = 0;
			
			/*if(game.isGhostEdible(g) != null && !game.isGhostEdible(g)) {
				consumableTime[index] = 0;
			}*/
			
			
			
		}
	}

	@Override
	public HashMap<String, Double> getFuzzyValues() {
		HashMap<String,Double> vars = new HashMap<String,Double>();
		for(GHOST g: GHOST.values()) {
			vars.put(g.name()+"distance",   distance[g.ordinal()]);
			vars.put(g.name()+"confidence", confidence[g.ordinal()]);
			vars.put(g.name()+"consumableTime", consumableTime[g.ordinal()]);
		}
		return vars;
	}

}

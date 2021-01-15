package es.ucm.fdi.ici.c2021.practica4.grupo06;

import java.util.HashMap;

import es.ucm.fdi.ici.fuzzy.Input;
import pacman.game.Game;
import pacman.game.Constants.GHOST;

public class GhostsInput implements Input {
	
	double[] edible = {0, 0 , 0, 0};
	double pacmanDistancePPill;
	double[] distToClosestGhost = {0, 0, 0, 0};
	double[] distToMsPacMan = {100, 100, 100, 100}; 
	

	@Override
	public void parseInput(Game game) {
		for(GHOST g: GHOST.values()) {
			int index = g.ordinal();
			edible[index] = game.isGhostEdible(g) ? 1 : 0;
			distToClosestGhost[index] = getClosestGhostDistance(game, g);
			distToMsPacMan[index] = game.getShortestPathDistance(game.getGhostCurrentNodeIndex(g), game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(g));
		}
		pacmanDistancePPill = getDistToClosestPowerPill(game);
	}

	@Override
	public HashMap<String, Double> getFuzzyValues() {
		HashMap<String,Double> vars = new HashMap<String,Double>();
		for(GHOST g: GHOST.values()) {
			vars.put(g.name()+"edible", edible[g.ordinal()]);
			vars.put(g.name()+"distToClosestGhost", distToClosestGhost[g.ordinal()]);
			vars.put(g.name()+"distToMsPacMan", distToMsPacMan[g.ordinal()]);
		}
		vars.put("distancePacManToPPill", pacmanDistancePPill);
		return vars;
	}
	
	private int getClosestGhostDistance(Game game, GHOST ghost) {
		int dist = Integer.MAX_VALUE;
		for(GHOST g: GHOST.values()) {
			if(!ghost.equals(g)) {
				int auxDistance = getDistanceBetweenGhosts(game, ghost, g);
				if(auxDistance < dist)
					dist = auxDistance;
			}
		}
		return dist;
	}
	
	private int getDistanceBetweenGhosts(Game game, GHOST g1, GHOST g2) {
		return game.getShortestPathDistance(game.getGhostCurrentNodeIndex(g1), game.getGhostCurrentNodeIndex(g2), game.getGhostLastMoveMade(g1));
	}
	
	private int getDistToClosestPowerPill(Game game) {
		int dist = Integer.MAX_VALUE;
		for(int ppill: game.getActivePowerPillsIndices()) {
			int aux = game.getShortestPathDistance(game.getPacmanCurrentNodeIndex(), game.getPowerPillIndex(ppill), game.getPacmanLastMoveMade());
			if(aux < dist)
				dist = aux;
		}
		return dist;
	}

}

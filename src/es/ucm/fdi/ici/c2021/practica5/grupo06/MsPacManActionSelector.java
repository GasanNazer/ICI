package es.ucm.fdi.ici.c2021.practica5.grupo06;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import es.ucm.fdi.ici.c2021.practica5.grupo06.CBRengine.GhostsDescription;
import es.ucm.fdi.ici.c2021.practica5.grupo06.CBRengine.MsPacManDescription;
import pacman.game.Game;

public class MsPacManActionSelector {

	HashMap<String, Action> map;
	List<Action> actions;
	Game game;

	public MsPacManActionSelector(List<Action> actions) {
		this.map = new HashMap<String, Action>();
		for (Action a : actions)
			map.put(a.getActionId(), a);
		this.actions = actions;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * Method called when the CBREngine is not able to find a suitable action.
	 * Simplest implementation returns a random one.
	 * 
	 * @return
	 */
	public Action findAction(MsPacManDescription description) {		
		if(description.getNearestNonEdibleGhostDist() < 50) {
			return this.getAction("RunAwayFromClosestGhosts");
		}
		if(description.getNearestEdibleGhostDist() < 150)
			return this.getAction("RunTowardsNearestGhost");
		if(description.getTimeEdibleLeft() <= 0 && description.getNearestNonEdibleGhostDist() != Integer.MAX_VALUE && description.getLeftPPills() > 0)
			return this.getAction("RunTowardsPowerPill");
		
		return this.getAction("RunTowardsPill");
	}

	/**
	 * Method called when the CBREngine has found a failed action. Simplest
	 * implementation returns another randomly.
	 * 
	 * @return
	 */
	public Action findAnotherAction(String failledAction) {
		int randomIndex = new Random().nextInt(actions.size());
		Action other = actions.get(randomIndex);
		if (other.getActionId().equals(failledAction))
			randomIndex = (randomIndex + 1) % actions.size();
		return actions.get(randomIndex);
	}

	public Action getAction(String action) {
		return map.get(action);
	}
}

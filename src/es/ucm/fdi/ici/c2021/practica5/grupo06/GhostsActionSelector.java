package es.ucm.fdi.ici.c2021.practica5.grupo06;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import es.ucm.fdi.ici.c2021.practica5.grupo06.CBRengine.GhostsDescription;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class GhostsActionSelector {
	HashMap<String, Action> map;
	List<Action> actions;
	Game game;
	private GHOST ghost;

	public GhostsActionSelector(List<Action> actions) {
		this.map = new HashMap<String, Action>();
		for (Action a : actions)
			map.put(a.getActionId(), a);
		this.actions = actions;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Action findAction(GhostsDescription description) {
		if (description != null) {

			boolean pacManCloseToPP = description.getDistanceToNearestPowerPillPacMan() < 30;
			
			// Blinky
			if (this.ghost.equals(GHOST.BLINKY)) {
				if ((description.getGhostEdibleBlinky() && description.getDistanceToPacmanBlinky() < 50) || pacManCloseToPP) {
					return this.getAction("RunAwayFromPacManAction action " + GHOST.BLINKY);
				}
				else if (description.getGhostEdibleBlinky() && description.getEdibleTimeLeftBlinky() < 20) {
					return this.getAction("ChasePacMan action " + GHOST.BLINKY);
				}
				if (!description.getGhostEdibleBlinky()) {
					return this.getAction("ChasePacMan action " + GHOST.BLINKY);
				}
			}
			
			// Pinky
			if (this.ghost.equals(GHOST.PINKY)) {
				if ((description.getGhostEdiblePinky() && description.getDistanceToPacmanPinky() < 50) || pacManCloseToPP) {
					return this.getAction("RunAwayFromPacManAction action " + GHOST.PINKY);
				}
				else if (description.getGhostEdiblePinky() && description.getEdibleTimeLeftPinky() < 20) {
					return this.getAction("ChasePacMan action " + GHOST.PINKY);
				}
				if (!description.getGhostEdiblePinky()) {
					return this.getAction("ChasePacMan action " + GHOST.PINKY);
				}
			}
			
			// Inky
			if (this.ghost.equals(GHOST.INKY)) {
				if ((description.getGhostEdibleInky() && description.getDistanceToPacmanInky() < 50) || pacManCloseToPP) {
					return this.getAction("RunAwayFromPacManAction action " + GHOST.INKY);
				}
				else if (description.getGhostEdibleInky() && description.getEdibleTimeLeftInky() < 20) {
					return this.getAction("ChasePacMan action " + GHOST.INKY);
				}
				if (!description.getGhostEdibleInky()) {
					return this.getAction("ChasePacMan action " + GHOST.INKY);
				}
			}
				
			// Sue
			if (this.ghost.equals(GHOST.SUE)) {
				if ((description.getGhostEdibleSue() && description.getDistanceToPacmanSue() < 50) || pacManCloseToPP) {
					return this.getAction("RunAwayFromPacManAction action " + GHOST.SUE);
				}
				else if (description.getGhostEdibleSue() && description.getEdibleTimeLeftSue() < 20) {
					return this.getAction("ChasePacMan action " + GHOST.SUE);
				}
				if (!description.getGhostEdibleSue()) {
					return this.getAction("ChasePacMan action " + GHOST.SUE);
				}
			}

		}

		int randomIndex = new Random().nextInt(actions.size());
		return actions.get(randomIndex);
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

	public void setGhost(GHOST ghost) {
		this.ghost = ghost;
	}
}

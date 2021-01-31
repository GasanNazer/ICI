package es.ucm.fdi.ici.c2021.practica5.grupo06;

import java.util.HashMap;

import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRQuery;
import es.ucm.fdi.ici.c2021.practica5.grupo06.CBRengine.GhostsDescription;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class GhostsInput implements Input {

	HashMap<GHOST, Integer> distanceToNearestPowerPill = new HashMap<GHOST, Integer>();
	HashMap<GHOST, Integer> positionOfNearestPowerPill = new HashMap<GHOST, Integer>();
	HashMap<GHOST, Integer> distanceToPacman = new HashMap<GHOST, Integer>();
	HashMap<GHOST, Boolean> ghostEdible = new HashMap<GHOST, Boolean>();
	Integer distanceToNearestPowerPillPacMan;
	Integer positionOfNearestPowerPillPacMan;
	/* BLINKY(40, "Blinky"),
    PINKY(60, "Pinky"),
    INKY(80, "Inky"),
    SUE(100, "Sue");
    0-> initial value
    */
	GHOST nearestGhostToPacMan;
	Integer distanceNearestGhostToPacMan;
	HashMap<GHOST, Integer> distanceToNearestGhost = new HashMap<GHOST, Integer>();
	HashMap<GHOST, Integer> positionOfNearestGhost = new HashMap<GHOST, Integer>();
	HashMap<GHOST, GHOST> nearestGhost = new HashMap<GHOST, GHOST>();
	HashMap<GHOST, Integer> distanceToNearestEdibleGhost = new HashMap<GHOST, Integer>(); 
	HashMap<GHOST, Integer> positionOfNearestEdibleGhost = new HashMap<GHOST, Integer>();
	HashMap<GHOST, GHOST> nearestEdibleGhost = new HashMap<GHOST, GHOST>();
	HashMap<GHOST, Integer> distanceToNearestNoEdibleGhost = new HashMap<GHOST, Integer>();
	HashMap<GHOST, Integer> positionOfNearestNoEdibleGhost = new HashMap<GHOST, Integer>();
	HashMap<GHOST, GHOST> nearestNoEdibleGhost = new HashMap<GHOST, GHOST>();
	// if the value is -1 the ghost is not edible
	HashMap<GHOST, Integer> edibleTimeLeft = new HashMap<GHOST, Integer>();
	// left-0,right-1, up-2, down-3, neutral-4
	HashMap<GHOST, Integer> directionLastMove = new HashMap<GHOST, Integer>();
	Integer numberOfPowerPillsLeft;
	Integer score;
	Integer time;

	@Override
	public void parseInput(Game game) {
		
		for(GHOST ghost : GHOST.values())
			isEdible(game, ghost);

		for (GHOST ghost : GHOST.values()) {
			computeNearestPPillForGhost(game, ghost);
			computeDistanceToPacMan(game, ghost);
			computeNearestGhost(game, ghost);
			computeNearestEdibleGhost(game, ghost);
			computeNearestNoEdibleGhost(game,ghost);
			computeEdibleTimeLeft(game, ghost);
			computeDirectionLastMove(game, ghost);
		}

		computeNearestGhostToPacMan(game);
		computeNearestPPill(game);
		this.numberOfPowerPillsLeft = game.getNumberOfActivePowerPills();
		this.time = game.getTotalTime();
		this.score = game.getScore();
	}

	@Override
	public CBRQuery getQuery() {
		GhostsDescription description = new GhostsDescription();
		
		for(GHOST ghost : GHOST.values()) {
			Integer distance = this.distanceToNearestPowerPill.get(ghost);
			Integer distancePacman = this.distanceToPacman.get(ghost);
			Boolean ghostEdible = this.ghostEdible.get(ghost);
			Integer distanceNearestGhost = this.distanceToNearestGhost.get(ghost);
			Integer distanceNearestEdibleGhost = this.distanceToNearestEdibleGhost.get(ghost);
			Integer distanceToNearestNoEdibleGhost = this.distanceToNearestNoEdibleGhost.get(ghost);
			Integer edibleTime = this.edibleTimeLeft.get(ghost);
			Integer move = this.directionLastMove.get(ghost);
			switch (ghost) {
			case BLINKY:
				description.setDistanceToNearestPowerPillBlinky(distance);
				description.setDistanceToPacmanBlinky(distancePacman);
				description.setGhostEdibleBlinky(ghostEdible);
				description.setDistanceToNearestGhostBlinky(distanceNearestGhost);
				description.setDistanceToNearestEdibleGhostBlinky(distanceNearestEdibleGhost);
				description.setDistanceToNearestNoEdibleGhostBlinky(distanceToNearestNoEdibleGhost);
				description.setEdibleTimeLeftBlinky(edibleTime);
				description.setDirectionLastMoveBlinky(move);
				break;
			case PINKY:
				description.setDistanceToNearestPowerPillPinky(distance);
				description.setDistanceToPacmanPinky(distancePacman);
				description.setGhostEdiblePinky(ghostEdible);
				description.setDistanceToNearestGhostPinky(distanceNearestGhost);
				description.setDistanceToNearestEdibleGhostPinky(distanceNearestEdibleGhost);
				description.setDistanceToNearestNoEdibleGhostPinky(distanceToNearestNoEdibleGhost);
				description.setEdibleTimeLeftPinky(edibleTime);
				description.setDirectionLastMovePinky(move);
				break;
			case INKY:
				description.setDistanceToNearestPowerPillInky(distance);
				description.setDistanceToPacmanInky(distancePacman);
				description.setGhostEdibleInky(ghostEdible);
				description.setDistanceToNearestGhostInky(distanceNearestGhost);
				description.setDistanceToNearestEdibleGhostInky(distanceNearestEdibleGhost);
				description.setDistanceToNearestNoEdibleGhostInky(distanceToNearestNoEdibleGhost);
				description.setEdibleTimeLeftInky(edibleTime);
				description.setDirectionLastMovePinky(move);
				break;
			case SUE:
				description.setDistanceToNearestPowerPillSue(distance);
				description.setDistanceToPacmanSue(distancePacman);
				description.setGhostEdibleSue(ghostEdible);
				description.setDistanceToNearestGhostSue(distanceNearestGhost);
				description.setDistanceToNearestEdibleGhostSue(distanceNearestEdibleGhost);
				description.setDistanceToNearestNoEdibleGhostSue(distanceToNearestNoEdibleGhost);
				description.setEdibleTimeLeftSue(edibleTime);
				description.setDirectionLastMoveSue(move);
				break;
			}
		}
		
		/*
		description.setPositionOfNearestPowerPill(positionOfNearestPowerPill); Is it really needed?
		*/
		
		description.setDistanceToNearestPowerPillPacMan(distanceToNearestPowerPillPacMan);
		description.setPositionOfNearestPowerPillPacMan(positionOfNearestPowerPillPacMan);
		
		int nearestGhostPacMan = 0;
		switch(this.nearestGhostToPacMan) {
		case BLINKY:
			nearestGhostPacMan = 40;
			break;
		case PINKY:
			nearestGhostPacMan = 60;
			break;
		case INKY:
			nearestGhostPacMan = 80;
			break;
		case SUE:
			nearestGhostPacMan = 100;
			break;
		}
		description.setNearestGhostToPacMan(nearestGhostPacMan);
		description.setDistanceNearestGhostToPacMan(distanceNearestGhostToPacMan);
		
		
		//description.setPositionOfNearestGhost(positionOfNearestGhost); Is it really needed?
		//description.setNearestGhost(nearestGhost); Is it really needed?
		//description.setPositionOfNearestEdibleGhost(positionOfNearestEdibleGhost);  Is it really needed?
		//description.setNearestEdibleGhost(nearestEdibleGhost);  Is it really needed?
		//description.setPositionOfNearestNoEdibleGhost(positionOfNearestNoEdibleGhost); Is it really needed?
		//description.setNearestNoEdibleGhost(nearestNoEdibleGhost); Is it really needed?
		
		description.setNumberOfPowerPillsLeft(numberOfPowerPillsLeft);
	
		description.setScore(score);
		description.setTime(time);
		
		CBRQuery query = new CBRQuery();
		query.setDescription(description);
		return query;
	}

	private void computeNearestPPill(Game game) {
		this.distanceToNearestPowerPillPacMan = Integer.MAX_VALUE;
		this.positionOfNearestPowerPillPacMan = -1;
		for (int pos : game.getPowerPillIndices()) {
			int distance = (int) game.getDistance(game.getPacmanCurrentNodeIndex(), pos, DM.PATH);
			if (distance < this.distanceNearestGhostToPacMan) {
				this.distanceToNearestPowerPillPacMan = distance;
				this.positionOfNearestPowerPillPacMan = pos;
			}
		}
	}

	private void computeNearestPPillForGhost(Game game, GHOST ghost) {
		int nearestPPill = Integer.MAX_VALUE;
		int positon = -1;
		for (int pos : game.getPowerPillIndices()) {
			int distance = (int) game.getDistance(game.getGhostCurrentNodeIndex(ghost), pos, DM.PATH);
			if (distance < nearestPPill) {
				nearestPPill = distance;
				positon = pos;
			}
		}

		this.distanceToNearestPowerPill.put(ghost, nearestPPill);
		this.positionOfNearestPowerPill.put(ghost, positon);
	}

	private void computeDistanceToPacMan(Game game, GHOST ghost) {
		int distance = (int) game.getDistance(game.getGhostCurrentNodeIndex(ghost), game.getPacmanCurrentNodeIndex(),
				DM.PATH);
		this.distanceToPacman.put(ghost, distance);
	}

	private void isEdible(Game game, GHOST ghost) {
		this.ghostEdible.put(ghost, game.isGhostEdible(ghost));
	}

	private void computeNearestGhostToPacMan(Game game) {
		this.nearestGhostToPacMan = GHOST.BLINKY;
		this.distanceNearestGhostToPacMan = this.distanceToPacman.get(this.nearestGhostToPacMan);

		int distance;
		for (GHOST ghost : GHOST.values()) {
			distance = this.distanceToPacman.get(ghost);
			if (distance < this.distanceNearestGhostToPacMan) {
				this.nearestGhostToPacMan = ghost;
				this.distanceNearestGhostToPacMan = this.distanceToPacman.get(this.nearestGhostToPacMan);
			}
		}
	}

	private void computeNearestGhost(Game game, GHOST ghost) {
		int distance = Integer.MAX_VALUE, current_distance;
		GHOST nearestGhost = ghost;
		for (GHOST g : GHOST.values()) {
			if (!g.equals(ghost)) {
				current_distance = (int) game.getDistance(game.getGhostCurrentNodeIndex(ghost),
						game.getGhostCurrentNodeIndex(g), DM.PATH);
				if (distance < current_distance) {
					distance = current_distance;
					nearestGhost = g;
				}
			}
		}
		this.distanceToNearestGhost.put(ghost, distance);
		this.positionOfNearestGhost.put(ghost, game.getGhostCurrentNodeIndex(nearestGhost));
		this.nearestGhost.put(ghost, nearestGhost);
	}

	private void computeNearestEdibleGhost(Game game, GHOST ghost) {
		int current_distance = Integer.MAX_VALUE, distance = current_distance;
		GHOST nearestEdibleGhost = ghost;
		for (GHOST g : this.ghostEdible.keySet()) {
			if (!g.equals(ghost)) {
				if (this.ghostEdible.get(g)) {
					current_distance = (int) game.getDistance(game.getGhostCurrentNodeIndex(ghost),
							game.getGhostCurrentNodeIndex(g), DM.PATH);
					if (distance < current_distance) {
						distance = current_distance;
						nearestEdibleGhost = g;
					}
				}
			}
		}
		this.distanceToNearestEdibleGhost.put(ghost, distance);
		this.positionOfNearestEdibleGhost.put(ghost, game.getGhostCurrentNodeIndex(nearestEdibleGhost));
		this.nearestEdibleGhost.put(ghost, nearestEdibleGhost);
	}

	private void computeEdibleTimeLeft(Game game, GHOST ghost) {
		this.edibleTimeLeft.put(ghost, this.ghostEdible.get(ghost) ? game.getGhostEdibleTime(ghost) : -1);
	}

	private void computeDirectionLastMove(Game game, GHOST ghost) {
		// left-0,right-1, up-2, down-3

		switch (game.getGhostLastMoveMade(ghost)) {
		case LEFT:
			this.directionLastMove.put(ghost, 0);
			break;
		case RIGHT:
			this.directionLastMove.put(ghost, 1);
			break;
		case UP:
			this.directionLastMove.put(ghost, 2);
			break;
		case DOWN:
			this.directionLastMove.put(ghost, 3);
			break;
		default:
			this.directionLastMove.put(ghost, 4);
			break;
		}
	}
	
	private void computeNearestNoEdibleGhost(Game game, GHOST ghost) {
		int current_distance = Integer.MAX_VALUE, distance = current_distance;
		GHOST nearestNoEdibleGhost = ghost;
		for (GHOST g : GHOST.values()) {
			if (!g.equals(ghost)) {
				if (!this.ghostEdible.get(g)) {
					current_distance = (int) game.getDistance(game.getGhostCurrentNodeIndex(ghost),
							game.getGhostCurrentNodeIndex(g), DM.PATH);
					if (distance < current_distance) {
						distance = current_distance;
						nearestNoEdibleGhost = g;
					}
				}
			}
		}
		this.distanceToNearestNoEdibleGhost.put(ghost, distance);
		this.positionOfNearestNoEdibleGhost.put(ghost, game.getGhostCurrentNodeIndex(nearestNoEdibleGhost));
		this.nearestNoEdibleGhost.put(ghost, nearestNoEdibleGhost);
		
	}
}

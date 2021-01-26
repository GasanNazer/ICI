package es.ucm.fdi.ici.c2021.practica5.grupo06.CBRengine;

import java.util.HashMap;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;
import pacman.game.Constants.GHOST;

public class GhostsDescription implements CaseComponent {

	Integer id;
	
	//HashMap<GHOST, Integer> distanceToNearestPowerPill;
	//HashMap<GHOST, Integer> positionOfNearestPowerPill;
	//HashMap<GHOST, Integer> distanceToPacman;
	//HashMap<GHOST, Boolean> ghostEdible;
	Integer distanceToNearestPowerPillPacMan;
	Integer positionOfNearestPowerPillPacMan;
	//GHOST nearestGhostToPacMan;
	Integer distanceNearestGhostToPacMan;
	//HashMap<GHOST, Integer> distanceToNearestGhost;
	//HashMap<GHOST, Integer> positionOfNearestGhost;
	//HashMap<GHOST, GHOST> nearestGhost;
	//HashMap<GHOST, Integer> distanceToNearestEdibleGhost;
	//HashMap<GHOST, Integer> positionOfNearestEdibleGhost;
	//HashMap<GHOST, GHOST> nearestEdibleGhost;
	//HashMap<GHOST, Integer> distanceToNearestNoEdibleGhost;
	//HashMap<GHOST, Integer> positionOfNearestNoEdibleGhost;
	//HashMap<GHOST, GHOST> nearestNoEdibleGhost;
	// if the value is -1 the ghost is not edible
	//HashMap<GHOST, Integer> edibleTimeLeft;
	// left-0,right-1, up-2, down-3
	//HashMap<GHOST, Integer> directionLastMove;
	Integer numberOfPowerPillsLeft;
	Integer score;
	Integer time;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDistanceToNearestPowerPillPacMan() {
		return distanceToNearestPowerPillPacMan;
	}

	public void setDistanceToNearestPowerPillPacMan(Integer distanceToNearestPowerPillPacMan) {
		this.distanceToNearestPowerPillPacMan = distanceToNearestPowerPillPacMan;
	}

	public Integer getPositionOfNearestPowerPillPacMan() {
		return positionOfNearestPowerPillPacMan;
	}

	public void setPositionOfNearestPowerPillPacMan(Integer positionOfNearestPowerPillPacMan) {
		this.positionOfNearestPowerPillPacMan = positionOfNearestPowerPillPacMan;
	}

	public Integer getDistanceNearestGhostToPacMan() {
		return distanceNearestGhostToPacMan;
	}

	public void setDistanceNearestGhostToPacMan(Integer distanceNearestGhostToPacMan) {
		this.distanceNearestGhostToPacMan = distanceNearestGhostToPacMan;
	}

	public Integer getNumberOfPowerPillsLeft() {
		return numberOfPowerPillsLeft;
	}

	public void setNumberOfPowerPillsLeft(Integer numberOfPowerPillsLeft) {
		this.numberOfPowerPillsLeft = numberOfPowerPillsLeft;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	@Override
	public Attribute getIdAttribute() {
		return new Attribute("id", GhostsDescription.class);
	}

	@Override
	public String toString() {
		return "GhostsDescription [id=" + id + ", distanceToNearestPowerPillPacMan=" + distanceToNearestPowerPillPacMan
				+ ", positionOfNearestPowerPillPacMan=" + positionOfNearestPowerPillPacMan
				+ ", distanceNearestGhostToPacMan=" + distanceNearestGhostToPacMan + ", numberOfPowerPillsLeft="
				+ numberOfPowerPillsLeft + ", score=" + score + ", time=" + time + "]";
	}


}

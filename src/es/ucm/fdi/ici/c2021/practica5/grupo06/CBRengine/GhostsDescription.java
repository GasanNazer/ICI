package es.ucm.fdi.ici.c2021.practica5.grupo06.CBRengine;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class GhostsDescription implements CaseComponent {

	Integer id;

	// HashMap<GHOST, Integereger> distanceToNearestPowerPill;
	Integer distanceToNearestPowerPillBlinky;
	Integer distanceToNearestPowerPillPinky;
	Integer distanceToNearestPowerPillInky;
	Integer distanceToNearestPowerPillSue;
	
	// It may not be needed
	// HashMap<GHOST, Integereger> positionOfNearestPowerPill;
	
	// HashMap<GHOST, Integereger> distanceToPacman;
	Integer distanceToPacmanBlinky;
	Integer distanceToPacmanPinky;
	Integer distanceToPacmanInky;
	Integer distanceToPacmanSue;
	
	// HashMap<GHOST, boolean> ghostEdible;
	Boolean ghostEdibleBlinky;
	Boolean ghostEdiblePinky;
	Boolean ghostEdibleInky;
	Boolean ghostEdibleSue;
	
	Integer distanceToNearestPowerPillPacMan;
	Integer positionOfNearestPowerPillPacMan;
	Integer nearestGhostToPacMan;
	Integer distanceNearestGhostToPacMan;
	// HashMap<GHOST, Integereger> distanceToNearestGhost;
	Integer distanceToNearestGhostBlinky;
	Integer distanceToNearestGhostPinky;
	Integer distanceToNearestGhostInky;
	Integer distanceToNearestGhostSue;
	
	// It may not be needed
	// HashMap<GHOST, Integereger> positionOfNearestGhost;
	// HashMap<GHOST, GHOST> nearestGhost;
	
	// HashMap<GHOST, Integereger> distanceToNearestEdibleGhost;
	Integer distanceToNearestEdibleGhostBlinky;
	Integer distanceToNearestEdibleGhostPinky;
	Integer distanceToNearestEdibleGhostInky;
	Integer distanceToNearestEdibleGhostSue;
	
	// It may not be needed
	// HashMap<GHOST, Integereger> positionOfNearestEdibleGhost;
	// HashMap<GHOST, GHOST> nearestEdibleGhost;
	
	// HashMap<GHOST, Integereger> distanceToNearestNoEdibleGhost;
	Integer distanceToNearestNoEdibleGhostBlinky;
	Integer distanceToNearestNoEdibleGhostPinky;
	Integer distanceToNearestNoEdibleGhostInky;
	Integer distanceToNearestNoEdibleGhostSue;
	
	
	// It may not be needed
	// HashMap<GHOST, Integereger> positionOfNearestNoEdibleGhost;
	// HashMap<GHOST, GHOST> nearestNoEdibleGhost;
	
	// if the value is -1 the ghost is not edible
	// HashMap<GHOST, Integereger> edibleTimeLeft;
	Integer edibleTimeLeftBlinky;
	Integer edibleTimeLeftPinky;
	Integer edibleTimeLeftInky;
	Integer edibleTimeLeftSue;
	
	
	// left-0,right-1, up-2, down-3, neutral-4
	// HashMap<GHOST, Integereger> directionLastMove;
	Integer directionLastMoveBlinky;
	Integer directionLastMovePinky;
	Integer directionLastMoveInky;
	Integer directionLastMoveSue;
	
	Integer numberOfPowerPillsLeft;
	

	Integer score;
	Integer time;
	
	@Override
	public Attribute getIdAttribute() {
		return new Attribute("id", GhostsDescription.class);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDistanceToNearestPowerPillBlinky() {
		return distanceToNearestPowerPillBlinky;
	}

	public void setDistanceToNearestPowerPillBlinky(Integer distanceToNearestPowerPillBlinky) {
		this.distanceToNearestPowerPillBlinky = distanceToNearestPowerPillBlinky;
	}

	public Integer getDistanceToNearestPowerPillPinky() {
		return distanceToNearestPowerPillPinky;
	}

	public void setDistanceToNearestPowerPillPinky(Integer distanceToNearestPowerPillPinky) {
		this.distanceToNearestPowerPillPinky = distanceToNearestPowerPillPinky;
	}

	public Integer getDistanceToNearestPowerPillInky() {
		return distanceToNearestPowerPillInky;
	}

	public void setDistanceToNearestPowerPillInky(Integer distanceToNearestPowerPillInky) {
		this.distanceToNearestPowerPillInky = distanceToNearestPowerPillInky;
	}

	public Integer getDistanceToNearestPowerPillSue() {
		return distanceToNearestPowerPillSue;
	}

	public void setDistanceToNearestPowerPillSue(Integer distanceToNearestPowerPillSue) {
		this.distanceToNearestPowerPillSue = distanceToNearestPowerPillSue;
	}

	public Integer getDistanceToPacmanBlinky() {
		return distanceToPacmanBlinky;
	}

	public void setDistanceToPacmanBlinky(Integer distanceToPacmanBlinky) {
		this.distanceToPacmanBlinky = distanceToPacmanBlinky;
	}

	public Integer getDistanceToPacmanPinky() {
		return distanceToPacmanPinky;
	}

	public void setDistanceToPacmanPinky(Integer distanceToPacmanPinky) {
		this.distanceToPacmanPinky = distanceToPacmanPinky;
	}

	public Integer getDistanceToPacmanInky() {
		return distanceToPacmanInky;
	}

	public void setDistanceToPacmanInky(Integer distanceToPacmanInky) {
		this.distanceToPacmanInky = distanceToPacmanInky;
	}

	public Integer getDistanceToPacmanSue() {
		return distanceToPacmanSue;
	}

	public void setDistanceToPacmanSue(Integer distanceToPacmanSue) {
		this.distanceToPacmanSue = distanceToPacmanSue;
	}

	public Boolean getGhostEdibleBlinky() {
		return ghostEdibleBlinky;
	}

	public void setGhostEdibleBlinky(Boolean ghostEdibleBlinky) {
		this.ghostEdibleBlinky = ghostEdibleBlinky;
	}

	public Boolean getGhostEdiblePinky() {
		return ghostEdiblePinky;
	}

	public void setGhostEdiblePinky(Boolean ghostEdiblePinky) {
		this.ghostEdiblePinky = ghostEdiblePinky;
	}

	public Boolean getGhostEdibleInky() {
		return ghostEdibleInky;
	}

	public void setGhostEdibleInky(Boolean ghostEdibleInky) {
		this.ghostEdibleInky = ghostEdibleInky;
	}

	public Boolean getGhostEdibleSue() {
		return ghostEdibleSue;
	}

	public void setGhostEdibleSue(Boolean ghostEdibleSue) {
		this.ghostEdibleSue = ghostEdibleSue;
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

	public Integer getNearestGhostToPacMan() {
		return nearestGhostToPacMan;
	}

	public void setNearestGhostToPacMan(Integer nearestGhostToPacMan) {
		this.nearestGhostToPacMan = nearestGhostToPacMan;
	}

	public Integer getDistanceNearestGhostToPacMan() {
		return distanceNearestGhostToPacMan;
	}

	public void setDistanceNearestGhostToPacMan(Integer distanceNearestGhostToPacMan) {
		this.distanceNearestGhostToPacMan = distanceNearestGhostToPacMan;
	}

	public Integer getDistanceToNearestGhostBlinky() {
		return distanceToNearestGhostBlinky;
	}

	public void setDistanceToNearestGhostBlinky(Integer distanceToNearestGhostBlinky) {
		this.distanceToNearestGhostBlinky = distanceToNearestGhostBlinky;
	}

	public Integer getDistanceToNearestGhostPinky() {
		return distanceToNearestGhostPinky;
	}

	public void setDistanceToNearestGhostPinky(Integer distanceToNearestGhostPinky) {
		this.distanceToNearestGhostPinky = distanceToNearestGhostPinky;
	}

	public Integer getDistanceToNearestGhostInky() {
		return distanceToNearestGhostInky;
	}

	public void setDistanceToNearestGhostInky(Integer distanceToNearestGhostInky) {
		this.distanceToNearestGhostInky = distanceToNearestGhostInky;
	}

	public Integer getDistanceToNearestGhostSue() {
		return distanceToNearestGhostSue;
	}

	public void setDistanceToNearestGhostSue(Integer distanceToNearestGhostSue) {
		this.distanceToNearestGhostSue = distanceToNearestGhostSue;
	}

	public Integer getDistanceToNearestEdibleGhostBlinky() {
		return distanceToNearestEdibleGhostBlinky;
	}

	public void setDistanceToNearestEdibleGhostBlinky(Integer distanceToNearestEdibleGhostBlinky) {
		this.distanceToNearestEdibleGhostBlinky = distanceToNearestEdibleGhostBlinky;
	}

	public Integer getDistanceToNearestEdibleGhostPinky() {
		return distanceToNearestEdibleGhostPinky;
	}

	public void setDistanceToNearestEdibleGhostPinky(Integer distanceToNearestEdibleGhostPinky) {
		this.distanceToNearestEdibleGhostPinky = distanceToNearestEdibleGhostPinky;
	}

	public Integer getDistanceToNearestEdibleGhostInky() {
		return distanceToNearestEdibleGhostInky;
	}

	public void setDistanceToNearestEdibleGhostInky(Integer distanceToNearestEdibleGhostInky) {
		this.distanceToNearestEdibleGhostInky = distanceToNearestEdibleGhostInky;
	}

	public Integer getDistanceToNearestEdibleGhostSue() {
		return distanceToNearestEdibleGhostSue;
	}

	public void setDistanceToNearestEdibleGhostSue(Integer distanceToNearestEdibleGhostSue) {
		this.distanceToNearestEdibleGhostSue = distanceToNearestEdibleGhostSue;
	}

	public Integer getDistanceToNearestNoEdibleGhostBlinky() {
		return distanceToNearestNoEdibleGhostBlinky;
	}

	public void setDistanceToNearestNoEdibleGhostBlinky(Integer distanceToNearestNoEdibleGhostBlinky) {
		this.distanceToNearestNoEdibleGhostBlinky = distanceToNearestNoEdibleGhostBlinky;
	}

	public Integer getDistanceToNearestNoEdibleGhostPinky() {
		return distanceToNearestNoEdibleGhostPinky;
	}

	public void setDistanceToNearestNoEdibleGhostPinky(Integer distanceToNearestNoEdibleGhostPinky) {
		this.distanceToNearestNoEdibleGhostPinky = distanceToNearestNoEdibleGhostPinky;
	}

	public Integer getDistanceToNearestNoEdibleGhostInky() {
		return distanceToNearestNoEdibleGhostInky;
	}

	public void setDistanceToNearestNoEdibleGhostInky(Integer distanceToNearestNoEdibleGhostInky) {
		this.distanceToNearestNoEdibleGhostInky = distanceToNearestNoEdibleGhostInky;
	}

	public Integer getDistanceToNearestNoEdibleGhostSue() {
		return distanceToNearestNoEdibleGhostSue;
	}

	public void setDistanceToNearestNoEdibleGhostSue(Integer distanceToNearestNoEdibleGhostSue) {
		this.distanceToNearestNoEdibleGhostSue = distanceToNearestNoEdibleGhostSue;
	}

	public Integer getEdibleTimeLeftBlinky() {
		return edibleTimeLeftBlinky;
	}

	public void setEdibleTimeLeftBlinky(Integer edibleTimeLeftBlinky) {
		this.edibleTimeLeftBlinky = edibleTimeLeftBlinky;
	}

	public Integer getEdibleTimeLeftPinky() {
		return edibleTimeLeftPinky;
	}

	public void setEdibleTimeLeftPinky(Integer edibleTimeLeftPinky) {
		this.edibleTimeLeftPinky = edibleTimeLeftPinky;
	}

	public Integer getEdibleTimeLeftInky() {
		return edibleTimeLeftInky;
	}

	public void setEdibleTimeLeftInky(Integer edibleTimeLeftInky) {
		this.edibleTimeLeftInky = edibleTimeLeftInky;
	}

	public Integer getEdibleTimeLeftSue() {
		return edibleTimeLeftSue;
	}

	public void setEdibleTimeLeftSue(Integer edibleTimeLeftSue) {
		this.edibleTimeLeftSue = edibleTimeLeftSue;
	}

	public Integer getDirectionLastMoveBlinky() {
		return directionLastMoveBlinky;
	}

	public void setDirectionLastMoveBlinky(Integer directionLastMoveBlinky) {
		this.directionLastMoveBlinky = directionLastMoveBlinky;
	}

	public Integer getDirectionLastMovePinky() {
		return directionLastMovePinky;
	}

	public void setDirectionLastMovePinky(Integer directionLastMovePinky) {
		this.directionLastMovePinky = directionLastMovePinky;
	}

	public Integer getDirectionLastMoveInky() {
		return directionLastMoveInky;
	}

	public void setDirectionLastMoveInky(Integer directionLastMoveInky) {
		this.directionLastMoveInky = directionLastMoveInky;
	}

	public Integer getDirectionLastMoveSue() {
		return directionLastMoveSue;
	}

	public void setDirectionLastMoveSue(Integer directionLastMoveSue) {
		this.directionLastMoveSue = directionLastMoveSue;
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
	public String toString() {
		return "GhostsDescription [id=" + id + ", distanceToNearestPowerPillBlinky=" + distanceToNearestPowerPillBlinky
				+ ", distanceToNearestPowerPillPinky=" + distanceToNearestPowerPillPinky
				+ ", distanceToNearestPowerPillInky=" + distanceToNearestPowerPillInky
				+ ", distanceToNearestPowerPillSue=" + distanceToNearestPowerPillSue + ", distanceToPacmanBlinky="
				+ distanceToPacmanBlinky + ", distanceToPacmanPinky=" + distanceToPacmanPinky
				+ ", distanceToPacmanInky=" + distanceToPacmanInky + ", distanceToPacmanSue=" + distanceToPacmanSue
				+ ", ghostEdibleBlinky=" + ghostEdibleBlinky + ", ghostEdiblePinky=" + ghostEdiblePinky
				+ ", ghostEdibleInky=" + ghostEdibleInky + ", ghostEdibleSue=" + ghostEdibleSue
				+ ", distanceToNearestPowerPillPacMan=" + distanceToNearestPowerPillPacMan
				+ ", positionOfNearestPowerPillPacMan=" + positionOfNearestPowerPillPacMan + ", nearestGhostToPacMan="
				+ nearestGhostToPacMan + ", distanceNearestGhostToPacMan=" + distanceNearestGhostToPacMan
				+ ", distanceToNearestGhostBlinky=" + distanceToNearestGhostBlinky + ", distanceToNearestGhostPinky="
				+ distanceToNearestGhostPinky + ", distanceToNearestGhostInky=" + distanceToNearestGhostInky
				+ ", distanceToNearestGhostSue=" + distanceToNearestGhostSue + ", distanceToNearestEdibleGhostBlinky="
				+ distanceToNearestEdibleGhostBlinky + ", distanceToNearestEdibleGhostPinky="
				+ distanceToNearestEdibleGhostPinky + ", distanceToNearestEdibleGhostInky="
				+ distanceToNearestEdibleGhostInky + ", distanceToNearestEdibleGhostSue="
				+ distanceToNearestEdibleGhostSue + ", distanceToNearestNoEdibleGhostBlinky="
				+ distanceToNearestNoEdibleGhostBlinky + ", distanceToNearestNoEdibleGhostPinky="
				+ distanceToNearestNoEdibleGhostPinky + ", distanceToNearestNoEdibleGhostInky="
				+ distanceToNearestNoEdibleGhostInky + ", distanceToNearestNoEdibleGhostSue="
				+ distanceToNearestNoEdibleGhostSue + ", edibleTimeLeftBlinky=" + edibleTimeLeftBlinky
				+ ", edibleTimeLeftPinky=" + edibleTimeLeftPinky + ", edibleTimeLeftInky=" + edibleTimeLeftInky
				+ ", edibleTimeLeftSue=" + edibleTimeLeftSue + ", directionLastMoveBlinky=" + directionLastMoveBlinky
				+ ", directionLastMovePinky=" + directionLastMovePinky + ", directionLastMoveInky="
				+ directionLastMoveInky + ", directionLastMoveSue=" + directionLastMoveSue + ", numberOfPowerPillsLeft="
				+ numberOfPowerPillsLeft + ", score=" + score + ", time=" + time + "]";
	}
}

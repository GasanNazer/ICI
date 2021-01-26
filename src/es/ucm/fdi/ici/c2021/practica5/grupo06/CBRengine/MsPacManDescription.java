package es.ucm.fdi.ici.c2021.practica5.grupo06.CBRengine;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class MsPacManDescription implements CaseComponent {

	Integer id;
	Integer score;
	Integer time;
	Integer nearestPPill;
	Integer nearestNonEdibleGhostDist;
	Integer nearestEdibleGhostDist;
	Boolean edibleGhost;
	Integer timeEdibleLeft;
	Integer nearestPill;
	Integer leftPills;
	Integer leftPPills;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getNearestPPill() {
		return nearestPPill;
	}

	public void setNearestPPill(Integer nearestPPill) {
		this.nearestPPill = nearestPPill;
	}

	public Boolean getEdibleGhost() {
		return edibleGhost;
	}

	public void setEdibleGhost(Boolean edibleGhost) {
		this.edibleGhost = edibleGhost;
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

	public Attribute getIdAttribute() {
		return new Attribute("id", MsPacManDescription.class);
	}



	public Integer getTimeEdibleLeft() {
		return timeEdibleLeft;
	}

	public void setTimeEdibleLeft(Integer timeEdibleLeft) {
		this.timeEdibleLeft = timeEdibleLeft;
	}

	public Integer getNearestPill() {
		return nearestPill;
	}

	public void setNearestPill(Integer nearestPill) {
		this.nearestPill = nearestPill;
	}

	public Integer getLeftPills() {
		return leftPills;
	}

	public void setLeftPills(Integer leftPills) {
		this.leftPills = leftPills;
	}

	public Integer getLeftPPills() {
		return leftPPills;
	}

	public void setLeftPPills(Integer leftPPills) {
		this.leftPPills = leftPPills;
	}

	public Integer getNearestNonEdibleGhostDist() {
		return nearestNonEdibleGhostDist;
	}

	public void setNearestNonEdibleGhostDist(Integer nearestNonEdibleGhostDist) {
		this.nearestNonEdibleGhostDist = nearestNonEdibleGhostDist;
	}

	public Integer getNearestEdibleGhostDist() {
		return nearestEdibleGhostDist;
	}

	public void setNearestEdibleGhostDist(Integer nearestEdibleGhostDist) {
		this.nearestEdibleGhostDist = nearestEdibleGhostDist;
	}
	
	@Override
	public String toString() {
		return "MsPacManDescription [id=" + id + ", score=" + score + ", time=" + time + ", nearestPPill="
				+ nearestPPill + ", nearestNonEdibleGhostDist=" + nearestNonEdibleGhostDist
				+ ", nearestEdibleGhostDist=" + nearestEdibleGhostDist + ", edibleGhost=" + edibleGhost
				+ ", timeEdibleLeft=" + timeEdibleLeft + ", nearestPill=" + nearestPill + ", leftPills=" + leftPills
				+ ", leftPPills=" + leftPPills + "]";
	}

	
	
}

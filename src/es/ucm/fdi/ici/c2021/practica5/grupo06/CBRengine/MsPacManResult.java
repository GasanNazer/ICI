package es.ucm.fdi.ici.c2021.practica5.grupo06.CBRengine;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class MsPacManResult implements CaseComponent, Cloneable {

	Integer id;
	Integer score;
	Integer time;
	Integer level;
	Integer eatenPills;
	Integer eatenPPills;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getEatenPills() {
		return eatenPills;
	}

	public void setEatenPills(Integer eatenPills) {
		this.eatenPills = eatenPills;
	}

	public Integer getEatenPPills() {
		return eatenPPills;
	}

	public void setEatenPPills(Integer eatenPPills) {
		this.eatenPPills = eatenPPills;
	} 
	
	
	@Override
	public Attribute getIdAttribute() {
		return new Attribute("id", MsPacManResult.class);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{  
		return super.clone();  
	}

	@Override
	public String toString() {
		return "MsPacManResult [id=" + id + ", score=" + score + ", time=" + time
				+ ", level=" + level + ", eatenPills=" + eatenPills + ", eatenPPills=" + eatenPPills + "]";
	}

}

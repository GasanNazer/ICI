package es.ucm.fdi.ici.c2021.practica5.grupo06.CBRengine;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class GhostsResult implements CaseComponent, Cloneable {

	Integer id;
	Integer score;
	
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

	@Override
	public Attribute getIdAttribute() {
		return new Attribute("id", GhostsResult.class);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{  
		return super.clone();  
	}

	@Override
	public String toString() {
		return "GhostsResult [id=" + id + ", score=" + score + "]";
	} 
	
	

}

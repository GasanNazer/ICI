package es.ucm.fdi.ici.c2021.practica5.grupo06.CBRengine;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class GhostsSolution implements CaseComponent, Cloneable {
	Integer id;
	String action;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	@Override
	public Attribute getIdAttribute() {
		return new Attribute("id", GhostsSolution.class);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{  
		return super.clone();  
	}
	@Override
	public String toString() {
		return "GhostsSolution [id=" + id + ", action=" + action + "]";
	}  
	
	
	
}

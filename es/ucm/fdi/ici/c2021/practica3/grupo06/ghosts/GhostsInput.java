package es.ucm.fdi.ici.c2021.practica3.grupo06.ghosts;

import java.util.Collection;
import java.util.Vector;

import es.ucm.fdi.ici.rules.Input;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class GhostsInput extends Input {

	private boolean BLINKYedible;
	private boolean INKYedible;
	private boolean PINKYedible;
	private boolean SUEedible;
	private double minPacmanDistancePPill;
	private boolean wasPacManEaten = false;
	
	private boolean BLINKYcloseToOthers;
	private boolean INKYcloseToOthers;
	private boolean PINKYcloseToOthers;
	private boolean SUEcloseToOthers;
	
	private boolean BLINKYfarFromOthers;
	private boolean INKYfarFromOthers;
	private boolean PINKYfarFromOthers;
	private boolean SUEfarFromOthers;
	
	private boolean BLINKYcloseToPacMan;
	private boolean INKYcloseToPacMan;
	private boolean PINKYcloseToPacMan;
	private boolean SUEcloseToPacMan;
	
	private boolean BLINKYendOFEdible;
	private boolean INKYendOFEdible;
	private boolean PINKYendOFEdible;
	private boolean SUEendOFEdible;
	
	private final static int LIMIT = 30;
	private final static int GHOST_EATABLE_TIME_LIMIT = 30;
	private final static int DISTANCE_TO_PACMAN = 100;
	
	public GhostsInput(Game game) {
		super(game);
	}

	@Override
	public void parseInput() {
		this.BLINKYedible = game.isGhostEdible(GHOST.BLINKY);
		this.INKYedible = game.isGhostEdible(GHOST.INKY);
		this.PINKYedible = game.isGhostEdible(GHOST.PINKY);
		this.SUEedible = game.isGhostEdible(GHOST.SUE);
	
		int pacman = game.getPacmanCurrentNodeIndex();
		this.minPacmanDistancePPill = Double.MAX_VALUE;
		for(int ppill: game.getActivePowerPillsIndices()) {
			double distance = game.getDistance(pacman, ppill, DM.PATH);
			this.minPacmanDistancePPill = Math.min(distance, this.minPacmanDistancePPill);
		}
		
		this.wasPacManEaten = this.game.wasPacManEaten();
		
		this.BLINKYcloseToOthers = this.ghostCloseToOthers(GHOST.BLINKY);
		this.INKYcloseToOthers = this.ghostCloseToOthers(GHOST.INKY);
		this.PINKYcloseToOthers = this.ghostCloseToOthers(GHOST.PINKY);
		this.SUEcloseToOthers = this.ghostCloseToOthers(GHOST.SUE);
		
		this.BLINKYfarFromOthers = this.ghostFarFromOthers(GHOST.BLINKY);
		this.INKYfarFromOthers = this.ghostFarFromOthers(GHOST.INKY);
		this.PINKYfarFromOthers = this.ghostFarFromOthers(GHOST.PINKY);
		this.SUEfarFromOthers = this.ghostFarFromOthers(GHOST.SUE);
		
		this.BLINKYcloseToPacMan = this.ghostCloseToPacMan(GHOST.BLINKY);
		this.INKYcloseToPacMan = this.ghostCloseToPacMan(GHOST.INKY);
		this.PINKYcloseToPacMan = this.ghostCloseToPacMan(GHOST.PINKY);
		this.SUEcloseToPacMan = this.ghostCloseToPacMan(GHOST.SUE);
		
		this.BLINKYendOFEdible = this.ghostEndOfEdible(GHOST.BLINKY);
		this.INKYendOFEdible = this.ghostEndOfEdible(GHOST.INKY);
		this.PINKYendOFEdible = this.ghostEndOfEdible(GHOST.PINKY);
		this.SUEendOFEdible = this.ghostEndOfEdible(GHOST.SUE);
	}

	public boolean isBLINKYedible() {
		return BLINKYedible;
	}

	public boolean isINKYedible() {
		return INKYedible;
	}

	public boolean isPINKYedible() {
		return PINKYedible;
	}

	public boolean isSUEedible() {
		return SUEedible;
	}

	public double getMinPacmanDistancePPill() {
		return minPacmanDistancePPill;
	}

	@Override
	public Collection<String> getFacts() {
		Vector<String> facts = new Vector<String>();
		facts.add(String.format("(BLINKY (edible %s))", this.BLINKYedible));
		facts.add(String.format("(BLINKY (closeToOthers %s))", this.BLINKYcloseToOthers));
		facts.add(String.format("(BLINKY (farFromOthers %s))", this.BLINKYfarFromOthers));
		facts.add(String.format("(BLINKY (closeToPacMan %s))", this.BLINKYcloseToPacMan));
		facts.add(String.format("(BLINKY (endOfEdible %s))", this.BLINKYendOFEdible));
		
		facts.add(String.format("(INKY (edible %s))", this.INKYedible));
		facts.add(String.format("(INKY (closeToOthers %s))", this.INKYcloseToOthers));
		facts.add(String.format("(INKY (farFromOthers %s))", this.INKYfarFromOthers));
		facts.add(String.format("(INKY (closeToPacMan %s))", this.INKYcloseToPacMan));
		facts.add(String.format("(INKY (endOfEdible %s))", this.INKYendOFEdible));
		
		facts.add(String.format("(PINKY (edible %s))", this.PINKYedible));
		facts.add(String.format("(PINKY (closeToOthers %s))", this.PINKYcloseToOthers));
		facts.add(String.format("(PINKY (farFromOthers %s))", this.PINKYfarFromOthers));
		facts.add(String.format("(PINKY (closeToPacMan %s))", this.PINKYcloseToPacMan));
		facts.add(String.format("(PINKY (endOfEdible %s))", this.PINKYendOFEdible));
		
		facts.add(String.format("(SUE (edible %s))", this.SUEedible));
		facts.add(String.format("(SUE (closeToOthers %s))", this.SUEcloseToOthers));
		facts.add(String.format("(SUE (farFromOthers %s))", this.SUEfarFromOthers));
		facts.add(String.format("(SUE (closeToPacMan %s))", this.SUEcloseToPacMan));
		facts.add(String.format("(SUE (endOfEdible %s))", this.SUEendOFEdible));
		
		facts.add(String.format("(MSPACMAN (mindistancePPill %d))", 
								(int)this.minPacmanDistancePPill));
		facts.add(String.format("(MSPACMAN_EATEN (eaten %s))", this.wasPacManEaten));
		return facts;
	}
	
	private boolean ghostFarFromOthers(GHOST ghost) {
		for(GHOST g : GHOST.values()) {
			if(!g.equals(ghost) && this.isGhostOut(g) && this.getDistanceBetweenGhosts(ghost, g) >= LIMIT)
				return true;
		}
		return false;
	}
	
	public boolean ghostCloseToOthers(GHOST ghost) {
		for(GHOST g : GHOST.values()) {
			if(!g.equals(ghost) && this.isGhostOut(g) && this.getDistanceBetweenGhosts(ghost, g) < LIMIT)
				return true;
		}
		return false;
	}

	public boolean wasPacManEaten() {
		return wasPacManEaten;
	}

	public void setPacManDead(boolean wasPacManEaten) {
		this.wasPacManEaten = wasPacManEaten;
	}
	
	public int getGhostEdibleTime(GHOST ghost) {
		return game.getGhostEdibleTime(ghost);
	}
	
	public int getDistanceToPacman(GHOST ghost) {
		return game.getShortestPathDistance(game.getGhostCurrentNodeIndex(ghost),
				game.getPacmanCurrentNodeIndex());
	}
	
	public int getDistanceBetweenGhosts(GHOST g1, GHOST g2) {
		return game.getShortestPathDistance(game.getGhostCurrentNodeIndex(g1), game.getGhostCurrentNodeIndex(g2), game.getGhostLastMoveMade(g1));
	}
	
	public boolean isGhostOut(GHOST g) {
		return !(game.getGhostLairTime(g) > 0);
	}

	public boolean isBLINKYcloseToOthers() {
		return BLINKYcloseToOthers;
	}

	public void setBLINKYcloseToOthers(boolean bLINKYcloseToOthers) {
		BLINKYcloseToOthers = bLINKYcloseToOthers;
	}

	public boolean isINKYcloseToOthers() {
		return INKYcloseToOthers;
	}

	public void setINKYcloseToOthers(boolean iNKYcloseToOthers) {
		INKYcloseToOthers = iNKYcloseToOthers;
	}

	public boolean isPINKYcloseToOthers() {
		return PINKYcloseToOthers;
	}

	public void setPINKYcloseToOthers(boolean pINKYcloseToOthers) {
		PINKYcloseToOthers = pINKYcloseToOthers;
	}

	public boolean isSUEcloseToOthers() {
		return SUEcloseToOthers;
	}

	public void setSUEcloseToOthers(boolean sUEcloseToOthers) {
		SUEcloseToOthers = sUEcloseToOthers;
	}

	public boolean ghostCloseToPacMan(GHOST ghost) {
		return this.getDistanceToPacman(ghost) < LIMIT;
	}
	
	public boolean ghostEndOfEdible(GHOST ghost) {
		int ghostTime = this.getGhostEdibleTime(ghost);
		int distanceToPacman = this.getDistanceToPacman(ghost);
		return ghostTime < GHOST_EATABLE_TIME_LIMIT && distanceToPacman < DISTANCE_TO_PACMAN;
	}


	
	
}

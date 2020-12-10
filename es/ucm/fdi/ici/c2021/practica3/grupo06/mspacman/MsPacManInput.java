package es.ucm.fdi.ici.c2021.practica3.grupo06.mspacman;

import java.util.Collection;
import java.util.HashSet;
import java.util.Vector;

import es.ucm.fdi.ici.rules.Input;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class MsPacManInput extends Input {

	public static double thresold = 30;
	public static int larger_radius = 80;
	public static final int TIME = 30;
	private boolean BLINKYedible;
	private boolean INKYedible;
	private boolean PINKYedible;
	private boolean SUEedible;
	private double minPacmanDistancePPill;
	private GHOST nearestGhost = null;
	private int nearestGhostEdibleTime;
	private boolean PacManInJunction;
	private static int limit = 30;
	
	public MsPacManInput(Game game) {
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
		for (int ppill : game.getPowerPillIndices()) {
			double distance = game.getDistance(pacman, ppill, DM.PATH);
			this.minPacmanDistancePPill = Math.min(distance, this.minPacmanDistancePPill);
		}

		try {
			for (GHOST ghostType : GHOST.values()) {
				int ghostNode = game.getGhostCurrentNodeIndex(ghostType);
				int distanceGhostToPacman = game.getShortestPathDistance(game.getPacmanCurrentNodeIndex(), ghostNode,
						game.getPacmanLastMoveMade());
				if (distanceGhostToPacman < thresold) {
					this.nearestGhost = ghostType;
				}
			}
		} catch (IndexOutOfBoundsException ex) {
		}
		
		if(this.nearestGhost != null)
			this.nearestGhostEdibleTime = this.game.getGhostEdibleTime(this.nearestGhost);
		else
			this.nearestGhostEdibleTime = 1000;
		
		this.PacManInJunction = this.game.isJunction(this.game.getPacmanCurrentNodeIndex());
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

	public GHOST getNearestGhost() {
		return nearestGhost;
	}

	public void setNearestGhost(GHOST nearestGhost) {
		this.nearestGhost = nearestGhost;
	}

	public int getNearestGhostEdibleTime() {
		return nearestGhostEdibleTime;
	}

	public void setNearestGhostEdibleTime(int nearestGhostEdibleTime) {
		this.nearestGhostEdibleTime = nearestGhostEdibleTime;
	}

	public boolean isPacManInJunction() {
		return PacManInJunction;
	}

	public void setPacManInJunction(boolean pacManInJunction) {
		PacManInJunction = pacManInJunction;
	}
	
	private GHOST getNearestChasingGhost() {
		int pacManNode = game.getPacmanCurrentNodeIndex();
		GHOST nearestGhost = null;

		for (GHOST ghostType : GHOST.values()) {
			if (game.getGhostEdibleTime(ghostType) < 50 && game.getGhostLairTime(ghostType) == 0
					&& !game.isGhostEdible(ghostType)) {
				int ghostNode = game.getGhostCurrentNodeIndex(ghostType);
				if (game.getShortestPathDistance(pacManNode, ghostNode, game.getPacmanLastMoveMade()) < limit) {
					nearestGhost = ghostType;
				}
			}
		}

		return nearestGhost;
	}
	
	private HashSet<MOVE> getForbiddenMoves(){
		HashSet<MOVE> forbiddenMoves = new HashSet<>();
		for(GHOST ghost : GHOST.values())
			if(game.getGhostLairTime(ghost) <= 0)
				forbiddenMoves.add(game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(ghost), game.getPacmanLastMoveMade(), DM.PATH));
		return forbiddenMoves;
	}

	@Override
	public Collection<String> getFacts() {
		Vector<String> facts = new Vector<String>();
		facts.add(String.format("(GHOSTS (edible %s))", (this.BLINKYedible || this.INKYedible || this.PINKYedible || this.SUEedible))); 
		facts.add(String.format("(GHOSTS (lessTime %s))", (getNearestGhostEdibleTime() < TIME || 
				(!this.isBLINKYedible() && !this.isINKYedible() && !this.isPINKYedible() && !this.isSUEedible()))));
		facts.add(String.format("(MSPACMAN (far %s))", this.getNearestGhost() == null));
		facts.add(String.format("(MSPACMAN (junction %s))", this.isPacManInJunction() && getNearestChasingGhost() != null && !getForbiddenMoves().contains(game.getPacmanLastMoveMade())));
		facts.add(String.format("(MSPACMAN (nearGPP %s))", (getNearestGhost() != null && getMinPacmanDistancePPill() < thresold)));
		facts.add(String.format("(MSPACMAN (nearG %s))", getNearestGhost() != null && getMinPacmanDistancePPill() < thresold));		
		return facts;
	}

}

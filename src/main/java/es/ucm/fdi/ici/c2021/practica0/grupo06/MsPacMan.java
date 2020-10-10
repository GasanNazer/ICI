package es.ucm.fdi.ici.c2021.practica0.grupo06;

import java.util.ArrayList;

import pacman.controllers.PacmanController;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class MsPacMan extends PacmanController {

	@Override
	public MOVE getMove(Game game, long timeDue) {
		int limit = 20;
		/*
		GHOST nearestGhost = getNearestChasingGhost(game, limit);
		if (nearestGhost != null) {
			System.out.println("Nearest ghost chasing:" + nearestGhost);
			return game.getNextMoveAwayFromTarget(game.getPacmanCurrentNodeIndex(),
					game.getGhostCurrentNodeIndex(nearestGhost), DM.PATH);
		}

		nearestGhost = getNearestEdibleGhost(game, limit);
		if (nearestGhost != null) {
			System.out.println("Nearest ghost editable:" + nearestGhost);
			return game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(),
					game.getGhostCurrentNodeIndex(nearestGhost), DM.PATH);
		}
		*/
		
		int nearestPill = getNearestPill(game);
		System.out.println("Nearest pill editable: " + nearestPill + " PacMan position: " + game.getPacmanCurrentNodeIndex());
		MOVE move = game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(), nearestPill, DM.PATH);
		System.out.println("Move: " + move);
		return  move;
	}

	private GHOST getNearestChasingGhost(Game game, int limit) {
		GHOST nearestGhost = null;
		double nearestDistance = limit;

		for (GHOST ghostType : GHOST.values()) {
			if (!game.isGhostEdible(ghostType)) {
				double distance = game.getDistance(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(ghostType), DM.PATH);
				if (distance < nearestDistance) {
					nearestGhost = ghostType;
					nearestDistance = distance;
				}
			}
		}

		return nearestGhost;
	}

	private GHOST getNearestEdibleGhost(Game game, int limit) {
		GHOST nearestGhost = null;
		double nearestDistance = limit;

		for (GHOST ghostType : GHOST.values()) {
			if (game.isGhostEdible(ghostType)) {
				double distance = game.getDistance(game.getGhostCurrentNodeIndex(ghostType), game.getPacmanCurrentNodeIndex(), DM.PATH);
				if (distance < nearestDistance) {
					nearestGhost = ghostType;
					nearestDistance = distance;
				}
			}
		}

		return nearestGhost;
	}

	private int getNearestPill(Game game) {
		int nearestPill = 0; // could be pill or power pill
		double nearestDistance = Double.MAX_VALUE;
		ArrayList<Double> a = new ArrayList();
		// checking if the nearest pill is of type pill
		for (int pill : game.getPillIndices()) {
			if(game.isPillStillAvailable(game.getPillIndex(pill))) {
				double distance = game.getDistance(game.getPacmanCurrentNodeIndex(), game.getPillIndex(pill), DM.PATH);
				a.add(distance);
				if (distance < nearestDistance) {
					nearestPill = game.getPillIndex(pill);
					nearestDistance = distance;
				}
				
			}
		}
		
		//a.forEach(e -> System.out.println(e + ","));
		/*
		// checking if the nearest pill is of type power pill
		for (int pill : game.getPowerPillIndices()) {
			if(game.isPowerPillStillAvailable(pill)) {
				double distance = game.getDistance(game.getPacmanCurrentNodeIndex(), pill, DM.PATH);
				if (distance <= nearestDistance) {
					nearestPill = pill;
					nearestDistance = distance;
				}
			}
		}
		*/
		return nearestPill;
	}
}

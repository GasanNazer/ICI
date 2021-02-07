package es.ucm.fdi.ici.c2021.practica5.grupo06;

import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRQuery;
import es.ucm.fdi.ici.c2021.practica5.grupo06.CBRengine.MsPacManDescription;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class MsPacManInput implements Input {

	Integer score;
	Integer time;
	Integer nearestPPill;
	Integer nearestPill;
	Integer nearestNonEdibleGhostDist;
	GHOST nearestNonEdibleGhost;
	Integer nearestEdibleGhostDist;
	Boolean edibleGhost;
	Integer timeEdibleLeft;
	
	Integer leftPills;
	Integer leftPPills;
	
	@Override
	public void parseInput(Game game) {
		computeNearestEdibleAndNonEdibleGhost(game);
		computeNearestPPill(game);
		computeNearestPill(game);
		time = game.getTotalTime();
		score = game.getScore();
	}

	@Override
	public CBRQuery getQuery() {
		MsPacManDescription description = new MsPacManDescription();
		description.setEdibleGhost(edibleGhost);
		description.setNearestEdibleGhostDist(nearestEdibleGhostDist);
		description.setNearestNonEdibleGhostDist(nearestNonEdibleGhostDist);
		description.setTimeEdibleLeft(timeEdibleLeft);
		description.setNearestPPill(nearestPPill);
		description.setNearestPill(nearestPill);
		description.setScore(score);
		description.setTime(time);
		description.setLeftPills(leftPills);
		description.setLeftPPills(leftPPills);
		
		CBRQuery query = new CBRQuery();
		query.setDescription(description);
		return query;
	}
	
	private void computeNearestEdibleAndNonEdibleGhost(Game game) {
		nearestEdibleGhostDist = Integer.MAX_VALUE;
		nearestNonEdibleGhostDist = Integer.MAX_VALUE;
		edibleGhost = false;
		timeEdibleLeft = 0;
		GHOST nearestEdibleGhost = null;
		nearestNonEdibleGhost = null;
		for(GHOST g: GHOST.values()) {
			int pos = game.getGhostCurrentNodeIndex(g);
			int distance;
			try {
				distance = (int)game.getDistance(game.getPacmanCurrentNodeIndex(), pos, game.getPacmanLastMoveMade(), DM.PATH);
			}catch(Exception e){
				distance = Integer.MAX_VALUE;
			}
			if(game.isGhostEdible(g)) {
				if(distance < nearestEdibleGhostDist){
					nearestEdibleGhostDist = distance;
					nearestEdibleGhost = g;
				}
			}
			else {
				if(distance < nearestNonEdibleGhostDist){
					nearestNonEdibleGhostDist = distance;
					nearestNonEdibleGhost = g;
				}
			}
			
		}
		if(nearestEdibleGhost!=null) {
			edibleGhost = true;
			timeEdibleLeft = game.getGhostEdibleTime(nearestEdibleGhost);
		}
	}
	
	private void computeNearestPPill(Game game) {
		nearestPPill = Integer.MAX_VALUE;
		leftPPills = game.getNumberOfActivePowerPills();
		for(int pos: game.getActivePowerPillsIndices()) {
			int distance = (int)game.getDistance(game.getPacmanCurrentNodeIndex(), pos, game.getPacmanLastMoveMade(), DM.PATH);
			if(distance < nearestPPill) {
				if(nearestNonEdibleGhost != null && distance < (int)game.getDistance(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(nearestNonEdibleGhost), game.getPacmanLastMoveMade(), DM.PATH))
					nearestPPill = distance;
				else if(nearestNonEdibleGhost == null)
					nearestPPill = distance;
			}
		}
	}
	
	private void computeNearestPill(Game game) {
		nearestPill = Integer.MAX_VALUE;
		leftPills = game.getNumberOfActivePills();
		for(int pos: game.getActivePillsIndices()) {
			int distance = (int)game.getDistance(game.getPacmanCurrentNodeIndex(), pos, game.getPacmanLastMoveMade(), DM.PATH);
			if(distance < nearestPill) {
				if(nearestNonEdibleGhost != null && distance < (int)game.getDistance(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(nearestNonEdibleGhost), game.getPacmanLastMoveMade(), DM.PATH))
					nearestPill = distance;
				else if(nearestNonEdibleGhost == null)
					nearestPill = distance;
			}
		}
	}
}

package es.ucm.fdi.ici.c2021.practica0.grupo06;

import pacman.Executor;
import pacman.controllers.GhostController;
import pacman.controllers.HumanController;
import pacman.controllers.KeyBoardInput;
import pacman.controllers.PacmanController;

public class ExecutorTest {

	public static void main(String[] args) {
		Executor executor = new Executor.Builder()
				.setTickLimit(4000)
				//.setGhostPO(true)
				//.setPacmanPO(true)
				//.setPacmanPOvisual(true)
				
				.setVisual(true)
				.setScaleFactor(2.0)
				.build();
				
		PacmanController pacMan = new MsPacMan();//new HumanController(new KeyBoardInput());//new PacManRandom();//new MsPacMan();//new MsPacMan();//
		GhostController ghosts = new Ghosts();
		System.out.println(executor.runGame(pacMan, ghosts, 50));
	}
}

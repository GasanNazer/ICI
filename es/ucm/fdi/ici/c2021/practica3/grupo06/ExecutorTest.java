package es.ucm.fdi.ici.c2021.practica3.grupo06;
import pacman.Executor;
import pacman.controllers.GhostController;
import pacman.controllers.HumanController;
import pacman.controllers.KeyBoardInput;
import pacman.controllers.PacmanController;


public class ExecutorTest {

    public static void main(String[] args) {
        Executor executor = new Executor.Builder()
                .setTickLimit(4000)
                .setGhostPO(false)
                .setPacmanPO(false)
                .setVisual(true)
                .setScaleFactor(2.0)
                .build();

        PacmanController pacMan = new HumanController(new KeyBoardInput());
        GhostController ghosts = new es.ucm.fdi.ici.c2021.practica3.grupo06.Ghosts();
        
        System.out.println( 
        		executor.runGame(pacMan, ghosts, 40)
        );
        
    }
}
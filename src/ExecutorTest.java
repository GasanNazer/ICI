import es.ucm.fdi.ici.c2021.practica5.grupo06.Ghosts;
import es.ucm.fdi.ici.c2021.practica5.grupo06.MsPacMan;

import pacman.Executor;
import pacman.controllers.GhostController;
import pacman.controllers.PacmanController;

public class ExecutorTest {

    public static void main(String[] args) {
        Executor executor = new Executor.Builder()
                .setTickLimit(4000)
                .setTimeLimit(200)
                .setGhostPO(false)
                .setPacmanPO(false)
                .setVisual(true)
                .setScaleFactor(2.0)
                .build();

        PacmanController pacMan = new MsPacMan();
        GhostController ghosts = new Ghosts();//new AggressiveGhosts();
      
        
        System.out.println( 
        		executor.runGame(pacMan, ghosts, 40)
        );
        
    }
}

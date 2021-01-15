

<<<<<<< HEAD:src/ExecutorTest.java
import es.ucm.fdi.ici.practica5.MsPacMan;
=======
import es.ucm.fdi.ici.c2021.practica4.grupo06.Ghosts;
import es.ucm.fdi.ici.c2021.practica4.grupo06.MsPacMan;
>>>>>>> 807a71989af75105079add4892e4fbd435702a62:ExecutorTest.java
import pacman.Executor;
import pacman.controllers.GhostController;
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

        PacmanController pacMan = new MsPacMan();
<<<<<<< HEAD:src/ExecutorTest.java
        GhostController ghosts = new AggressiveGhosts();
      
=======
        GhostController ghosts = new Ghosts();
>>>>>>> 807a71989af75105079add4892e4fbd435702a62:ExecutorTest.java
        
        System.out.println( 
        		executor.runGame(pacMan, ghosts, 40)
        );
        
    }
}

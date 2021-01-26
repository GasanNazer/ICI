import pacman.controllers.PacmanController;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class PacManRandom extends PacmanController{

	@Override
	public MOVE getMove(Game game, long timeDue) {
		return MOVE.NEUTRAL;
	}

}

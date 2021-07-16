package game;

import city.cs.engine.*;

/**
 * Listener for collision with a portal.  When the player collides with a portal,
 * if the current level is complete the game is advanced to the next level.
 */
public class PortalCollision implements CollisionListener {
    private Game game;

    /**
     * initialises the portal listener
     * @param game
     */
    public PortalCollision(Game game) {

        this.game = game;

    }

    @Override
    public void collide(CollisionEvent e) {


        if (e.getOtherBody() instanceof Person || e.getOtherBody() instanceof Troll)

            game.advanceToNextLevel();

//       }


    }
}
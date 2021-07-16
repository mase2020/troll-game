package game;

import city.cs.engine.*;

/**
 *portal in a game. When the actor collides with a portal, if
 * the current level is complete the game is advanced to the
 * next level. 
 */
public class Portal extends StaticBody {

    /**
     * Initialise a new portal.
     * @param world The world.
     */
    public Portal(World world) {
        super(world, new BoxShape(2f, 2.5f));
        addImage(new BodyImage("data/door.png", 6f));
    }
}

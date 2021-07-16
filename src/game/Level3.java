package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.LinkedList;

/**
 * level 3 of the game.
 */
public class Level3 extends GameLevel {
    /**
     * initiates level 3
     *
     * @param game links to the game.
     */
    public Level3(Game game) {
        super(game);

    }

    @Override
    /**
     * populate the game level.
     * different platforms for each game level.
     * add relevant number of coins for each level
     */
    public void populate() {


        // make platforms
        int platformCount =0;
        // a collection to deploy platforms at different positions.
        LinkedList<Platform> platforms = new LinkedList<>();
        while (platformCount< 10) {
            platforms.add( new Platform(this,2, 0.5f));
            platformCount++;

        }
        platforms.get(0).setPosition(new Vec2(-27.5f, -2f));
        platforms.get(1).setPosition(new Vec2(27.5f, -2f));
        platforms.get(2).setPosition(new Vec2(-18, 7f));
        platforms.get(3).setPosition(new Vec2(18, 7f));
        platforms.get(4).setPosition(new Vec2(9, -10f));
        platforms.get(5).setPosition(new Vec2(-12, -10f));
        platforms.get(6).setPosition(new Vec2(-20, -7f));
        platforms.get(7).setPosition(new Vec2(20, -5f));
        platforms.get(8).setPosition(new Vec2(-8, 0f));
        platforms.get(9).setPosition(new Vec2(4, -3f));

        // collision with troll will decrease health
        troll.addCollisionListener(new Collision(character));

// add hearts as health objects when health is 50 or below.
        heartNumber = 3;
        addHearts();
        // add coins to collect
        coinNumber = 25;
        addCoins();
    }




}


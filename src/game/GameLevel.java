package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Sets the basic structure of each level.
 */
public abstract class GameLevel extends World {

    protected static Person character;
    protected static Troll troll;
    // protected so that it can be accessed within the package.
    protected int heartNumber;
    protected int coinNumber;

    // create  x and y positions to apply for the characters.

    private int x;
    private int y;


    /**
     * creates the basic structure of each level, what each level will consist of.
     *
     * @param game connects to the Game
     */
    public GameLevel(Game game) {
        // make the ground
        Shape groundShape = new BoxShape(33, 1f);
        Body ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0, -18.5f));

        // make the walls
        Shape leftWallShape = new BoxShape(1f, 16, new Vec2(-33f, 15f));
        Fixture leftWall = new SolidFixture(ground, leftWallShape);
        Shape rightWallShape = new BoxShape(1f, 16, new Vec2(33f, 15f));
        Fixture rightWall = new SolidFixture(ground, rightWallShape);

        x = -27;
        y = 8;
        // make a character and set initial position
        character = new Person(this);
        character.setPosition(new Vec2(x, y));

        // change x and y for troll.
        x = 27;
        y = 8;
        // make a troll and set initial position
        troll = new Troll(this);
        troll.setPosition(new Vec2(x, y));

        // create a pathway into the next level
        Portal portal = new Portal(this);
        portal.setPosition(new Vec2(-27, -15));
        portal.addCollisionListener(new PortalCollision(game));
    }

    /**
     * a collection which will deploy different numbers of hearts according to the level.
     * The heartNumber will change at each level.
     * only the character can collect the hearts.
     * characters health will increase once collected
     *
     */
    public void addHearts() {
        int hearts = 0;
        LinkedList<Heart> heart = new LinkedList<Heart>();
        while (hearts < heartNumber) {

            //random positioning of the hearts.
            x = (int) (Math.random() * 50) - 27;
            y = 15;
            heart.add(new Heart(this));
            heart.get(hearts).setPosition(new Vec2(x, y));
            heart.get(hearts).addCollisionListener(new Collision(character));
            heart.get(hearts).addCollisionListener(new Collision(troll));
            hearts++;
        }
//
    }

    /**
     * A collection which will deploy different amounts of coins depending on the current level.
     * The coinNumber will change at each level.
     * both troll and character can collect the coins.
     * the coin count will increment upon collision.
     */
    public void addCoins() {
        int coins = 0;
        //collection of coins
        LinkedList<Coin> coin = new LinkedList<Coin>();
        while (coins < coinNumber) {
            // deploy at random positions
            x = (int) (Math.random() * 50) - 27;
            y = 15;
            coin.add(new Coin(this));
            coin.get(coins).setPosition(new Vec2(x, y));
            coin.get(coins).addCollisionListener(new Collision(character));
            coin.get(coins).addCollisionListener(new Collision(troll));
            coins++;
        }

    }

    /**
     * returns the character
     *
     * @return character
     */
    public static Person getPlayer() {
        return character;
    }

    /**
     * returns troll
     *
     * @return troll
     */
    public static Troll getTroll() {

        return troll;
    }

    /**
     * @return true if conditions are met else false.
     * <p>
     * <p>
     * conditions to go to the next level
     * if condition is met, then overall score will be updated.
     * coinCount will be reset for the next level
     */
    public boolean levelComplete() {
        if (Troll.getTrollCoinCount() > coinNumber / 2) {
            getTroll().incrementScore();
            getTroll().resetCoinCount();
            return true;
        } else if (Person.getCoinCount() > coinNumber / 2) {
            getPlayer().incrementScore();
            getPlayer().resetCoinCount();
            return true;
        } else {
            return false;
        }
    }

    /**
     * populates the world.

     */
    public abstract void populate();


}

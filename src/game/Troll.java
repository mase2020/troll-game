package game;

import city.cs.engine.*;

/**
 * Creates the troll in the game,
 */
public class Troll extends Walker {
    //use of polygon editor to make the right shape.
    private static final Shape trollShape = new PolygonShape(
            0.13f, 1.46f, 1.22f, -0.08f, 0.6f, -1.26f, -1.13f, -1.43f, -1.37f, -0.57f, -1.06f, 0.87f, -0.36f, 1.48f);

    private static final BodyImage image =
            new BodyImage("data/troll.png", 3f);

    public static void setTrollCoinCount(int trollCoinCount) {
        Troll.trollCoinCount = trollCoinCount;
    }

    public static void setTrollScore(int trollScore) {
        Troll.trollScore = trollScore;
    }

    //count will increment with the collection of coins
    private static int trollCoinCount = 0;
    private static int trollScore;


    /**
     * initialises the troll
     *
     * @param world links to the world
     */
    public Troll(World world) {
        super(world, trollShape);
        addImage(image);

    }

    /**
     * the trolls coin count
     *
     * @return coinCount
     */
    public static int getTrollCoinCount() {
        return trollCoinCount;
    }

    /**
     * increment the coin count once a coin is collected
     */
    public void trollCoinCount() {
        trollCoinCount++;
        System.out.println("Troll has collected " + trollCoinCount + " coins");

    }

    /**
     * increment score
     */
    public void incrementScore() {
        trollScore++;
    }

    /**
     * reset coin count for new level
     */
    public void resetCoinCount() {
        trollCoinCount = 0;
    }

    /**
     * the score of the troll
     *
     * @return score
     */
    public static int getTrollScore() {
        return trollScore;
    }
}
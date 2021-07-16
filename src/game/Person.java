package game;

import city.cs.engine.*;

/**
 * Creates the Character in the game,
 */
public class Person extends Walker {

    private static final Shape personShape = new PolygonShape(
            -0.2f, 1.46f, 0.22f, 1.46f, 0.35f, -0.19f, 0.31f, -1.39f, -0.33f, -1.42f, -0.3f, 1.37f);

    private static final BodyImage image =
            new BodyImage("data/person.png", 3f);

    private static int health;
    private static int coinCount;
    private static int personScore;

    /**
     * initialises the character
     * @param world links to the world
     */

    public Person(World world) {
        super(world, personShape);
        addImage(image);
        this.health = 100;
        coinCount = 0;
    }

    /**
     * enables to set the coin count for use with a loaded game
     * @param coinCount retrives the coin count
     */
    public static void setCoinCount(int coinCount) {
        Person.coinCount = coinCount;
    }

    /**
     * sets the characters scores on loaded game
     * @param personScore retrieves the score when saved
     */
    public static void setPersonScore(int personScore) {
        Person.personScore = personScore;
    }

    /**
     * gets the health of the character
     * @return health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * increases the health of the character when a heart is collected
     */
    public void increaseHealth() {
        if (this.health > 0 && this.health <= 90) {
            this.health += 10;
            System.out.println("your health is " + health + "%");
        } else if (this.health > 90) {
            this.health = 100;
            System.out.println("your health is " + health + "%");
        }
    }

    /**
     * sets the health of the character once the game is loaded.
     * @param health
     */
    public static void setHealth(int health) {
        Person.health = health;
    }

    /**
     * decreases the heaslth on contact with the troll
     * also destroys the character if health is below 0.
     */
    public void decreaseHealth() {
        if (this.health > 25) {
            this.health -= 25;
            System.out.println("your current health is " + health + "%");
        } else {
            this.health -= 25;
            System.out.println("You have ran out of health!!!");
        }


    }

    /**
     * the characters coin count
     * @return coinCount
     */

    public static int getCoinCount() {
        return coinCount;
    }

    /**
     * increment the coin count once a coin is collected
     */
    public void coinCount() {
        coinCount++;
        System.out.println("Person has collected " + coinCount + " coins");


    }

    /**
     * the score of the character
     * @return score
     */
    public static int getPersonScore() {
        return personScore;
    }

    /**
     * increment score
     */
    public void incrementScore() {
        personScore++;
    }

    /**
     * reset coin count for new level
     */
    public void resetCoinCount() {
        coinCount = 0;
    }


}

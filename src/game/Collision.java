package game;

import city.cs.engine.*;

/**
 * Collision listener that allows the character to collect health.
 * Troll and character are allowed to collect coins which are then incremented to their score.
 */
public class Collision implements CollisionListener {
    private Person character;
    private Troll troll;

    public Collision(Person character) {
        this.character = character;
    }
    public Collision(Troll troll) {
        this.troll = troll;
    }

    /**
     * Allows the game characters to collect different objects. once collected the object is destroyed and either the health or coinCount is incremented
     * @param e the objects which collide
     */
    @Override
    public void collide(CollisionEvent e) {
        // collision of a person with a heart will result to gaining health.
        if (e.getReportingBody() instanceof Heart && e.getOtherBody() == character) {
            character.increaseHealth();
            e.getReportingBody().destroy();
            //collision of person and troll will result in the person losing health
        } else if (e.getReportingBody() instanceof Troll && e.getOtherBody() == character) {
            character.decreaseHealth();
            //if health reaches 0, character dies.
            if (character.getHealth() <= 0) {
                character.destroy();
                System.out.println("You have lost a life!!!");
            }
            // collision with coins will increment coin count
        } else if (e.getReportingBody() instanceof Coin && e.getOtherBody() == character) {
            character.coinCount();
            e.getReportingBody().destroy();
        } else if (e.getReportingBody() instanceof Coin && e.getOtherBody() == troll) {
            troll.trollCoinCount();
            e.getReportingBody().destroy();
        }
    }
}

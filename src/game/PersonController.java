package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * setting controls for the Person
 */
public class PersonController extends KeyAdapter {
    private static final float JUMPING_SPEED = 15;
    private static final float WALKING_SPEED = 10;

    private Walker body;

    /**
     * initialises the character controls
     * @param body body of the character.
     */
    public PersonController(Walker body) {

        this.body = body;

    }

    @Override
    /**
     * key controls for the character
     */
    public void keyPressed(KeyEvent i) {
        int code = i.getKeyCode();

 if (code == KeyEvent.VK_W) { // W = jump
            Vec2 v = body.getLinearVelocity();
            // only jump if body is not already jumping
            if (Math.abs(v.y) < 0.01f) {
                body.jump(JUMPING_SPEED);
            }
        } else if (code == KeyEvent.VK_A) {
            body.startWalking(-WALKING_SPEED); // A = walk left
        } else if (code == KeyEvent.VK_D) {
            body.startWalking(WALKING_SPEED); // D = walk right

        }
    }


    @Override
    /**
     * what happens upon the release of keys.
     */
    public void keyReleased(KeyEvent i) {
        int code = i.getKeyCode();
        if (code == KeyEvent.VK_A) {
            body.stopWalking();
        } else if (code == KeyEvent.VK_D) {
            body.stopWalking();
        }
    }
    public void setPerson (Walker b){
        body = b;
    }
}

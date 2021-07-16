package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * setting controls for the troll
 */
public class TrollController extends KeyAdapter {
    private static float JUMPING_SPEED = 15;
    private static final float WALKING_SPEED = 10;

    private Walker body;

    /**
     * initialises the troll controls
     *
     * @param body body of the troll.
     */
    public TrollController(Walker body) {
        this.body = body;
    }


    @Override
    /**
     * key controls for the troll
     */
    public void keyPressed(KeyEvent i) {
        int code = i.getKeyCode();

      if (code == KeyEvent.VK_UP) { // up = jump
            Vec2 v = body.getLinearVelocity();
            // only jump if body is not already jumping
            if (Math.abs(v.y) < 0.01f) {
                body.jump(JUMPING_SPEED);
            }
        } else if (code == KeyEvent.VK_LEFT) {
            body.startWalking(-WALKING_SPEED); // 1 = walk left
        } else if (code == KeyEvent.VK_RIGHT) {
            body.startWalking(WALKING_SPEED); // 2 = walk right
        }
    }


    @Override
    /**
     * what happens upon the release of keys.
     */
    public void keyReleased(KeyEvent i) {
        int code = i.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            body.stopWalking();
        } else if (code == KeyEvent.VK_RIGHT) {
            body.stopWalking();
        }
    }

    public void setTroll(Walker b) {
        body = b;
    }
}


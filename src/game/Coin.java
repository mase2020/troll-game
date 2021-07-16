package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.Color;


/**
 * A coin.
 */
public class Coin extends DynamicBody {
    //use of polygon editor to make the right shape.
    private static final Shape coinShape = new PolygonShape(0.046f, 0.492f, 0.432f, 0.286f, 0.492f, -0.252f, 0.116f, -0.486f, -0.338f, -0.412f, -0.492f, 0.024f, -0.324f, 0.466f);
    private static final BodyImage image =
            new BodyImage("data/coin.png", 1f);

    public Coin(World world) {
        super(world, coinShape);
        addImage(image);

    }
}

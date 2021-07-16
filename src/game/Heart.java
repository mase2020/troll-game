package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.Color;


/**
 * A heart.
 */
public class Heart extends DynamicBody {
    // use of the polygon editor to edit the shape of the heart
    private static final Shape heartShape = new PolygonShape(-0.486f,0.484f, 0.538f,0.482f, 0.529f,-0.082f, 0.048f,-0.467f, -0.479f,-0.098f, -0.543f,0.461f);
    private static final BodyImage image =
            new BodyImage("data/heart.png", 1f);

    /**
     * adding hearts
     * @param world will add a heart to the world
     */
    public Heart(World world) {
        super(world, heartShape);
        addImage(image);

    }
}

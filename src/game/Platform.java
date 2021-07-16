package game;

import city.cs.engine.*;

/**
 * Creates Platforms in the game
 */
public class Platform extends StaticBody {

    private float width;
    private float height;
    public Platform(World w, float width, float height) {
        super(w);
        Shape s = new BoxShape(width,height);
        Fixture f = new SolidFixture(this,s);
        this.height = height;
        this.width = width;
    }

    /**
     * gets the width of the platforms
     * @return width
     */
    public float getWidth() {
        return width;
    }

    /**
     * gets the height of the platforms
     * @return height
     */
    public float getHeight() {
        return height;
    }
}

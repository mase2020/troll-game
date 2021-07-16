package game;

import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;

import java.io.FileWriter;
import java.io.IOException;

/**
 * enables a saving feature in the game.
 */
public class SaveGame {
    private String fileName;

    /**
     * initialises the save game.
     *
     * @param fileName where to write the data.
     */
    public SaveGame(String fileName) {
        this.fileName = fileName;
    }

    /**
     * writes the file to a location.
     * will save the current status of all bodies within the game.
     * will save current game status ; scores, health and coin count
     *
     * @throws IOException
     */
    public void saveGame(GameLevel gameworld) throws IOException {
        //will allow to keep writing data without overwrite.
        boolean append = true;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName);
            // saving position data of troll and person.
            // saving health, coin count and score status.
            // separated by ',' for regex to work effectively in the load file
            writer.write(Game.getLevelNumber()
                    + "," + GameLevel.getPlayer().getPosition().x
                    + "," + GameLevel.getPlayer().getPosition().y
                    + "," + GameLevel.getPlayer().getHealth()
                    + "," + GameLevel.getPlayer().getPersonScore()
                    + "," + GameLevel.getTroll().getTrollScore()
                    + "," + GameLevel.getTroll().getPosition().x
                    + "," + GameLevel.getTroll().getPosition().y
                    + "," + GameLevel.getPlayer().getCoinCount()
                    + "," + GameLevel.getTroll().getTrollCoinCount()
                    + "\n");

            // retrieving information of all dynamic bodies.
            // separated by ':' for regex to work efficiently when loading.
            for (DynamicBody body : gameworld.getDynamicBodies()) {
                writer.write(body.getClass().getSimpleName()
                        + ":" + body.getPosition().x
                        + ":" + body.getPosition().y + "\n");
            }
            // retrieving information of all static bodies.
            //platform has extra params.
            for (StaticBody body : gameworld.getStaticBodies()) {
                if (body instanceof Platform) {
                    writer.write(body.getClass().getSimpleName()
                            + ":" + body.getPosition().x
                            + ":" + body.getPosition().y
                            + ":" + ((Platform) body).getWidth()
                            + ":" + ((Platform) body).getHeight()
                            + "\n");
                } else writer.write(body.getClass().getSimpleName()
                        + ":" + body.getPosition().x
                        + ":" + body.getPosition().y + "\n");
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}

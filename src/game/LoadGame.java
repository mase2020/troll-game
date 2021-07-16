package game;

import city.cs.engine.Body;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * ability to load a previously saved file
 */
public class LoadGame {
    private String fileName;
    private Game game;
    private static int gameLevel;

    /**
     * Initialise a new saved file reader
     *
     * @param fileName the name of the save file
     */
    public LoadGame(String fileName, Game game) {
        this.fileName = fileName;
        this.game = game;

    }


    public static int getGameLevel() {
        return gameLevel;
    }

    /**
     * Read the save file data from the save data file and print it to
     * the terminal window.
     * all data which was saved will be loaded.
     * previous game conditions can be resumed.
     */
    public GameLevel loadGame() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            //when writing the saveGame file, the level number, player position and troll position are split, the regex has the value of ,
            //anything before that is the first token, this will return a string.
            String[] tokens = line.split(",");
            // to convert a string into an integer, parseInt is used. parseFloat for floats.
            gameLevel = Integer.parseInt(tokens[0]);
            //character positions
            float xPlayer = Float.parseFloat(tokens[1]);
            float yPlayer = Float.parseFloat(tokens[2]);
            int health = Integer.parseInt(tokens[3]);
            int personScore = Integer.parseInt(tokens[4]);
            int trollScore = Integer.parseInt(tokens[5]);
            float xTroll = Float.parseFloat(tokens[6]);
            float yTroll = Float.parseFloat(tokens[7]);
            int personCoinCount = Integer.parseInt(tokens[8]);
            int trollCoinCount = Integer.parseInt(tokens[9]);
            Vec2 posPlayer = new Vec2(xPlayer, yPlayer);
            Vec2 posTroll = new Vec2(xTroll, yTroll);
// game level is retrieved from the saved file. new game world is created for that level
            GameLevel level = null;
            if (gameLevel == 1) {
                level = new Level1(game);

            } else if (gameLevel == 2) {
                level = new Level2(game);

            } else if (gameLevel == 3) {
                level = new Level3(game);

            }
// retrieving the positions of all other static and dynamic bodies and deploying them.
            while ((line = reader.readLine()) != null) {
                tokens = line.split(":");
                String className = tokens[0];
                float x = Float.parseFloat(tokens[1]);
                float y = Float.parseFloat(tokens[2]);
                Vec2 position = new Vec2(x, y);

                if (className.equals("Coin")) {
                    Coin coin = new Coin(level);
                    coin.setPosition(position);
                    coin.addCollisionListener(new Collision(level.getPlayer()));
                    coin.addCollisionListener(new Collision(level.getTroll()));
                } else if (className.equals("Heart")) {
                    Heart heart = new Heart(level);
                    heart.setPosition(position);
                    heart.addCollisionListener(new Collision(level.getPlayer()));
                } else if (className.equals("Platform")) {
                    float w = Float.parseFloat(tokens[3]);
                    float h = Float.parseFloat(tokens[4]);
                    Platform platform = new Platform(level, w, h);
                    platform.setPosition(position);
                }
            }

// setting the position of the character, troll, health, coin counts and scores.
            Game.setLevelNumber(gameLevel);
            level.getPlayer().setPosition(posPlayer);
            level.getTroll().setPosition(posTroll);
            level.getPlayer().setHealth(health);
            level.getPlayer().setPersonScore(personScore);
            level.getTroll().setTrollScore(trollScore);
            level.getPlayer().setCoinCount(personCoinCount);
            level.getTroll().setTrollCoinCount(trollCoinCount);
            // collision with troll will decrease health


            // fulfilling the conditions of the saved file.


            return level;
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }


}

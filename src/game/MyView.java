package game;

import city.cs.engine.SoundClip;
import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * the view which will be present in the game
 */
public class MyView extends UserView {
    private Person character;
    private Troll troll;
    private Image background;
    private int level;
    private static SoundClip bgMusic;

    public static SoundClip getBgMusic() {
        return bgMusic;
    }

    /**
     * initialises the view
     * @param w links to the world
     * @param character character body
     * @param troll troll body
     * @param width width of the JPanel frame
     * @param height height of the JPanel frame
     */
    public MyView(World w, Person character, Troll troll, int width, int height) {
        super(w, width, height);
        this.character = character;
        this.troll = troll;
        setBackground();
    }


    /**
     * sets the background image and soundclip depending on the current level.
     */
    public void setBackground() {
        level =  Game.getLevelNumber();

        if (level == 1) {
            background = new ImageIcon("data/game-background.jpg").getImage();
            try {
               bgMusic = new SoundClip("data/level1.wav");
                bgMusic.loop();
            } catch (UnsupportedAudioFileException |
                    IOException |
                    LineUnavailableException e) {
            }
        } else if (level == 2) {
            background = new ImageIcon("data/bg2.jpg").getImage();
            bgMusic.stop();
            try {
            bgMusic = new SoundClip("data/level2.wav");
            bgMusic.loop();
        } catch (UnsupportedAudioFileException |
                IOException |
                LineUnavailableException e) {
             }
        } else if (level == 3) {
            background = new ImageIcon("data/bg3.jpg").getImage();
            bgMusic.stop();
            try {
                bgMusic = new SoundClip("data/level3.wav");
                bgMusic.loop();
            } catch (UnsupportedAudioFileException |
                    IOException |
                    LineUnavailableException e) {
            }
        }
    }




    @Override
    /**
     * adds the score and health on the view.
     */
    protected void paintBackground(Graphics2D g) {
        super.paintBackground(g);

        g.drawImage(background, 0, 0, null);
//        Vec2 posPerson = character.getPosition();
//        Point2D.Float posPersonScreen = this.worldToView(posPerson);
//        g.drawString("Health:" +character.getHealth(),(int)(posPersonScreen.x)-25, (int)(posPersonScreen.y)-50);
    }

    /**
     * will add the coin count, scores and persons health to the view.
     * @param g
     */
    @Override
    protected void paintForeground(Graphics2D g) {
        super.paintForeground(g);
        g.drawString("Person Coins collected: " + character.getCoinCount(), 1250, 20);
        g.drawString("Troll Coins collected: " + troll.getTrollCoinCount(), 10, 20);
        g.drawString("Person Health: " + character.getHealth(), 1250, 40);
        g.drawString("Person Score: " + character.getPersonScore(), 1250, 60);
        g.drawString("Troll Score: " + troll.getTrollScore(), 10, 40);
    }
}

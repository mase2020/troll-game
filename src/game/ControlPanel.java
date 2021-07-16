package game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Adding GUI buttons to the game.
 */

public class ControlPanel extends JPanel{
    private JButton pauseButton;
    private JButton restartButton;
    private JButton quitButton;
    private JButton save;
    private JButton load;
    private Game game;
    private GameLevel level;

    /**
     * creating the GUI buttons, once pressed they perform a certain action.
     * @param game on the current game world
     * @param level on the current game level
     */
    public ControlPanel(Game game, GameLevel level) {

        // adding all the buttons
        this.game = game;
        this.level = level;
        pauseButton = new JButton("Pause");
        pauseButton.setForeground(Color.ORANGE);

        this.add(pauseButton);
        restartButton = new JButton("Restart");
        restartButton.setForeground(Color.GREEN);

        this.add(restartButton);
        quitButton = new JButton("Quit");
        quitButton.setForeground(Color.RED);

        this.add(quitButton);
        save = new JButton("Save");
        save.setForeground(Color.BLUE);

        this.add(save);
        load = new JButton("Load");
        load.setForeground(Color.MAGENTA);
        this.add(load);

        //adding action listeners to the button so that they respond when clicked.

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Game quit");
                System.exit(0);
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Game paused");
                game.pause();
            }
        });
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Game restarted");
               game.restart();
            }
        });

       save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Game Saved");
                SaveGame sg = new SaveGame("data/save.txt");
                try {
                    sg.saveGame(Game.getCurrentLevel());
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }

            }
        });

       load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Game Loaded");
                LoadGame lg =new LoadGame("data/save.txt", game);
                try {
                    GameLevel loadedGame = lg.loadGame();
                    game.goToLevel(loadedGame);
                }
                catch(IOException  ex){
                    ex.printStackTrace();
                }

            }
        });
    }
    }




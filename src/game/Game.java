package game;

import java.awt.*;

import javax.swing.JFrame;

/**
 * Multi-player game including a character and a troll.
 * If the troll touches the character, the character will lose some health.
 * If characters health reaches 0, the character is destroyed.
 * The character can move faster than the troll and the
 * character can collect hearts to regain health.
 * The aim is to collect more than half the coins in each level and then reach the portal before the enemy.
 * The first to reach the portal with conditions fulfilled will win that level.
 * Whoever wins the most levels will be the overall winner of the game.
 *
 * @author Muhammad, Masum, Miah, Muhammad.Miah.6@city.ac.uk
 * @version 1.0
 */
public class Game {


    // The current level where the game will start from which sets out the world of the game.

    private static GameLevel currentLevel;

    // The levelNumber of the game in order to change the world depending on the levelNumber.

    private static int levelNumber = 1;

    // A graphical display of the world (a specialised JPanel)

    private static MyView view;

    public static GameLevel getCurrentLevel() {
        return currentLevel;
    }
// Adding the controls to the character

    private PersonController personController;

    //Adding the controls to the troll

    private TrollController trollController;

    /**
     * Start the game
     */
    public Game() {

        //make the world

        currentLevel = new Level1(this);

        // level 1 will be populated.
        currentLevel.populate();

        //make a view

        view = new MyView(currentLevel, currentLevel.getPlayer(), currentLevel.getTroll(), 1500, 735);


        // display the view in a frame
        final JFrame frame = new JFrame("Project Semester 2");
        //Adding the GUI buttons to the frame
        frame.add( new ControlPanel(this, currentLevel),BorderLayout.NORTH);
        //Adding the sound controls to the frame.
        frame.add(new AudioControls().getPanelControls(), BorderLayout.SOUTH);

        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // display the world in the window
        frame.add(view);

        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // make the window visible
        frame.setVisible(true);
        // get keyboard focus
        frame.requestFocus();
        // give keyboard focus to the frame whenever the mouse enters the view
        view.addMouseListener(new GiveFocus(frame));

        // adding the keys for movement for the character and the troll.
        // getPlayer() is a method which will return character
        // getTroll returns troll
        personController = new PersonController(currentLevel.getPlayer());
        trollController = new TrollController(currentLevel.getTroll());

        // adapted code so that can control at different levels
        frame.addKeyListener(personController);
        frame.addKeyListener(trollController);

        // uncomment to make the view track the bird
        // world.addStepListener(new Tracker(view, world.getPlayer()));

        // uncomment this to make a debugging view
        // JFrame debugView = new DebugViewer(world, 500, 500);

        // start!
        currentLevel.start();
    }

    /**
     * Run the game.
     */
    public static void main(String[] args) {

        new Game();

    }

    /**
     * used when loading a game.
     * <p>
     * the current game will be stopped and a game will be loaded.
     *
     * @param level Will receive the game level from the loaded game.
     *              </p>
     */
    public void goToLevel(GameLevel level) {
        // method body
        currentLevel.stop();
        // stop the background music in order to play the sound for the correct level.
        MyView.getBgMusic().stop();
        //current level will be the loaded level.
        currentLevel = level;
        view.setWorld(currentLevel);
        personController.setPerson(currentLevel.getPlayer());
        trollController.setTroll(currentLevel.getTroll());
        view.setBackground();
        currentLevel.start();

    }

    /**
     * If conditions of the current level are met, progress to the next level can be made.
     * a record of the player scores is kept.
     * after 3 rounds, the player with the most points wins the game.
     */

    public void advanceToNextLevel() {
        if (levelNumber == 3 && currentLevel.levelComplete()) {
            System.out.println("Game Over");
            // After 3 rounds, the player who has won the most rounds is the winner.
            if (currentLevel.getTroll().getTrollScore() > currentLevel.getPlayer().getPersonScore()) {
                System.out.println("Troll has won");
            } else {
                System.out.println("Person has won");
            }
            System.exit(0);
        } else if (currentLevel.levelComplete()) {
            if (levelNumber == 2) {
                System.out.println("Advance to level 3");
                currentLevel = new Level3(this);
            } else if (levelNumber == 1) {
                System.out.println("Advance to level 2");
                currentLevel = new Level2(this);
            }
            // if conditions are met, the new level is set.
            currentLevel.stop();
            view.setWorld(currentLevel);
            currentLevel.populate();
            personController.setPerson(currentLevel.getPlayer());
            trollController.setTroll(currentLevel.getTroll());
            currentLevel.start();
            levelNumber++;
            //need to set background after as level`number needs to increment first.
            view.setBackground();
        }
    }

    /**
     * adding pause method so that it can be implemented onto the GUI buttons.
     * this will pause the game at it's current state.
     */
    public void pause() {
        currentLevel.stop();
    }

    /**
     * adding restart method so that it can be implemented onto the GUI buttons.
     * this will restart the game from it's paused status
     */
    public void restart() {
        currentLevel.start();
    }

    /**
     * receives the current level number within the game
     *
     * @return levelNumber
     */
    public static int getLevelNumber() {
        return levelNumber;
    }

    /**
     * sets the game level number
     * @param levelNumber
     */
    public static void setLevelNumber(int levelNumber) {
        Game.levelNumber = levelNumber;
    }


}

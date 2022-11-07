package spark;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * The framing (pun intended) of the game
 */
public class SparkFrame
{
    /*Attributes*/
    private GameFile GAME;              //The game file of the game
    private MainMenu mainMenu;          //The main menu of the game

    /**
     * The Main Menu of the game
     */
    private class MainMenu extends JFrame
    {
        /*Attributes*/

        private JButton newGame = new JButton("New Game");   //Button for new game
        private JButton loadGame = new JButton("Load Game"); //Button for load game
        private newGAME newGAME = new newGAME();                                  //The New Game screen
        private loadGAME loadGAME = new loadGAME();                                //The Load Game screeen

        /*Methods*/
        private void initComponents()
        {
            this.setLayout(new GridLayout(2, 1));

            newGame.addActionListener(e ->
            {
                newGAME.setVisible(true);
                loadGAME.setVisible(false);
            });
            loadGame.addActionListener(e ->
            {
                loadGAME.setVisible(true);
                newGAME.setVisible(false);
            });

            this.add(newGame);
            this.add(loadGame);
        }


        /**
         * Class for the new game selection screen
         */
        private class newGAME extends JFrame
        {
            /*Attributes*/
            JTextField filename = new JTextField(20);
            JButton start = new JButton("New Game");

            /*Methods*/

            /**
             * Constructor
             */
            public newGAME()
            {
                this.initComponents();
            }

            /**
             * Initializes the components
             */
            private void initComponents()
            {
                this.setLayout(new BorderLayout());

                JPanel panel = new JPanel();
                panel.add(filename);

                start.addActionListener(e ->
                {
                    try
                    {
                        GAME.newGame(filename.getText());
                    } catch (IOException ex)
                    {
                        JOptionPane.showMessageDialog(newGame, "No such file! Try again.");
                    }
                });

                panel.add(start);

                this.add(panel, BorderLayout.CENTER);
            }
        }

        /**
         * Class for the load game selection screen
         */
        private class loadGAME extends JFrame
        {
            /*Attributes*/
            private JButton load = new JButton("Load");       //Load button
            private JComboBox options;                            //List of the available game files

            /*Methods*/

            /**
             * Constructor
             */
            public loadGAME()
            {
                this.initComponents();
            }

            /**
             * Initializes the components
             */
            private void initComponents()
            {
                this.setLayout(new BorderLayout());

                JPanel panel = new JPanel();
                File location = new File(System.getProperty("user.dir"));
                File[] files = location.listFiles();
                options = new JComboBox(files);
                panel.add(options);

                load.addActionListener(e ->
                {
                    try
                    {
                        GAME.loadGame((File) options.getSelectedItem());
                    } catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog(newGame, "Can't open file! Try again.");
                    }
                });
                panel.add(load);
                this.add(panel, BorderLayout.CENTER);
            }
        }
    }

    /**
     * The Game Frame itself
     * */
    class
}

package spark;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * The framing (pun intended) of the game
 */
public class SparkFrame
{
    /*Methods*/

    public void initComponents()
    {
        mainMenu = new MainMenu();
        mainMenu.setVisible(true);
    }

    /*Attributes*/
    private GameFile GAME = new GameFile();              //The game file of the game
    private MainMenu mainMenu;          //The main menu of the game
    private GameFrame gameFrame;        //The game frame

    /**
     * The Main Menu of the game
     */
    private class MainMenu extends JFrame
    {
        /*Attributes*/

        private JButton newGame = new JButton("New Game");   //Button for new game
        private JButton loadGame = new JButton("Load Game"); //Button for load game
        private newGAME newGAME = new newGAME();                 //The New Game screen
        private loadGAME loadGAME = new loadGAME();              //The Load Game screeen

        /*Methods*/
        public MainMenu()
        {
            super("Spark - Main menu");
            this.initComponents();
        }

        private void initComponents()
        {
            this.setLayout(new GridLayout(2, 1));

            newGame.addActionListener(e ->
            {
                newGAME.setVisible(true);
            });
            loadGame.addActionListener(e ->
            {
                loadGAME.setVisible(true);
            });

            this.add(newGame);
            this.add(loadGame);

            this.setSize(new Dimension(400, 300));
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setLocationRelativeTo(null);
        }


        /**
         * Class for the new game selection screen
         */
        private class newGAME extends JFrame
        {
            /*Attributes*/
            JTextField filename = new JTextField(20);  //Filename textbox
            JButton start = new JButton("New Game");     //Button for starting a new game

            /*Methods*/

            /**
             * Constructor
             */
            public newGAME()
            {
                super("Spark - Start new game");
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
                this.setSize(new Dimension(400, 80));
                this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                this.setResizable(false);
                this.setLocationRelativeTo(null);
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
                super("Spark - Load existing game");
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
                this.setSize(new Dimension(400, 80));
                this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                this.setResizable(false);
                this.setLocationRelativeTo(null);
            }
        }
    }

    /**
     * The Game Frame itself
     */
    class GameFrame extends JFrame
    {
        /*Attributes*/
        private JTextPane quest_desc;   //A textpane for the quest description
        private JButton A_button;       //Button for choosing transition A
        private JButton B_button;       //Button for choosing transition B

        /*Methods*/
        public GameFrame()
        {
            this.initComponents();
        }

        private void initComponents()
        {

        }
    }
}

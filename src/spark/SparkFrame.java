package spark;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * The framing (pun intended) of the game
 */
public class SparkFrame
{
    /*Methods*/

    public SparkFrame()
    {
        this.initComponents();
    }

    private void initComponents()
    {
        mainMenu.setVisible(true);
    }

    /*Attributes*/
    private GameFile GAME = new GameFile();              //The game file of the game (and also the game logic)
    private MainMenu mainMenu = new MainMenu();          //The main menu of the game
    private GameFrame gameFrame = new GameFrame();       //The game frame

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

                        this.setVisible(false);
                        mainMenu.setVisible(false);

                        gameFrame.setVisible(true);
                        gameFrame.updateComponents();
                    } catch (IOException ex)
                    {
                        JOptionPane.showMessageDialog(newGame, "No such file! Try again.");
                    } catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog(newGame, "Unknown error occurred! Try again.");
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
                File[] files = location.listFiles((idc, fname) -> fname.endsWith(".sprkdt"));

                options = new JComboBox(files);

                panel.add(options);


                load.addActionListener(e ->
                {
                    try
                    {
                        GAME.loadGame((File) options.getSelectedItem());
                        this.setVisible(false);
                        mainMenu.setVisible(false);

                        gameFrame.setVisible(true);
                        gameFrame.updateComponents();
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
        private JTextArea quest_desc = new JTextArea("%placeholder%");      //A textpane for the quest description
        private JButton A_button = new JButton("%placeholder%");       //Button for choosing transition A
        private JButton B_button = new JButton("%placeholder%");       //Button for choosing transition B

        /*Methods*/
        public GameFrame()
        {
            super("Spark");
            this.initComponents();
        }

        private void initComponents()
        {
            this.setLayout(new BorderLayout());
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 2));

            /*Restrictions*/
            quest_desc.setEditable(false);

            this.add(quest_desc);
            panel.add(A_button);
            panel.add(B_button);
            this.add(panel, BorderLayout.PAGE_END);

            this.setSize(new Dimension(600, 450));
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setLocationRelativeTo(null);
        }

        private void updateComponents()
        {
            QuestQueue queue = GAME.getQueue();

            /*Setting up text*/
            quest_desc.setText(queue.getCurrent().getDesc());
            A_button.setText(queue.getCurrent().getOptionA());
            B_button.setText(queue.getCurrent().getOptionB());

            /*Cleanup*/
            ActionListener[] alis = A_button.getActionListeners();
            for (ActionListener e : alis)
            {
                A_button.removeActionListener(e);
            }

            ActionListener[] blis = B_button.getActionListeners();
            for (ActionListener e : blis)
            {
                B_button.removeActionListener(e);
            }

            /*Setting up the good actionlisteners*/
            A_button.addActionListener(e ->
            {
                queue.chooseA();
                this.updateComponents();
            });

            B_button.addActionListener(e ->
            {
                queue.chooseB();
                this.updateComponents();
            });
        }
    }
}

package spark;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

/**
 * The framing (pun intended) of the game
 */
public class SparkFrame
{
    /*Attributes*/
    private final GameFile GAME = new GameFile();                               //The game file of the game (and also the game logic)
    private final MainMenu mainMenu = new MainMenu();                           //The main menu of the game
    private final GameFrame gameFrame = new GameFrame();                        //The game frame

    /*Methods*/

    /**
     * Constructor
     */
    public SparkFrame()
    {
        /*Registering the custom font*/
        try
        {
            String sep = FileSystems.getDefault().getSeparator();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("bin" + sep + "ttf" + sep + "VCR_OSD_MONO_1.001.ttf")));
        } catch (Exception e)
        {
            /*progress anyway*/
        }
        //SRC:  https://docs.oracle.com/javase/tutorial/2d/text/fonts.html

        this.initComponents();
    }

    /**
     * Initializes the components
     */
    private void initComponents()
    {
        mainMenu.setVisible(true);
    }

    /**
     * The Main Menu of the game
     */
    private class MainMenu extends JFrame
    {
        /*Attributes*/

        private final JButton newGame = new JButton();                 //Button for new game
        private final JButton loadGame = new JButton();                //Button for load game
        private final newGAME newGAME = new newGAME();                 //The New Game screen
        private final loadGAME loadGAME = new loadGAME();              //The Load Game screen

        /*Methods*/

        /**
         * Constructor
         */
        public MainMenu()
        {
            super("Spark - Main menu");
            this.initComponents();
        }

        /**
         * Initializes the components
         */
        private void initComponents()
        {
            /*Magic Constants*/
            float[] hsb = {0.0f, 0.0f, 0.93333334f};     //HSB value of the background colour

            /*File-separator*/
            String sep = FileSystems.getDefault().getSeparator();


            /*Init with Grid*/
            this.setLayout(new GridLayout(3, 1));

            newGame.addActionListener(e ->
            {
                newGAME.setVisible(true);
            });
            loadGame.addActionListener(e ->
            {
                loadGAME.setVisible(true);
            });

            /*Menu Picture*/
            JPanel picture = new JPanel(new BorderLayout());

            /*Custom labels in case of images not loading*/
            JLabel spark_menu = new JLabel("SPARK", SwingConstants.CENTER);
            spark_menu.setFont(new Font("VCR OSD MONO", Font.PLAIN, 35));

            JLabel new_game = new JLabel("New Game", SwingConstants.CENTER);
            new_game.setFont(new Font("VCR OSD MONO", Font.PLAIN, 20));

            JLabel load_game = new JLabel("Load Game", SwingConstants.CENTER);
            load_game.setFont(new Font("VCR OSD MONO", Font.PLAIN, 20));

            /*Trying to set up images*/
            /*Main Menu image*/
            try
            {
                spark_menu = new JLabel(new ImageIcon(ImageIO.read(new File("bin" + sep + "img" + sep + "menu.png"))));
            } catch (IOException e)
            {
                /*Progress anyway*/
            }
            picture.add(spark_menu, BorderLayout.CENTER);

            /*New Game button*/
            try
            {
                new_game = new JLabel(new ImageIcon(ImageIO.read(new File("bin" + sep + "img" + sep + "new_game.png"))));
            } catch (IOException e)
            {
                /*Progress anyway*/
            }
            newGame.add(new_game);
            newGame.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));  //color setting

            /*Load Game Button*/
            try
            {
                load_game = new JLabel(new ImageIcon(ImageIO.read(new File("bin" + sep + "img" + sep + "load_game.png"))));
            } catch (IOException e)
            {
                /*Progress anyway*/
            }
            loadGame.add(load_game);
            loadGame.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));  //color setting

            this.add(picture);
            this.add(newGame);
            this.add(loadGame);

            this.setSize(new Dimension(420, 300));
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
            JTextField filename = new JTextField(20);                  //Filename textbox
            JButton start = new SparkJButton("New Game", 15);      //Button for starting a new game

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

                filename.addActionListener(e ->
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
                        JOptionPane.showMessageDialog(newGame, "      No such file!  Try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog(newGame, "Unknown error occurred! Try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

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
                        JOptionPane.showMessageDialog(newGame, "      No such file!  Try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog(newGame, "Unknown error occurred! Try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

                panel.add(start);

                /*Setting frame parameters*/
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
            private final JButton load = new SparkJButton("Load", 15);       //Load button
            private JComboBox options;                                                   //List of the available game files

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

                /*Magic...*/
                String savepath = new File(System.getProperty("user.dir")).getPath();
                String sep = FileSystems.getDefault().getSeparator(); //file separator
                savepath += sep + "bin" + sep + "save" + sep;

                final String finalpath = savepath;
                File location = new File(finalpath);

                File[] files = location.listFiles((idc, fname) -> fname.endsWith(".sprkdt"));

                if (files == null)
                {
                    location.mkdir();
                    files = new File[]{};
                }

                String[] saves = new String[files.length];
                for (int i = 0; i < files.length; i++)
                {
                    saves[i] = files[i].getName();
                }
                /*End of magic*/

                options = new JComboBox(saves);

                panel.add(options);

                load.addActionListener(e ->
                {
                    String path = finalpath + options.getSelectedItem();
                    try
                    {
                        GAME.loadGame(new File(path));
                        this.setVisible(false);
                        mainMenu.setVisible(false);

                        gameFrame.setVisible(true);
                        gameFrame.updateComponents();
                    } catch (IOException ex)
                    {
                        JOptionPane.showMessageDialog(newGame, "   Can't open file!  Try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog(newGame, "Unknown error occurred! Try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

                panel.add(load);

                /*Setting frame parameters*/
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
        private final JTextArea quest_desc = new JTextArea("%placeholder%");                      //A textpane for the quest description
        private final JButton A_button = new SparkJButton("%placeholder%", 18);       //Button for choosing transition A
        private final JButton B_button = new SparkJButton("%placeholder%", 18);       //Button for choosing transition B
        private final JMenuBar menubar = new JMenuBar();                                          //Menubar of the frame

        /*Methods*/

        /**
         * Constructor
         */
        public GameFrame()
        {
            super("Spark");
            this.initComponents();
        }

        /**
         * Initializes the components
         */
        private void initComponents()
        {
            this.setLayout(new BorderLayout());
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 2));

            /*Restrictions*/
            quest_desc.setEditable(false);
            quest_desc.setLineWrap(true);
            quest_desc.setWrapStyleWord(true);

            /*Menubar*/
            JMenu file = new JMenu("File");
            menubar.add(file);
            JMenuItem save = new JMenuItem("Save game");
            JMenuItem exit = new JMenuItem("Exit without saving");

            /*Save dialog box*/
            save.addActionListener(e ->
            {
                try
                {
                    GAME.saveGame(JOptionPane.showInputDialog(this, "What should be the save file's name?", "Save Game", JOptionPane.QUESTION_MESSAGE));
                } catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(this, "File couldn't be saved! Try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            /*Exit game*/
            exit.addActionListener(e -> System.exit(0));

            /*Font*/
            quest_desc.setFont(new Font("Arial", Font.PLAIN, 18));

            /*Adding the components*/
            file.add(save);
            file.add(exit);
            this.add(quest_desc);
            panel.add(A_button);
            panel.add(B_button);
            this.add(panel, BorderLayout.PAGE_END);
            this.add(menubar, BorderLayout.NORTH);

            /*Setting frame parameters*/
            this.setSize(new Dimension(600, 450));
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setLocationRelativeTo(null);
        }

        /**
         * Updates the components
         */
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

            /*Setting up the good action listeners*/
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

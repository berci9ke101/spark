package spark;

import spark.quest.*;

import java.io.*;
import java.nio.file.FileSystems;

/**
 * A class for the game file and its manipulation
 */
public class GameFile
{
    /*Attributes*/
    private QuestQueue queue;           //The queue for the Quests

    /*Methods*/

    /**
     * Getter for the queue
     *
     * @return the queue of the game
     */
    public QuestQueue getQueue()
    {
        return queue;
    }

    /**
     * Starts a new game from a given file.
     *
     * @param name The name of the gamefile
     * @throws IOException If the file doesn't exist
     */
    public void newGame(String name) throws IOException
    {
        BufferedReader read = new BufferedReader(new FileReader(name));
        queue = new QuestQueue();
        String line;
        while ((line = read.readLine()) != null)
        {
            if (!line.equals(""))
            {
                String[] data = line.split(";");

                int ID = Integer.parseInt(data[0]);
                String desc = data[2];
                String optA = data[3];
                int jmpA = Integer.parseInt(data[4]);
                String optB = data[5];
                int jmpB = Integer.parseInt(data[6]);

                switch (data[1].toLowerCase())
                {
                    case "b":
                        queue.add(new BasicQuest(ID, desc, optA, optB, jmpA, jmpB));
                        break;
                    case "v":
                        int altjmp = Integer.parseInt(data[8]);
                        String altdsc = data[7];
                        queue.add(new VisitableQuest(ID, desc, optA, optB, jmpA, jmpB, altdsc, altjmp));
                        break;
                    case "r":
                        queue.add(new RandomQuest(ID, desc, optA, optB, jmpA, jmpB));
                        break;
                }
            }
        }
        read.close();

        queue.sort();
    }


    /**
     * Loads a savegame from a given file.
     *
     * @param name The file reference of the save file
     * @throws ClassNotFoundException If the object isn't QuestQueue type
     * @throws IOException            If the file doesn't exist
     */
    public void loadGame(File name) throws IOException, ClassNotFoundException
    {
        queue = new QuestQueue();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name));
        queue = (QuestQueue) ois.readObject();
        ois.close();
        queue.sort();
    }

    /**
     * Saves out the current game to a specified file
     *
     * @param name The name of the file
     * @throws IOException If the file doesn't exist
     */
    public void saveGame(String name) throws IOException
    {
        String sep = FileSystems.getDefault().getSeparator();

        FileOutputStream fio = new FileOutputStream("bin" + sep + "save" + sep + name + ".sprkdt");

        ObjectOutputStream oos = new ObjectOutputStream(fio);
        oos.writeObject(queue);
        oos.close();
    }
}

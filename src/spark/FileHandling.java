package spark;

import spark.quest.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.List;

public class FileHandling
{
    /*Attributes*/
    private QuestQueue queue;           //The queue for the Quests

    /*Methods*/

    public void newGameFromFile(String name)
    {
        try
        {
            BufferedReader read = new BufferedReader(new FileReader(name));
            queue = new QuestQueue();
            String line;
            while ((line = read.readLine()) != null)
            {
                String[] data = line.split(";");

                int ID = Integer.parseInt(data[0]);
                String desc = data[2];
                String optA = data[3];
                int jmpA = Integer.parseInt(data[4]);
                String optB = data[5];
                int jmpB = Integer.parseInt(data[6]);

                switch (data[1])
                {
                    case "B":
                        queue.add(new BasicQuest(ID, desc, optA, optB, jmpA, jmpB));
                        break;
                    case "V":
                        int altjmp = Integer.parseInt(data[8]);
                        String altdsc = data[7];
                        queue.add(new VisitableQuest(ID, desc, optA, optB, jmpA, jmpB, altdsc, altjmp));
                        break;
                    case "R":
                        queue.add(new RandomQuest(ID, desc, optA, optB, jmpA, jmpB));
                        break;
                }
            }


            read.close();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

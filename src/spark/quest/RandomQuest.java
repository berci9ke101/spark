package spark.quest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomQuest extends Quest
{
    /*Attributes*/
    private List<String> descriptions;

    /*Methods*/

    /**
     * Constructor
     *
     * @param ID      ID of the Quest
     * @param Desc    Description of the Quest
     * @param OptionA The text for option A
     * @param OptionB The text for option B
     * @param JumpA   The transition ID for option A
     * @param JumpB   The transition ID for option A
     */
    public RandomQuest(long ID, String Desc, String OptionA, String OptionB, long JumpA, long JumpB)
    {
        super(ID, Desc, OptionA, OptionB, JumpA, JumpB);

        ///Getting the string with the '#' divider characters
        String tmp = super.getDesc();
        ///Splitting it up and storing them in descriptions
        descriptions = new ArrayList<>(List.of(tmp.split("#")));
    }

    /**
     * Getter for description
     *
     * @return One of the random texts from the quest
     */
    @Override
    public String getDesc()
    {
        Random rnd = new Random();
        return descriptions.get(rnd.nextInt(0, descriptions.size()));
    }
}
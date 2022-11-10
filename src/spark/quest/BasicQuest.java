package spark.quest;

/**
 * A simple Quest class, that fully implements the Quest class
 * */
public class BasicQuest extends Quest
{
    /*Methods*/

    /**
     * Constructor
     *
     * @param ID ID of the Quest
     * @param Desc Description of the Quest
     * @param OptionA The text for option A
     * @param OptionB The text for option B
     * @param JumpA The transition ID for option A
     * @param JumpB The transition ID for option A
     */
    public BasicQuest(int ID, String Desc, String OptionA, String OptionB, int JumpA, int JumpB)
    {
        super(ID, Desc, OptionA, OptionB, JumpA, JumpB);
    }
}

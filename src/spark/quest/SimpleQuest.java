package spark.quest;

public class SimpleQuest extends Quest
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
    public SimpleQuest(long ID, String Desc, String OptionA, String OptionB, long JumpA, long JumpB)
    {
        super(ID, Desc, OptionA, OptionB, JumpA, JumpB);
    }
}

package spark.quest;

/**
 * Default abstract Quest class
 * */
abstract public class Quest
{
    /*Attributes*/

    private int ID = -1;           //The id of the quest
    private String Desc = "";      //The description of the quest
    private String OptionA = "";   //The text for option A
    private String OptionB = "";   //The text for option B
    private int JumpA = -1;        //The transition ID for option A
    private int JumpB = -1;        //The transition ID for option B

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
    public Quest(int ID, String Desc, String OptionA, String OptionB, int JumpA, int JumpB)
    {
        this.ID = ID;
        this.Desc = Desc;
        this.OptionA = OptionA;
        this.OptionB = OptionB;
        this.JumpA = JumpA;
        this.JumpB = JumpB;
    }

    /**
     * Getter for ID
     *
     * @return ID of the Quest
     */
    public int getID()
    {
        return ID;
    }

    /**
     * Getter for Desc
     *
     * @return Description of the Quest
     */
    public String getDesc()
    {
        return Desc;
    }

    /**
     * Getter for OptionA
     *
     * @return The text for option A
     */
    public String getOptionA()
    {
        return OptionA;
    }

    /**
     * Getter for OptionB
     *
     * @return The text for option B
     */
    public String getOptionB()
    {
        return OptionB;
    }

    /**
     * Getter for JumpA
     *
     * @return The transition ID for option A
     */
    public int getJumpA()
    {
        return JumpA;
    }

    /**
     * Getter for JumpB
     *
     * @return The transition ID for option B
     */
    public int getJumpB()
    {
        return JumpB;
    }
}

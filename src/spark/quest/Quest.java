package spark.quest;

abstract public class Quest
{
    /*Attributes*/

    private long ID = -1;           //The id of the quest
    private String Desc = "";       //The description of the quest
    private String OptionA = "";    //The text for option A
    private String OptionB = "";    //The text for option B
    private long JumpA = -1;        //The transition id for option A
    private long JumpB = -1;        //The transition id for option B

    /*Methods*/

    /*Setters, getters*/

    /**
     * Getter for ID
     */
    public long getID()
    {
        return ID;
    }

    /**
     * Getter for Desc
     */
    public String getDesc()
    {
        return Desc;
    }

    /**
     * Getter for OptionA
     */
    public String getOptionA()
    {
        return OptionA;
    }

    /**
     * Getter for OptionB
     */
    public String getOptionB()
    {
        return OptionB;
    }

    /**
     * Getter for JumpA
     */
    public long getJumpA()
    {
        return JumpA;
    }

    /**
     * Getter for JumpB
     */
    public long getJumpB()
    {
        return JumpB;
    }
}

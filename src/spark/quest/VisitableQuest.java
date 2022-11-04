package spark.quest;

/**
 * A Quest that, displays an alternative description and offers an alternative transition if it was visited
 * */
public class VisitableQuest extends Quest
{
    /*Attributes*/

    private String alternatedesc;       //The alternative description of the Quest, which is displayed only when the quest has been visited
    private boolean visited = false;    //Indicates whether the Quest was visited or not
    private long alternatejump;         //This is the alternative ID the Quest alternate jumps to if it was visited

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
    public VisitableQuest(long ID, String Desc, String OptionA, String OptionB, long JumpA, long JumpB, String alternatedesc, long alternatejump)
    {
        super(ID, Desc, OptionA, OptionB, JumpA, JumpB);
        this.alternatedesc = alternatedesc;
        this.alternatejump = alternatejump;
    }

    /**
     * Getter for Description
     *
     * @return The alternative description of the Quest if it was visited, or the super's description if it wasn't
     */
    @Override
    public String getDesc()
    {
        if (visited)
        {
            return alternatedesc;
        }
        this.visited = true;
        return super.getDesc();
    }

    /**
     * Getter for JumpA ID
     *
     * @return The ID where the Quest alternate jumps to or super's A option's ID
     */
    @Override
    public long getJumpA()
    {
        if (visited)
        {
            return alternatejump;
        }
        this.visited = true;
        return super.getJumpA();
    }

    /**
     * Getter for JumpB ID
     *
     * @return The ID where the Quest alternate jumps to or super's B option's ID
     */
    @Override
    public long getJumpB()
    {
        if (visited)
        {
            return alternatejump;
        }
        return super.getJumpB();
    }
}

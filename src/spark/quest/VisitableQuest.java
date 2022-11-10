package spark.quest;

import java.util.Random;

/**
 * A Quest that, displays an alternative description and offers an alternative transition if it was visited
 */
public class VisitableQuest extends Quest
{
    /*Attributes*/

    private String alternatedesc;       //The alternative description of the Quest, which is displayed only when the quest has been visited
    private boolean visited = false;    //Indicates whether the Quest was visited or not
    private int alternatejump;          //This is the alternative ID the Quest alternate jumps to if it was visited

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
    public VisitableQuest(int ID, String Desc, String OptionA, String OptionB, int JumpA, int JumpB, String alternatedesc, int alternatejump)
    {
        super(ID, Desc, OptionA, OptionB, JumpA, JumpB);
        this.alternatedesc = alternatedesc;
        this.alternatejump = alternatejump;
    }

    /**
     * Getter for OptionA
     *
     * @return The text for option A
     */
    @Override
    public String getOptionA()
    {
        if (visited)
        {
            Random rnd = new Random();
            String[] opts = {"Head back.", "Leave.", "Go back.", "Go away."};
            return opts[rnd.nextInt() % 4];
        }
        return super.getOptionA();
    }

    /**
     * Getter for OptionB
     *
     * @return The text for option B
     */
    @Override
    public String getOptionB()
    {
        if (visited)
        {
            Random rnd = new Random();
            String[] opts = {"Head back.", "Leave.", "Go back.", "Go away."};
            return opts[rnd.nextInt() % 4];
        }
        return super.getOptionB();
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
        return super.getDesc();
    }

    /**
     * Getter for JumpA ID
     *
     * @return The ID where the Quest alternate jumps to or super's A option's ID
     * @\\ If it was the first visit it sets the visited attribute to true.
     */
    @Override
    public int getJumpA()
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
     * @\\ If it was the first visit it sets the visited attribute to true.
     */
    @Override
    public int getJumpB()
    {
        if (visited)
        {
            return alternatejump;
        }
        this.visited = true;
        return super.getJumpB();
    }
}

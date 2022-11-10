package spark;

import spark.quest.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class stores the Quests and manages the transitions
 */
public class QuestQueue implements Serializable
{
    /*Attributes*/

    private List<Quest> queue = new ArrayList<>();      //The list that stores the Quest objects
    private int current_state = 0;                      //Stores the state, that the queue is currently in

    /*Methods*/

    /**
     * Stores a Quest
     *
     * @param quest The Quest object, that should be stored
     */
    public void add(Quest quest)
    {
        queue.add(quest);
    }

    /**
     * Sorts the queue
     */
    public void sort()
    {
        Collections.sort(queue, (q1, q2) -> (q1.getID() - q2.getID()));
    }

    /**
     * Chooses the current Quest's A's transition and transitions there.
     * If the transition ID is '-1' exits.
     */
    public void chooseA()
    {
        int transfer = this.at(current_state).getJumpA();
        if (transfer != -1)
        {
            current_state = transfer;
        }
        else
        {
            System.exit(0);
        }
    }

    /**
     * Chooses the current Quest's B's transition and transitions there
     * If the transition ID is '-1' exits.
     */
    public void chooseB()
    {
        int transfer = this.at(current_state).getJumpB();
        if (transfer != -1)
        {
            current_state = transfer;
        }
        else
        {
            System.exit(0);
        }
    }

    /**
     * Returns the current state
     *
     * @return The current state
     */
    public int getCurrent_state()
    {
        return current_state;
    }

    /**
     * Returns the Quest object at the given index
     *
     * @return The Quest object at the given index
     */
    private Quest at(int index)
    {
        return queue.get(index);
    }

    /**
     * Returns the current quest
     *
     * @return The current Quest object
     */
    public Quest getCurrent()
    {
        return this.at(current_state);
    }
}

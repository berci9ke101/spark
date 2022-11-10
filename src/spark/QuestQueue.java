package spark;

import spark.quest.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class stores the Quests and manages the transitions
 */
public class QuestQueue
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
     * Chooses the current Quest's A's transition and transitions there
     */
    public void chooseA()
    {
        current_state = this.at(current_state).getJumpA();
    }

    /**
     * Chooses the current Quest's B's transition and transitions there
     */
    public void chooseB()
    {
        current_state = this.at(current_state).getJumpB();
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

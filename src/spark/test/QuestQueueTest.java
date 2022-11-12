package spark.test;

import org.junit.*;
import spark.*;
import spark.quest.*;

public class QuestQueueTest
{
    private QuestQueue queue;
    private BasicQuest basic;
    private RandomQuest random;

    @Before
    public void init()
    {
        queue = new QuestQueue();
        basic = new BasicQuest(0, "", "", "", 1, -1);
        random = new RandomQuest(1, "", "", "", 0, -1);
    }

    @Test
    public void testQueueAdd()
    {
        queue.add(basic);
        queue.add(random);

        Assert.assertEquals(basic, queue.getCurrent()); //Testing whether the 0th element of the list is basic
    }

    @Test
    public void testChoose()
    {
        queue.add(basic);
        queue.add(random);

        queue.chooseA();    //Choosing A with value '1' of the JumpA option of the basic quest causes the queue to go to the random quest
        Assert.assertEquals(random, queue.getCurrent());
        queue.chooseA();    //Choosing A with value '0' of the JumpA option of the random quest causes the queue to go to the basic quest
        Assert.assertEquals(basic, queue.getCurrent());
    }

    @Test
    public void testSort()
    {
        /*Adding the quests in reverse order*/
        queue.add(random);
        queue.add(basic);


        Assert.assertEquals(random, queue.getCurrent()); //Random is the 0th element
        queue.sort();
        Assert.assertEquals(basic, queue.getCurrent()); //Basic is the 0th element now
    }
}

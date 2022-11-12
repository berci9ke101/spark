package spark.test;

import org.junit.*;
import spark.quest.*;

public class QuestTest
{
    @Test
    public void testAlternateDescription()
    {
        Quest altquest = new VisitableQuest(1, "description", "", "", 0, -1, "alternate", -1);

        Assert.assertEquals("description", altquest.getDesc());
        altquest.getJumpA(); //this triggers the visited flag
        Assert.assertEquals("alternate", altquest.getDesc());
    }

    @Test
    public void testAlternateJump()
    {
        Quest altquest = new VisitableQuest(1, "", "", "", 0, -1, "", 2);
        Assert.assertEquals(0, altquest.getJumpA()); //this triggers the visited flag
        Assert.assertEquals(2, altquest.getJumpB());
    }
}

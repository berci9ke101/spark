package spark.test;

import org.junit.*;
import spark.*;
import spark.quest.*;

import java.io.IOException;

public class GameFileTest
{
    private GameFile game;

    @Before
    public void init()
    {
        game = new GameFile();
    }

    @Test(expected = IOException.class)
    public void testNewGameThrow() throws Exception
    {
        game.newGame("");
    }

    @Test(expected = IOException.class)
    public void testLoadGameThrow() throws Exception
    {
        game.newGame("");
    }

    @Test(expected = IOException.class)
    public void testSaveGameThrow() throws Exception
    {
        //Tested on Windows
        game.saveGame("/=/=/");
    }

    @Test(expected = Test.None.class /* no exception expected */)
    public void testNewGameNoThrow() throws Exception
    {
        //Requires test_load file to work
        game.newGame("load");
    }

    @Test(expected = Test.None.class /* no exception expected */)
    public void testSaveGameNoThrow() throws Exception
    {
        game.saveGame("save");
    }
}

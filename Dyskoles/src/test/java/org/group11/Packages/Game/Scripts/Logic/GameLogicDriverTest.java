package org.group11.Packages.Game.Scripts.Logic;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Character_Scripts.Boss;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.group11.Packages.Game.Scripts.Character_Scripts.Minion;
import org.group11.Packages.Game.Scripts.Item_Scripts.RegenHeart;
import org.group11.Packages.Game.Scripts.Item_Scripts.RegenHeartTest;
import org.group11.Packages.Game.Scripts.Levels.TestLevel;
import org.group11.Packages.Game.Scripts.Levels.TestRoom;
import org.group11.Packages.Game.Scripts.Levels.TestRoom2;
import org.junit.Before;
import org.junit.Test;

public class GameLogicDriverTest {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    boolean everythingInstantiated = false;
    private Engine engine;
    private Scene scene;

    //******************************************************************************************************************
    //* setup
    //******************************************************************************************************************
    private class SetupClass extends GameObject {
        @Override
        public void start() {
            // General instantiations
            GameLogicDriver GLD = GameLogicDriver.getInstance();
            scene.Instantiate(GLD);

            everythingInstantiated = true;
        }
    }

    @Before
    public void setup() {
        engine = new Engine();
        engine.start();
        scene = Scene.get_scene();

        SetupClass setupClass = new SetupClass();
        scene.Instantiate(setupClass);

        while (!everythingInstantiated) {
            System.out.print("");
        }

        GameLogicDriver.clearEverything();
    }

    //******************************************************************************************************************
    //* tests
    //******************************************************************************************************************
    /**
     * Tests the method that adds a Level to the game
     */
    @Test
    public void set_gameLevelTest() {
        TestRoom level1 = new TestRoom();

        assert(GameLogicDriver.set_gameLevelAtStage(level1, 1));
        assert(GameLogicDriver._gameLevelList.get(0) == level1);
    }

    /**
     * Tests the method that overwrites a Level at a certain stage
     */
    @Test
    public void overwrite_gameLevelTest() {
        TestRoom level1 = new TestRoom();
        TestRoom2 level2 = new TestRoom2();
        TestLevel altLevel1 = new TestLevel();

        assert(GameLogicDriver.set_gameLevelAtStage(level1, 1));
        assert(GameLogicDriver.set_gameLevelAtStage(level2, 2));
        // Should overwrite level1
        assert(GameLogicDriver.set_gameLevelAtStage(altLevel1, 1));

        assert(GameLogicDriver._gameLevelList.get(0) == altLevel1);
        assert(GameLogicDriver._gameLevelList.get(1) == level2);
    }

    /**
     * Tests the method that returns what the game's Level is at a certain stage
     */
    @Test
    public void get_gameLevelTest() {
        TestRoom level1 = new TestRoom();
        GameLogicDriver.set_gameLevelAtStage(level1, 1);

        assert(GameLogicDriver.get_gameLevelAtStage(1) == level1);
        // Testing out of bounds
        assert(GameLogicDriver.get_gameLevelAtStage(2) == null);
        assert(GameLogicDriver.get_gameLevelAtStage(0) == null);
    }

    /**
     * Tests the GameLogicDriver's ability to load a new Level
     */
    @Test
    public void loadNewLevelTest() {
        TestLevel level1 = new TestLevel();
        GameLogicDriver.set_gameLevelAtStage(level1, 1);
        GameLogicDriver.loadNewLevel();

        assert(GameLogicDriver._playerCharacters == level1._players);
        assert(GameLogicDriver._enemyCharacters == level1._enemies);
        assert(GameLogicDriver._items == level1._items);

        // test if sprites enabled?
    }

    @Test
    public void startNewLevelTest() {
        // TODO: implement
    }

    /**
     * Tests the method to remove an Enemy from the game
     */
    @Test
    public void removeEnemyTest() {
        Minion minion = new Minion();
        Boss boss = new Boss();
        GameLogicDriver._enemyCharacters.add(minion);
        GameLogicDriver._enemyCharacters.add(boss);

        GameLogicDriver.removeEnemy(minion);
        assert(GameLogicDriver._enemyCharacters.size() == 1);

        GameLogicDriver.removeEnemy(boss);
        assert(GameLogicDriver._enemyCharacters.size() == 0);
    }

    /**
     * Tests the method to check if a MainCharacter can move into a tile when it is a floor tile and is clear of any
     * characters
     */
    @Test
    public void MCCheckMoveClearTest() {
        TestLevel level1 = new TestLevel();
        GameLogicDriver.set_gameLevelAtStage(level1, 1);
        GameLogicDriver.loadNewLevel();

        assert(GameLogicDriver.MCCheckMove(GameLogicDriver._playerCharacters.get(0), new Vector3(1,2,0)));
    }

    /**
     * Tests the method to check if a MainCharacter can move into a tile when it is a floor tile but has an enemy which
     * is killed, thus is clear to move in to
     */
    @Test
    public void MCCheckMoveEnemyClearTest() {
        TestLevel level1 = new TestLevel();
        GameLogicDriver.set_gameLevelAtStage(level1, 1);
        GameLogicDriver.loadNewLevel();
        GameLogicDriver._enemyCharacters.get(0).getStatBlock().set_hp(1);

        assert(GameLogicDriver.MCCheckMove(GameLogicDriver._playerCharacters.get(0), new Vector3(1,2,0)));
    }

    /**
     * Tests the method to check if a MainCharacter can move into a tile when it is a floor tile but has an enemy which
     * is not killed, thus is not clear to move in to
     */
    @Test
    public void MCCheckMoveEnemyNotClearTest() {
        TestLevel level1 = new TestLevel();
        GameLogicDriver.set_gameLevelAtStage(level1, 1);
        GameLogicDriver.loadNewLevel();

        assert(!GameLogicDriver.MCCheckMove(GameLogicDriver._playerCharacters.get(0), new Vector3(1,2,0)));
    }

    /**
     * Tests the method to check if a MainCharacter can move into a tile when it is a wall, thus cannot move in to it
     */
    @Test
    public void MCCheckMoveWallTest() {
        TestLevel level1 = new TestLevel();
        GameLogicDriver.set_gameLevelAtStage(level1, 1);
        GameLogicDriver.loadNewLevel();

        assert(!GameLogicDriver.MCCheckMove(GameLogicDriver._playerCharacters.get(0), new Vector3(-1,2,0)));
    }

    /**
     * Tests what happens when a MainCharacter is on a tile with no item when the method to check for an item is called
     */
    @Test
    public void MCCheckItemNoneTest() {
        int initialItemsNumber = GameLogicDriver._items.size();

        GameLogicDriver.MCCheckItem(GameLogicDriver._playerCharacters.get(0));
        assert (GameLogicDriver._items.size() == initialItemsNumber);
    }

}

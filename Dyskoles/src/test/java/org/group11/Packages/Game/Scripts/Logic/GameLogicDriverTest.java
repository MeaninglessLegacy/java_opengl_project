package org.group11.Packages.Game.Scripts.Logic;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Character_Scripts.Boss;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.group11.Packages.Game.Scripts.Character_Scripts.Minion;
import org.group11.Packages.Game.Scripts.Character_Scripts.Runner;
import org.group11.Packages.Game.Scripts.Item_Scripts.Key;
import org.group11.Packages.Game.Scripts.Item_Scripts.RegenHeart;
import org.group11.Packages.Game.Scripts.Item_Scripts.SpikeTrap;
import org.group11.Packages.Game.Scripts.Levels.TestLevel;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Floor;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Wall;
import org.junit.Before;
import org.junit.Test;

public class GameLogicDriverTest {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    boolean everythingInstantiated = false;
    private Engine engine;
    private Scene scene;

    private TestLevel testLevel;
    private MainCharacter MC;
    private Boss boss;
    private Minion minion;
    private Runner runner;
    private Key key;
    private RegenHeart regenHeart;
    private SpikeTrap spikeTrap;

    private Floor floor;
    private Wall wall;

    //******************************************************************************************************************
    //* setup
    //******************************************************************************************************************
    private class SetupClass extends GameObject {
        @Override
        public void start() {
            System.out.println("Test started");
            // General instantiations
            GameLogicDriver GLD = GameLogicDriver.getInstance();
            scene.Instantiate(GLD);
            GameLogicDriver.scene = Scene.get_scene();

            testLevel = new TestLevel();
            MC = new MainCharacter();
            boss = new Boss();
            minion = new Minion();
            runner = new Runner();
            key = new Key();
            regenHeart = new RegenHeart();
            spikeTrap = new SpikeTrap();
            floor = new Floor();
            wall = new Wall();

            scene.Instantiate(MC);
            scene.Instantiate(boss);
            scene.Instantiate(minion);
            scene.Instantiate(runner);
            scene.Instantiate(key);
            scene.Instantiate(regenHeart);
            scene.Instantiate(spikeTrap);
            scene.Instantiate(floor);
            scene.Instantiate(wall);

            everythingInstantiated = true;
        }
    }

    @Before
    public void setup() {
        if (engine == null) {
            engine = new Engine();
            engine.start();
        }
        if (scene == null) {
            scene = Scene.get_scene();
        }

        if (GameLogicDriver._gameMap != null || GameLogicDriver._playerCharacters.size() != 0 ||
            GameLogicDriver._enemyCharacters.size() != 0 || GameLogicDriver._items.size() != 0 ||
            GameLogicDriver._gameLevelList.size() != 0) {
            GameLogicDriver.clearEverything();
            System.out.println("Cleared everything");
        }

        SetupClass setupClass = new SetupClass();
        scene.Instantiate(setupClass);

        while (!everythingInstantiated) {
            System.out.print("");
        }
    }

    //******************************************************************************************************************
    //* tests
    //******************************************************************************************************************
    /**
     * Tests GameLogicDriver's method to add a Level to the game
     */
    @Test
    public void set_gameLevelTest() {
        TestLevel testLevel1 = new TestLevel();

        assert(GameLogicDriver.set_gameLevelAtStage(testLevel1, 1));
        assert(GameLogicDriver._gameLevelList.get(0) == testLevel1);
    }

    /**
     * Tests GameLogicDriver's method to overwrite a Level at a certain stage
     */
    @Test
    public void overwrite_gameLevelTest() {
        TestLevel testLevel1 = new TestLevel();
        TestLevel testLevel2 = new TestLevel();
        TestLevel testLevel3 = new TestLevel();

        assert(GameLogicDriver.set_gameLevelAtStage(testLevel1, 1));
        assert(GameLogicDriver.set_gameLevelAtStage(testLevel2, 2));
        // Should overwrite testLevel1
        assert(GameLogicDriver.set_gameLevelAtStage(testLevel3, 1));

        assert(GameLogicDriver._gameLevelList.get(0) == testLevel3);
        assert(GameLogicDriver._gameLevelList.get(1) == testLevel2);
    }

    /**
     * Tests GameLogicDriver's method to return what the game's Level is at a certain stage
     */
    @Test
    public void get_gameLevelTest() {
        TestLevel testLevel1 = new TestLevel();

        GameLogicDriver.set_gameLevelAtStage(testLevel1, 1);

        assert(GameLogicDriver.get_gameLevelAtStage(1) == testLevel1);
        // Testing out of bounds
        assert(GameLogicDriver.get_gameLevelAtStage(2) == null);
        assert(GameLogicDriver.get_gameLevelAtStage(0) == null);
    }

    /**
     * Tests GameLogicDriver's method to load a new Level
     */
    @Test
    public void loadNewLevelTest() {
        TestLevel testLevel1 = new TestLevel();

        GameLogicDriver.set_gameLevelAtStage(testLevel1, 1);
        GameLogicDriver.loadNewLevel();

        assert(GameLogicDriver._playerCharacters == testLevel1._players);
        assert(GameLogicDriver._enemyCharacters == testLevel1._enemies);
        assert(GameLogicDriver._items == testLevel1._items);

        // TODO test if sprites enabled?
    }

    @Test
    public void startNewLevelTest() {
        // TODO: implement
    }

    /**
     * Tests GameLogicDriver's method to remove an Enemy from the game
     */
    @Test
    public void removeEnemyTest() {
        Minion minion1 = new Minion();
        Boss boss1 = new Boss();

        GameLogicDriver._enemyCharacters.add(minion1);
        GameLogicDriver._enemyCharacters.add(boss1);

        GameLogicDriver.removeEnemy(minion1);
        assert(GameLogicDriver._enemyCharacters.size() == 1);

        GameLogicDriver.removeEnemy(boss1);
        assert(GameLogicDriver._enemyCharacters.size() == 0);
    }

    /**
     * Tests GameLogicDriver's method to check if a MainCharacter can move into a tile when it is a floor tile and is
     * clear of any characters
     */
    @Test
    public void MCCheckMoveClearTest() {
        TestLevel testLevel1 = new TestLevel();
        MainCharacter MC1 = new MainCharacter(new Vector3(0, 2, 0));

        GameLogicDriver.set_gameLevelAtStage(testLevel1, 1);
        GameLogicDriver.loadNewLevel();
        GameLogicDriver._playerCharacters.add(MC1);

        assert(GameLogicDriver.MCCheckMove(GameLogicDriver._playerCharacters.get(0), new Vector3(1,2,0)));
    }

    /**
     * Tests GameLogicDriver's method to check if a MainCharacter can move into a tile when it is a floor tile but has
     * an enemy which is killed, thus is clear to move in to
     */
    @Test
    public void MCCheckMoveEnemyClearTest() {
        TestLevel testLevel1 = new TestLevel();
        MainCharacter MC1 = new MainCharacter(new Vector3(0, 2, 0));
        Minion minion1 = new Minion(new Vector3(1, 2, 0));
        minion1.getStatBlock().set_hp(1);

        GameLogicDriver.set_gameLevelAtStage(testLevel1, 1);
        GameLogicDriver.loadNewLevel();
        GameLogicDriver._playerCharacters.add(MC1);
        GameLogicDriver._enemyCharacters.add(minion1);

        assert(GameLogicDriver.MCCheckMove(GameLogicDriver._playerCharacters.get(0), new Vector3(1,2,0)));
    }

    /**
     * Tests GameLogicDriver's method to check if a MainCharacter can move into a tile when it is a floor tile but has
     * an enemy which is not killed, thus is not clear to move in to
     */
    @Test
    public void MCCheckMoveEnemyNotClearTest() {
        TestLevel testLevel1 = new TestLevel();
        MainCharacter MC1 = new MainCharacter(new Vector3(0, 2, 0));
        Minion minion1 = new Minion(new Vector3(1, 2, 0));

        GameLogicDriver.set_gameLevelAtStage(testLevel1, 1);
        GameLogicDriver.loadNewLevel();
        GameLogicDriver._playerCharacters.add(MC1);
        GameLogicDriver._enemyCharacters.add(minion1);

        assert(!GameLogicDriver.MCCheckMove(GameLogicDriver._playerCharacters.get(0), new Vector3(1,2,0)));
    }

    /**
     * Tests GameLogicDriver's method to check if a MainCharacter can move into a tile when it is a wall, thus cannot
     * move in to it
     */
    @Test
    public void MCCheckMoveWallTest() {
        TestLevel testLevel1 = new TestLevel();
        MainCharacter MC1 = new MainCharacter(new Vector3(0, 2, 0));

        GameLogicDriver.set_gameLevelAtStage(testLevel1, 1);
        GameLogicDriver.loadNewLevel();
        GameLogicDriver._playerCharacters.add(MC1);

        assert(!GameLogicDriver.MCCheckMove(GameLogicDriver._playerCharacters.get(0), new Vector3(-1,2,0)));
    }

    /**
     * Tests GameLogicDriver's method to check for an item when a MainCharacter is on a tile with no item on it
     */
    @Test
    public void MCCheckItemNoneTest() {
        MainCharacter MC1 = new MainCharacter(new Vector3(0, 2, 0));
        RegenHeart regenHeart1 = new RegenHeart(new Vector3(1, 2, 0));

        GameLogicDriver._playerCharacters.add(MC1);
        GameLogicDriver._items.add(regenHeart1);

        GameLogicDriver.MCCheckItem(GameLogicDriver._playerCharacters.get(0));
        assert(GameLogicDriver._items.size() == 1);
        assert(MC1.backpack.getItems().isEmpty());
    }

    /**
     * Tests GameLogicDriver's method to check for an item when a MainCharacter is on a tile with an item, but the item
     * is not picked up
     */
    @Test
    public void MCCheckItemNoPickupTest() {
        MainCharacter MC1 = new MainCharacter(new Vector3(0, 2, 0));
        RegenHeart regenHeart1 = new RegenHeart(new Vector3(0, 2, 0));

        GameLogicDriver._playerCharacters.add(MC1);
        GameLogicDriver._items.add(regenHeart1);

        GameLogicDriver.MCCheckItem(GameLogicDriver._playerCharacters.get(0));
        assert(GameLogicDriver._items.size() == 1);
        assert(MC1.backpack.getItems().isEmpty());
    }

    /**
     * Tests GameLogicDriver's method to check for an item when a MainCharacter is on a tile with an item, and the item
     * is picked up
     */
    @Test
    public void MCCheckItemPickupTest() {
        MainCharacter MC1 = new MainCharacter(new Vector3(0, 2, 0));
        MC1.takeDamage(1);
        RegenHeart regenHeart1 = new RegenHeart(new Vector3(0, 2, 0));

        GameLogicDriver._playerCharacters.add(MC1);
        GameLogicDriver._items.add(regenHeart1);

        GameLogicDriver.MCCheckItem(GameLogicDriver._playerCharacters.get(0));
        assert(GameLogicDriver._items.size() == 0);
    }

    /**
     * Tests GameLogicDriver's method to make all Keys obtainable when all the bosses are dead
     */
    @Test
    public void enableKeysTest() {
        Minion minion1 = new Minion();
        Boss boss1 = new Boss();
        Key key1 = new Key();

        GameLogicDriver._enemyCharacters.add(minion1);
        GameLogicDriver._enemyCharacters.add(boss1);
        GameLogicDriver._items.add(key1);

        GameLogicDriver.enableKeys();
        assert(!key1.getKeyVisibility());

        GameLogicDriver.removeEnemy(boss1);

        GameLogicDriver.enableKeys();
        assert(key1.getKeyVisibility());
    }

    /**
     * Tests GameLogicDriver's method to activate enemies so that they will move when there is a MainCharacter within
     * 3 tiles of the enemy
     */
    @Test
    public void activateNearbyEnemiesTest() {
        MainCharacter MC1 = new MainCharacter(new Vector3(0, 2, 0));
        Boss boss1 = new Boss(new Vector3(1, 2, 0));
        Minion minion1 = new Minion(new Vector3(3, 0, 0));
        Runner runner1 = new Runner(new Vector3(4, 2, 0));

        GameLogicDriver._playerCharacters.add(MC1);
        GameLogicDriver._enemyCharacters.add(boss1);
        GameLogicDriver._enemyCharacters.add(minion1);
        GameLogicDriver._enemyCharacters.add(runner1);

        GameLogicDriver.activateNearbyEnemies(MC1);
        assert(boss1.get_enemyActiveState());
        assert(minion1.get_enemyActiveState());
        assert(!runner1.get_enemyActiveState());
    }

    @Test
    public void enemyLogicTest() {
        // TODO: implement
    }

    /**
     * Tests GameLogicDriver's method to check what Character if any is in a certain tile for the calling Enemy, and
     * have that calling Enemy attack if the Character is a MainCharacter
     */
    @Test
    public void enemyCheckMoveTest() {
        MainCharacter MC1 = new MainCharacter(new Vector3(1, 2, 0));
        Boss boss1 = new Boss(new Vector3(0, 3, 0));
        Minion minion1 = new Minion(new Vector3(0, 2, 0));

        GameLogicDriver._playerCharacters.add(MC1);
        GameLogicDriver._enemyCharacters.add(boss1);
        GameLogicDriver._enemyCharacters.add(minion1);

        assert(GameLogicDriver.enemyCheckMove(minion1, new Vector3(0, 1, 0)) == null);
        assert(GameLogicDriver.enemyCheckMove(minion1, new Vector3(0, 3, 0)) == boss1);
        assert(GameLogicDriver.enemyCheckMove(minion1, new Vector3(1, 2, 0)) == MC1);
        assert(MC1.getStatBlock().get_hp() == MC1.getStatBlock().get_maxHp() - minion1.getStatBlock().get_atk());
    }

    /**
     * Tests GameLogicDriver's method to check if there's any Character on the given tile
     */
    @Test
    public void checkForCharacterTest() {
        MainCharacter MC1 = new MainCharacter(new Vector3(1, 2, 0));
        Boss boss1 = new Boss(new Vector3(0, 3, 0));

        GameLogicDriver._playerCharacters.add(MC1);
        GameLogicDriver._enemyCharacters.add(boss1);

        assert(GameLogicDriver.checkForCharacter(new Vector3(3, 3, 0)) == null);
        assert(GameLogicDriver.checkForCharacter(new Vector3(1, 2, 0)) == MC1);
        assert(GameLogicDriver.checkForCharacter(new Vector3(0, 3, 0)) == boss1);
    }

    /**
     * Tests GameLogicDriver's method to check if there's any Item on the given tile
     */
    @Test
    public void checkForItemTest() {
        Key key1 = new Key(new Vector3(1, 1, 0));

        GameLogicDriver._items.add(key1);

        assert(GameLogicDriver.checkForItem(new Vector3(1, 2, 0)) == null);
        assert(GameLogicDriver.checkForItem(new Vector3(1, 1, 0)) == key1);
    }

    /**
     * Tests GameLogicDriver's method to check if all the bosses are dead
     */
    @Test
    public void checkForAllDeadBossTest() {
        Minion minion1 = new Minion();
        Boss boss1 = new Boss();
        Runner runner1 = new Runner();

        GameLogicDriver._enemyCharacters.add(minion1);
        GameLogicDriver._enemyCharacters.add(boss1);
        GameLogicDriver._enemyCharacters.add(runner1);

        assert(!GameLogicDriver.checkForAllDeadBoss());

        GameLogicDriver.removeEnemy(boss1);
        assert(GameLogicDriver.checkForAllDeadBoss());
    }

    @Test
    public void endGameTest() {
        // TODO: implement
    }

    /**
     * Tests GameLogicDriver's method to reset all of it's attributes
     */
    @Test
    public void clearEverythingTest() {
        TestLevel testLevel1 = new TestLevel();
        MainCharacter MC1 = new MainCharacter();
        Boss boss1 = new Boss();
        Minion minion1 = new Minion();
        Key key1 = new Key();
        RegenHeart regenHeart1 = new RegenHeart();

        GameLogicDriver.set_gameLevelAtStage(testLevel1, 1);
        GameLogicDriver.loadNewLevel();
        GameLogicDriver._playerCharacters.add(MC1);
        GameLogicDriver._enemyCharacters.add(boss1);
        GameLogicDriver._enemyCharacters.add(minion1);
        GameLogicDriver._items.add(key1);
        GameLogicDriver._items.add(regenHeart1);

        GameLogicDriver.clearEverything();

        assert(GameLogicDriver._playerCharacters.size() == 0);
        assert(GameLogicDriver._enemyCharacters.size() == 0);
        assert(GameLogicDriver._items.size() == 0);
        assert(GameLogicDriver._gameLevelList.size() == 0);
        assert(GameLogicDriver._defaultGameLevelList.size() == 0);
        assert(GameLogicDriver._gameMap == null);
    }
}

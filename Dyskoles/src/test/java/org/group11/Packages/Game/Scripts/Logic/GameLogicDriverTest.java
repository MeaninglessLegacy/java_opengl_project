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

    private TestLevel testLevel1;
    private TestLevel testLevel2;
    private TestLevel testLevel3;
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

            testLevel1 = new TestLevel();
            testLevel2 = new TestLevel();
            testLevel3 = new TestLevel();
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
        assert(GameLogicDriver.set_gameLevelAtStage(testLevel1, 1));
        assert(GameLogicDriver._gameLevelList.get(0) == testLevel1);
    }

    /**
     * Tests GameLogicDriver's method to overwrite a Level at a certain stage
     */
    @Test
    public void overwrite_gameLevelTest() {
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
        GameLogicDriver.set_gameLevelAtStage(testLevel1, 1);
        GameLogicDriver.loadNewLevel();

        assert(GameLogicDriver._playerCharacters == testLevel1._players);
        assert(GameLogicDriver._enemyCharacters == testLevel1._enemies);
        assert(GameLogicDriver._items == testLevel1._items);

        // test if sprites enabled?
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
        GameLogicDriver._enemyCharacters.add(minion);
        GameLogicDriver._enemyCharacters.add(boss);

        GameLogicDriver.removeEnemy(minion);
        assert(GameLogicDriver._enemyCharacters.size() == 1);

        GameLogicDriver.removeEnemy(boss);
        assert(GameLogicDriver._enemyCharacters.size() == 0);
    }

    /**
     * Tests GameLogicDriver's method to check if a MainCharacter can move into a tile when it is a floor tile and is
     * clear of any characters
     */
    @Test
    public void MCCheckMoveClearTest() {
        GameLogicDriver.set_gameLevelAtStage(testLevel1, 1);
        GameLogicDriver.loadNewLevel();

        MC.transform.setPosition(new Vector3(0,2,0));
        GameLogicDriver._playerCharacters.add(MC);

        assert(GameLogicDriver.MCCheckMove(GameLogicDriver._playerCharacters.get(0), new Vector3(1,2,0)));
    }

    /**
     * Tests GameLogicDriver's method to check if a MainCharacter can move into a tile when it is a floor tile but has
     * an enemy which is killed, thus is clear to move in to
     */
    @Test
    public void MCCheckMoveEnemyClearTest() {
        GameLogicDriver.set_gameLevelAtStage(testLevel1, 1);
        GameLogicDriver.loadNewLevel();

        MC.transform.setPosition(new Vector3(0,2,0));
        GameLogicDriver._playerCharacters.add(MC);

        minion.transform.setPosition(new Vector3(1,2,0));
        GameLogicDriver._enemyCharacters.add(minion);
        minion.getStatBlock().set_hp(1);

        assert(GameLogicDriver.MCCheckMove(GameLogicDriver._playerCharacters.get(0), new Vector3(1,2,0)));
    }

    /**
     * Tests GameLogicDriver's method to check if a MainCharacter can move into a tile when it is a floor tile but has
     * an enemy which is not killed, thus is not clear to move in to
     */
    @Test
    public void MCCheckMoveEnemyNotClearTest() {
        GameLogicDriver.set_gameLevelAtStage(testLevel1, 1);
        GameLogicDriver.loadNewLevel();

        MC.transform.setPosition(new Vector3(0,2,0));
        GameLogicDriver._playerCharacters.add(MC);

        minion.transform.setPosition(new Vector3(1,2,0));
        GameLogicDriver._enemyCharacters.add(minion);

        assert(!GameLogicDriver.MCCheckMove(GameLogicDriver._playerCharacters.get(0), new Vector3(1,2,0)));
    }

    /**
     * Tests GameLogicDriver's method to check if a MainCharacter can move into a tile when it is a wall, thus cannot
     * move in to it
     */
    @Test
    public void MCCheckMoveWallTest() {
        GameLogicDriver.set_gameLevelAtStage(testLevel1, 1);
        GameLogicDriver.loadNewLevel();

        MC.transform.setPosition(new Vector3(0,2,0));
        GameLogicDriver._playerCharacters.add(MC);

        assert(!GameLogicDriver.MCCheckMove(GameLogicDriver._playerCharacters.get(0), new Vector3(-1,2,0)));
    }

    /**
     * Tests GameLogicDriver's method to check for an item when a MainCharacter is on a tile with no item on it
     */
    @Test
    public void MCCheckItemNoneTest() {
        MC.transform.setPosition(new Vector3(0,2,0));
        GameLogicDriver._playerCharacters.add(MC);

        regenHeart.transform.setPosition(new Vector3(1,2,0));
        GameLogicDriver._items.add(regenHeart);

        GameLogicDriver.MCCheckItem(GameLogicDriver._playerCharacters.get(0));
        assert(GameLogicDriver._items.size() == 1);
        assert(MC.backpack.getItems().isEmpty());
    }

    /**
     * Tests GameLogicDriver's method to check for an item when a MainCharacter is on a tile with an item, but the item
     * is not picked up
     */
    @Test
    public void MCCheckItemNoPickupTest() {
        MC.transform.setPosition(new Vector3(0,2,0));
        GameLogicDriver._playerCharacters.add(MC);

        regenHeart.transform.setPosition(new Vector3(0,2,0));
        GameLogicDriver._items.add(regenHeart);

        GameLogicDriver.MCCheckItem(GameLogicDriver._playerCharacters.get(0));
        assert(GameLogicDriver._items.size() == 1);
        assert(MC.backpack.getItems().isEmpty());
    }

    /**
     * Tests GameLogicDriver's method to check for an item when a MainCharacter is on a tile with an item, and the item
     * is picked up
     */
    @Test
    public void MCCheckItemPickupTest() {
        MC.transform.setPosition(new Vector3(0,2,0));
        GameLogicDriver._playerCharacters.add(MC);
        MC.takeDamage(1);

        regenHeart.transform.setPosition(new Vector3(0,2,0));
        GameLogicDriver._items.add(regenHeart);

        GameLogicDriver.MCCheckItem(GameLogicDriver._playerCharacters.get(0));
        assert(GameLogicDriver._items.size() == 0);
    }

    /**
     * Tests GameLogicDriver's method to make all Keys obtainable when all the bosses are dead
     */
    @Test
    public void enableKeysTest() {
        GameLogicDriver._enemyCharacters.add(minion);
        GameLogicDriver._enemyCharacters.add(boss);
        GameLogicDriver._items.add(key);

        GameLogicDriver.enableKeys();
        assert(!key.getKeyVisibility());

        GameLogicDriver.removeEnemy(boss);

        GameLogicDriver.enableKeys();
        assert(key.getKeyVisibility());
    }

    /**
     * Tests GameLogicDriver's method to activate enemies so that they will move when there is a MainCharacter within
     * 3 tiles of the enemy
     */
    @Test
    public void activateNearbyEnemiesTest() {
        MC.transform.setPosition(new Vector3(0, 2, 0));
        boss.transform.setPosition(new Vector3(1, 2, 0));
        minion.transform.setPosition(new Vector3(3, 0, 0));
        runner.transform.setPosition(new Vector3(4, 2, 0));
        GameLogicDriver._playerCharacters.add(MC);
        GameLogicDriver._enemyCharacters.add(boss);
        GameLogicDriver._enemyCharacters.add(minion);
        GameLogicDriver._enemyCharacters.add(runner);

        GameLogicDriver.activateNearbyEnemies(MC);
        assert(boss.get_enemyActiveState());
        assert(minion.get_enemyActiveState());
        assert(!runner.get_enemyActiveState());
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
        MC.transform.setPosition(new Vector3(1, 2, 0));
        boss.transform.setPosition(new Vector3(0, 3, 0));
        minion.transform.setPosition(new Vector3(0, 2, 0));
        GameLogicDriver._playerCharacters.add(MC);
        GameLogicDriver._enemyCharacters.add(boss);
        GameLogicDriver._enemyCharacters.add(minion);

        assert(GameLogicDriver.enemyCheckMove(minion, new Vector3(0, 1, 0)) == null);
        assert(GameLogicDriver.enemyCheckMove(minion, new Vector3(0, 3, 0)) == boss);
        assert(GameLogicDriver.enemyCheckMove(minion, new Vector3(1, 2, 0)) == MC);
        assert(MC.getStatBlock().get_hp() == MC.getStatBlock().get_maxHp() - minion.getStatBlock().get_atk());
    }

    /**
     * Tests GameLogicDriver's method to check if there's any Character on the given tile
     */
    @Test
    public void checkForCharacterTest() {
        MC.transform.setPosition(new Vector3(1, 2, 0));
        boss.transform.setPosition(new Vector3(0, 3, 0));
        GameLogicDriver._playerCharacters.add(MC);
        GameLogicDriver._enemyCharacters.add(boss);

        assert(GameLogicDriver.checkForCharacter(new Vector3(3, 3, 0)) == null);
        assert(GameLogicDriver.checkForCharacter(new Vector3(1, 2, 0)) == MC);
        assert(GameLogicDriver.checkForCharacter(new Vector3(0, 3, 0)) == boss);
    }

    /**
     * Tests GameLogicDriver's method to check if there's any Item on the given tile
     */
    @Test
    public void checkForItemTest() {
        key.transform.setPosition(new Vector3(1, 1, 0));
        GameLogicDriver._items.add(key);

        assert(GameLogicDriver.checkForItem(new Vector3(1, 2, 0)) == null);
        assert(GameLogicDriver.checkForItem(new Vector3(1, 1, 0)) == key);
    }

    /**
     * Tests GameLogicDriver's method to check if all the bosses are dead
     */
    @Test
    public void checkForAllDeadBossTest() {
        GameLogicDriver._enemyCharacters.add(minion);
        GameLogicDriver._enemyCharacters.add(boss);
        GameLogicDriver._enemyCharacters.add(runner);

        assert(!GameLogicDriver.checkForAllDeadBoss());

        GameLogicDriver.removeEnemy(boss);
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
        GameLogicDriver.set_gameLevelAtStage(testLevel1, 1);
        GameLogicDriver.loadNewLevel();
        GameLogicDriver._playerCharacters.add(MC);
        GameLogicDriver._enemyCharacters.add(boss);
        GameLogicDriver._enemyCharacters.add(minion);
        GameLogicDriver._items.add(key);
        GameLogicDriver._items.add(regenHeart);

        GameLogicDriver.clearEverything();

        assert(GameLogicDriver._playerCharacters.size() == 0);
        assert(GameLogicDriver._enemyCharacters.size() == 0);
        assert(GameLogicDriver._items.size() == 0);
        assert(GameLogicDriver._gameLevelList.size() == 0);
        assert(GameLogicDriver._defaultGameLevelList.size() == 0);
        assert(GameLogicDriver._gameMap == null);
    }
}

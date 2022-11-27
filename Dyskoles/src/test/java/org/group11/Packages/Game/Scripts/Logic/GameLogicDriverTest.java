package org.group11.Packages.Game.Scripts.Logic;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Character_Scripts.Boss;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.group11.Packages.Game.Scripts.Character_Scripts.Minion;
import org.group11.Packages.Game.Scripts.Character_Scripts.Runner;
import org.group11.Packages.Game.Scripts.Item_Scripts.Exit;
import org.group11.Packages.Game.Scripts.Item_Scripts.Key;
import org.group11.Packages.Game.Scripts.Item_Scripts.RegenHeart;
import org.group11.Packages.Game.Scripts.Item_Scripts.SpikeTrap;
import org.group11.Packages.Game.Scripts.Levels.TestLevel;
import org.group11.Packages.Game.Scripts.Levels.TestRoom2;
import org.group11.Packages.Game.Scripts.TestSetup;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Floor;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Wall;
import org.junit.Test;

/**
 * Runs tests on various methods for the GameLogicDriver class
 */
public class GameLogicDriverTest extends TestSetup {
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
    private Exit exit;
    private MenuScreen menuScreen;

    private Floor floor;
    private Wall wall;

    //******************************************************************************************************************
    //* setup
    //******************************************************************************************************************
    private class SpecificSetupClass extends GameObject{
        @Override
        public void start() {
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
            exit = new Exit();
            floor = new Floor();
            wall = new Wall();
            menuScreen = new MenuScreen();

            scene.Instantiate(MC);
            scene.Instantiate(boss);
            scene.Instantiate(minion);
            scene.Instantiate(runner);
            scene.Instantiate(key);
            scene.Instantiate(regenHeart);
            scene.Instantiate(spikeTrap);
            scene.Instantiate(exit);
            scene.Instantiate(floor);
            scene.Instantiate(wall);
            scene.Instantiate(menuScreen);

            everythingInstantiated = true;
        }
    }

    @Override
    public void setup() {
        engine = new Engine();
        engine.start();
        scene = Scene.get_scene();

        if (GameLogicDriver._gameMap != null || GameLogicDriver._playerCharacters.size() != 0 ||
            GameLogicDriver._enemyCharacters.size() != 0 || GameLogicDriver._items.size() != 0 ||
            GameLogicDriver._gameLevelList.size() != 0) {
            GameLogicDriver.clearEverything();
            System.out.println("Cleared everything");
        }

        SpecificSetupClass specificSetupClass = new SpecificSetupClass();
        scene.Instantiate(specificSetupClass);

        while (!everythingInstantiated) {
            System.out.print("");
        }
        System.out.println("Test started");
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
        // Testing out of bounds
        assert(!GameLogicDriver.set_gameLevelAtStage(testLevel1, 3));
        assert(!GameLogicDriver.set_gameLevelAtStage(testLevel1, -1));
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
     * Tests GameLogicDriver's method to correctly reset _gameStage to 1 if needed
     */
    @Test
    public void checkGameStageTest() {
        GameLogicDriver.checkGameStage();
        assert(GameLogicDriver._gameStage == 1);

        GameLogicDriver._gameStage++;
        GameLogicDriver.checkGameStage();
        assert(GameLogicDriver._gameStage == 1);

        TestLevel testLevel1 = new TestLevel();
        TestLevel testLevel2 = new TestLevel();
        TestLevel testLevel3 = new TestLevel();
        GameLogicDriver.set_gameLevelAtStage(testLevel1, 1);
        GameLogicDriver.set_gameLevelAtStage(testLevel2, 2);
        GameLogicDriver.set_gameLevelAtStage(testLevel3, 3);

        GameLogicDriver._gameStage += 2;
        GameLogicDriver.checkGameStage();
        assert(GameLogicDriver._gameStage == 3);

        GameLogicDriver._gameStage++;
        GameLogicDriver.checkGameStage();
        assert(GameLogicDriver._gameStage == 1);
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

    /**
     * Tests GameLogicDriver's method to iterate through it's list of enemies and call on them to perform logic
     */
    @Test
    public void enemyLogicTest() {
        TestLevel testLevel1 = new TestLevel();
        GameLogicDriver.set_gameLevelAtStage(testLevel1, 1);

        MainCharacter MC1 = new MainCharacter(new Vector3(3, 3, 0));
        Boss boss1 = new Boss(new Vector3(3, 1, 0));
        Minion minion1 = new Minion(new Vector3(1, 1, 0));
        Runner runner1 = new Runner(new Vector3(1, 3, 0));

        GameLogicDriver.startNewLevel();

        GameLogicDriver._playerCharacters.add(MC1);
        GameLogicDriver._enemyCharacters.add(boss1);
        GameLogicDriver._enemyCharacters.add(minion1);
        GameLogicDriver._enemyCharacters.add(runner1);

        int bossMaxTicks = boss1.get_ticksBeforeNextMove();
        int minionMaxTicks = minion1.get_ticksBeforeNextMove();
        int runnerMaxTicks = runner1.get_ticksBeforeNextMove();

        GameLogicDriver.enemyLogic(MC);

        assert(minion1.get_ticksBeforeNextMove() == minionMaxTicks);
        assert(boss1.get_ticksBeforeNextMove() == bossMaxTicks);
        assert(runner1.get_ticksBeforeNextMove() == runnerMaxTicks);

        GameLogicDriver.activateNearbyEnemies(MC);
        GameLogicDriver.enemyLogic(MC);

        assert(minion1.get_ticksBeforeNextMove() == minionMaxTicks - 1);
        assert(boss1.get_ticksBeforeNextMove() == bossMaxTicks - 1);
        assert(runner1.get_ticksBeforeNextMove() == runnerMaxTicks - 1);

        GameLogicDriver.enemyLogic(MC);

        assert(minion1.get_ticksBeforeNextMove() == minionMaxTicks);
        assert(boss1.get_ticksBeforeNextMove() == bossMaxTicks - 2);
        assert(runner1.get_ticksBeforeNextMove() == runnerMaxTicks);
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

    /**
     * Tests GameLogicDriver's method to end the game
     */
    @Test
    public void endGameTest() {
        TestLevel testLevel1 = new TestLevel();
        TestLevel testLevel2 = new TestLevel();
        TestLevel testLevel3 = new TestLevel();
        MainCharacter MC1 = new MainCharacter();

        GameLogicDriver.set_gameLevelAtStage(testLevel1, 1);
        GameLogicDriver.set_gameLevelAtStage(testLevel2, 2);
        GameLogicDriver.set_gameLevelAtStage(testLevel3, 3);
        GameLogicDriver._playerCharacters.add(MC1);
        GameLogicDriver.startNewLevel();
        GameLogicDriver.endGame(true);

        assert (!GameLogicDriver._gameStarted);
        assert (GameLogicDriver._gameStage == 2);

        GameLogicDriver.set_gameLevelAtStage(testLevel1, 1);
        GameLogicDriver.set_gameLevelAtStage(testLevel2, 2);
        GameLogicDriver.set_gameLevelAtStage(testLevel3, 3);
        GameLogicDriver._playerCharacters.add(MC1);
        GameLogicDriver.startNewLevel();
        GameLogicDriver.endGame(false);

        assert(!GameLogicDriver._gameStarted);
        assert(GameLogicDriver._gameStage == 2);
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

    /**
     * Tests Enemy's canEnemyMove() method. Must be contained in this test class to access GameLogicDriver's attributes
     */
    @Test
    public void enemyCanEnemyMoveTest() {
        MainCharacter MC1 = new MainCharacter(new Vector3(0, 4, 0));
        MC1.getStatBlock().set_MaxHp(100);
        MC1.getStatBlock().set_hp(100);
        Boss boss1 = new Boss(new Vector3(0, 2, 0));
        Minion minion1 = new Minion(new Vector3(0, 3, 0));
        Minion minion2 = new Minion(new Vector3(1, 2, 0));
        Minion minion3 = new Minion(new Vector3(0, 1, 0));
        Pathfinder pathfinder = new Pathfinder();

        GameLogicDriver.set_gameLevelAtStage(testLevel, 1);
        GameLogicDriver.startNewLevel();
        GameLogicDriver._playerCharacters.add(MC1);
        GameLogicDriver._enemyCharacters.add(boss1);
        GameLogicDriver._enemyCharacters.add(minion1);
        GameLogicDriver._enemyCharacters.add(minion2);
        GameLogicDriver._enemyCharacters.add(minion3);

        // Testing when enemy is not activated
        boss1.canEnemyMove(pathfinder, GameLogicDriver._gameMap, MC1);

        assert(boss1.get_ticksBeforeNextMove() == 3);

        // Enemy is activated, should decrement their move countdown
        boss1.set_enemyActiveState(true);
        boss1.canEnemyMove(pathfinder, GameLogicDriver._gameMap, MC1);

        assert(boss1.get_ticksBeforeNextMove() == 2);

        // Enemy tries to move but is trapped in by other enemies, so move countdown timer resets to 1
        boss1.set_ticksBeforeNextMove(1);
        boss1.canEnemyMove(pathfinder, GameLogicDriver._gameMap, MC1);

        assert(boss1.get_ticksBeforeNextMove() == 1);

        // Path is now free, enemy should move towards MainCharacter
        GameLogicDriver._enemyCharacters.remove(minion1);
        Vector3 bossOriginalPos = boss.transform.position;
        boss1.canEnemyMove(pathfinder, GameLogicDriver._gameMap, MC1);

        assert(boss1.get_ticksBeforeNextMove() == 3);
        assert(boss1.transform.position.y != bossOriginalPos.y);

        // Enemy attempts to move into the position with a MainCharacter, attacks that MainCharacter
        boss1.transform.setPosition(new Vector3(0, 2, 0));
        MC1.transform.setPosition(new Vector3(0, 3, 0));
        boss1.set_ticksBeforeNextMove(1);
        boss1.canEnemyMove(pathfinder, GameLogicDriver._gameMap, MC1);

        assert(boss1.get_ticksBeforeNextMove() == 3);
        assert(MC1.getStatBlock().get_hp() == MC1.getStatBlock().get_maxHp() - boss1.getStatBlock().get_atk());
    }

    /**
     * Tests Exit's activate() method when the player has a key
     */
    @Test
    public void exitKeyActivateTest() {
        MainCharacter MC1 = new MainCharacter();
        MC1.backpack.addItem(new Key());
        Exit exit1 = new Exit();

        GameLogicDriver.set_gameLevelAtStage(testLevel, 1);
        GameLogicDriver.startNewLevel();
        GameLogicDriver._playerCharacters.add(MC1);
        GameLogicDriver._items.add(exit1);

        exit1.activate(MC1);
        assert(!GameLogicDriver._gameStarted);
    }
}

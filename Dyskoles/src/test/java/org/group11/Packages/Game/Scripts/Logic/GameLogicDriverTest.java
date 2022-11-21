package org.group11.Packages.Game.Scripts.Logic;
import org.group11.Packages.Game.Scripts.Character_Scripts.Boss;
import org.group11.Packages.Game.Scripts.Character_Scripts.Enemy;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.group11.Packages.Game.Scripts.Character_Scripts.Minion;
import org.group11.Packages.Game.Scripts.Item_Scripts.Item;
import org.group11.Packages.Game.Scripts.Levels.TestLevel;
import org.group11.Packages.Game.Scripts.Levels.TestRoom;
import org.group11.Packages.Game.Scripts.Levels.TestRoom2;
import org.junit.Before;
import org.junit.Test;

public class GameLogicDriverTest {

    @Before
    public void setup() {

    }

    @Test
    public void set_gameLevelTest() {
        TestRoom level1 = new TestRoom();

        assert(GameLogicDriver.set_gameLevelAtStage(level1, 1));
        assert(GameLogicDriver._gameLevelList.get(0) == level1);
    }

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

    @Test
    public void get_gameLevelTest() {
        TestRoom level1 = new TestRoom();
        GameLogicDriver.set_gameLevelAtStage(level1, 1);

        assert(GameLogicDriver.get_gameLevelAtStage(1) == level1);
        // Testing out of bounds
        assert(GameLogicDriver.get_gameLevelAtStage(2) == null);
        assert(GameLogicDriver.get_gameLevelAtStage(0) == null);
    }

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

    }

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


}

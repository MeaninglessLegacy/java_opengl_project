package org.group11.Packages.Game.Scripts.Levels;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.Boss;
import org.group11.Packages.Game.Scripts.Character_Scripts.Enemy;
import org.group11.Packages.Game.Scripts.Item_Scripts.Key;
import org.group11.Packages.Game.Scripts.Logic.Level;
import org.group11.Packages.Game.Scripts.MapGenerators.SquareRoom;

/**
 * Level used to test features
 */
public class TestRoom extends Level {
    public TestRoom(){
        _mapGenerator = new SquareRoom();

        Vector3 testKeyPos = new Vector3(4, 4, 0);
        Vector3 testEnemyPos = new Vector3(1,2,0);
        Key testKey = new Key(testKeyPos);
        Enemy testEnemy = new Boss(testEnemyPos);

        _enemies.add(testEnemy);
        //_items.add(testKey);
    }
}

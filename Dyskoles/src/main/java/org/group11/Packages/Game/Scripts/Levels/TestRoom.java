package org.group11.Packages.Game.Scripts.Levels;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.Boss;
import org.group11.Packages.Game.Scripts.Character_Scripts.Enemy;
import org.group11.Packages.Game.Scripts.Character_Scripts.Minion;
import org.group11.Packages.Game.Scripts.Item_Scripts.Key;
import org.group11.Packages.Game.Scripts.Logic.Level;
import org.group11.Packages.Game.Scripts.MapGenerators.SquareRoom;

/**
 * Level used to test features
 */
public class TestRoom extends Level {
    public TestRoom(){
        _mapGenerator = new SquareRoom();

        Key testKey = new Key(new Vector3(4, 4, 0));
        Boss testBoss = new Boss(new Vector3(1,2,0));
        Minion testMinion = new Minion(new Vector3(3,2,0));

        //_items.add(testKey);
        _enemies.add(testBoss);
        _enemies.add(testMinion);

    }
}
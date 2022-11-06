package org.group11.Packages.Game.Scripts.Levels;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.Boss;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.group11.Packages.Game.Scripts.Character_Scripts.Minion;
import org.group11.Packages.Game.Scripts.Character_Scripts.Runner;
import org.group11.Packages.Game.Scripts.Item_Scripts.Exit;
import org.group11.Packages.Game.Scripts.Item_Scripts.Key;
import org.group11.Packages.Game.Scripts.Item_Scripts.RegenHeart;
import org.group11.Packages.Game.Scripts.Item_Scripts.SpikeTrap;
import org.group11.Packages.Game.Scripts.Logic.Level;
import org.group11.Packages.Game.Scripts.MapGenerators.SquareRoom;

/**
 * Level used to test features
 */
public class TestRoom extends Level {
    public TestRoom(){
        _mapGenerator = new SquareRoom();

        MainCharacter testMC = new MainCharacter(new Vector3(1, 1, 0));
        Key testKey = new Key(new Vector3(4, 4, 0));
        Exit testExit = new Exit(new Vector3(4, 6, 0));
        SpikeTrap testSpikeTrap = new SpikeTrap(new Vector3(8, 4, 0));
        RegenHeart testRegenHeart = new RegenHeart(new Vector3(6, 4, 0));
        Boss testBoss = new Boss(new Vector3(1,5,0));
        Minion testMinion1 = new Minion(new Vector3(5,3,0));
        Minion testMinion2 = new Minion(new Vector3(10,10,0));
        Minion testMinion3 = new Minion(new Vector3(11,10,0));
        Minion testMinion4 = new Minion(new Vector3(12,10,0));
        Minion testMinion5 = new Minion(new Vector3(13,10,0));
        Minion testMinion6 = new Minion(new Vector3(14,10,0));
        Runner testRunner = new Runner(new Vector3(15, 5, 0));

        _players.add(testMC);
        _items.add(testExit);
        _items.add(testKey);
        _items.add(testSpikeTrap);
        _items.add(testRegenHeart);
        _enemies.add(testBoss);
        _enemies.add(testMinion1);
        _enemies.add(testMinion2);
        _enemies.add(testMinion3);
        _enemies.add(testMinion4);
        _enemies.add(testMinion5);
        _enemies.add(testMinion6);
        //_enemies.add(testRunner);
    }
}

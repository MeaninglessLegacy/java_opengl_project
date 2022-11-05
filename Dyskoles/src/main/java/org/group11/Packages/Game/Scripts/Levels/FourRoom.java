package org.group11.Packages.Game.Scripts.Levels;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.Boss;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.group11.Packages.Game.Scripts.Character_Scripts.Minion;
import org.group11.Packages.Game.Scripts.Item_Scripts.Exit;
import org.group11.Packages.Game.Scripts.Item_Scripts.Key;
import org.group11.Packages.Game.Scripts.Item_Scripts.RegenHeart;
import org.group11.Packages.Game.Scripts.Item_Scripts.SpikeTrap;
import org.group11.Packages.Game.Scripts.Logic.Level;
import org.group11.Packages.Game.Scripts.MapGenerators.FourConnectedRooms;

public class FourRoom extends Level {
    public FourRoom(){
        _mapGenerator = new FourConnectedRooms();

        MainCharacter testMC = new MainCharacter(new Vector3(4, 4, 0));
        Key testKey = new Key(new Vector3(4, 4, 0));
        Exit testExit = new Exit(new Vector3(4, 6, 0));
        SpikeTrap testSpikeTrap = new SpikeTrap(new Vector3(8, 4, 0));
        RegenHeart testRegenHeart = new RegenHeart(new Vector3(6, 4, 0));
        Boss testBoss = new Boss(new Vector3(1,5,0));
        Minion testMinion1 = new Minion(new Vector3(5,3,0));
        Minion testMinion2 = new Minion(new Vector3(9,9,0));

        _players.add(testMC);
        /*
        _items.add(testExit);
        _items.add(testKey);
        _items.add(testSpikeTrap);
        _items.add(testRegenHeart);
        _enemies.add(testBoss);
        _enemies.add(testMinion1);
        _enemies.add(testMinion2);
        */
    }
}

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
import org.group11.Packages.Game.Scripts.MapGenerators.FourConnectedRooms;

public class FourRoom extends Level {
    public FourRoom(){
        _mapGenerator = new FourConnectedRooms();

        // SW room (0<=x<=11, 0<=y<=11)
        MainCharacter MC1 = new MainCharacter(new Vector3(1, 1, 0));
        Minion minion1 = new Minion(new Vector3(5,3,0));
        Minion minion2 = new Minion(new Vector3(5,5,0));
        SpikeTrap spikeTrap1 = new SpikeTrap(new Vector3(8, 7, 0));
        RegenHeart regenHeart1 = new RegenHeart(new Vector3(30, 2, 0));
        _items.add(regenHeart1);
        _players.add(MC1);
        _enemies.add(minion1);
        _enemies.add(minion2);
        _items.add(spikeTrap1);

        // SW Room (20<=x<=31, 0<=y<=11)
        Runner runner1 = new Runner(new Vector3(25, 8, 0));
        _enemies.add(runner1);

        // NE Room (20<=x<=31, 20<=y<=31)
        Boss boss1 = new Boss(new Vector3(1,5,0));
        Key key1 = new Key(new Vector3(28, 28, 0));
        Exit exit1 = new Exit(new Vector3(30, 30, 0));
        _enemies.add(boss1);
        _items.add(key1);
        _items.add(exit1);

        // NW Room (0<=x<=11, 20<=y<=31)











    }
}

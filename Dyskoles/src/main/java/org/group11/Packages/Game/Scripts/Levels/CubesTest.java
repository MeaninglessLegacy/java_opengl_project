package org.group11.Packages.Game.Scripts.Levels;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.group11.Packages.Game.Scripts.Item_Scripts.Exit;
import org.group11.Packages.Game.Scripts.Item_Scripts.Key;
import org.group11.Packages.Game.Scripts.Logic.Level;
import org.group11.Packages.Game.Scripts.MapGenerators.CubesRoom;

public class CubesTest extends Level {
    public CubesTest(){
        _mapGenerator = new CubesRoom();
        MainCharacter testMC = new MainCharacter(new Vector3(1, 1, 0));
        Key testKey = new Key(new Vector3(4, 4, 0));
        Exit testExit = new Exit(new Vector3(4, 6, 0));
        _items.add(testExit);
        _items.add(testKey);
        _players.add(testMC);
    }
}
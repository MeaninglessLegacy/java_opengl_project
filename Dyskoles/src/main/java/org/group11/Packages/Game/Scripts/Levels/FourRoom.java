package org.group11.Packages.Game.Scripts.Levels;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.*;
import org.group11.Packages.Game.Scripts.Item_Scripts.*;
import org.group11.Packages.Game.Scripts.Logic.Level;
import org.group11.Packages.Game.Scripts.Logic.Map;
import org.group11.Packages.Game.Scripts.MapGenerators.FourConnectedRooms;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;

import java.util.Random;

/**
 * Level whose map contains 4 connected rooms. Player starts in bottom left room, and must defeat the 3 bosses in the
 * top left, top right, and bottom right rooms to gain access to the key and exit which is in the top right room
 */
public class FourRoom extends Level {
    public FourRoom(){
        _mapGenerator = new FourConnectedRooms();

        // SW room (0<=x<=11, 0<=y<=11)
        _players.add(new MainCharacter(new Vector3(1, 1, 0)));
        _enemies.add(new Minion(new Vector3(5,3,0)));
        _enemies.add(new Minion(new Vector3(5,5,0)));
        _enemies.add(new Minion(new Vector3(7,10,0)));
        _enemies.add(new Minion(new Vector3(10,4,0)));
        _enemies.add(new Minion(new Vector3(2,7,0)));
        _items.add(new SpikeTrap(new Vector3(8,7,0)));
        _items.add(new SpikeTrap(new Vector3(7,3,0)));
        _items.add(new SpikeTrap(new Vector3(4,3,0)));
        _items.add(new RegenHeart(new Vector3(5,5,0)));
        _items.add(new RegenHeart(new Vector3(2,10,0)));
        _items.add(new RegenHeart(new Vector3(9,9,0)));

        // SE Room (20<=x<=31, 0<=y<=11)
        _enemies.add(new Boss(new Vector3(27,4,0)));
        _enemies.add(new Minion(new Vector3(20,4,0)));
        _enemies.add(new Minion(new Vector3(24,1,0)));
        _enemies.add(new Minion(new Vector3(28,9,0)));
        _enemies.add(new Runner(new Vector3(25,3,0)));
        _items.add(new SpikeTrap(new Vector3(22,7,0)));
        _items.add(new SpikeTrap(new Vector3(24,3,0)));
        _items.add(new SpikeTrap(new Vector3(25,10,0)));
        _items.add(new SpikeTrap(new Vector3(28,2,0)));
        _items.add(new SpikeTrap(new Vector3(29,5,0)));
        _items.add(new SpikeTrap(new Vector3(30,10,0)));
        _items.add(new SpikeTrap(new Vector3(25,6,0)));
        _items.add(new SpikeTrap(new Vector3(20,3,0)));
        _items.add(new SpikeTrap(new Vector3(24,9,0)));
        _items.add(new RegenHeart(new Vector3(24,5,0)));
        _items.add(new RegenHeart(new Vector3(27,9,0)));
        _items.add(new RegenHeart(new Vector3(21,8,0)));

        // NE Room (20<=x<=31, 20<=y<=31)
        _enemies.add(new Boss(new Vector3(27,27,0)));
        _enemies.add(new Minion(new Vector3(21,24,0)));
        _enemies.add(new Minion(new Vector3(25,29,0)));
        _enemies.add(new Minion(new Vector3(27,25,0)));
        _enemies.add(new Minion(new Vector3(38,21,0)));
        _items.add(new Key(new Vector3(26,26,0)));
        _items.add(new Exit(new Vector3(30,30,0)));
        _items.add(new SpikeTrap(new Vector3(20,24,0)));
        _items.add(new SpikeTrap(new Vector3(21,27,0)));
        _items.add(new SpikeTrap(new Vector3(23,22,0)));
        _items.add(new SpikeTrap(new Vector3(24,29,0)));
        _items.add(new SpikeTrap(new Vector3(26,28,0)));
        _items.add(new SpikeTrap(new Vector3(27,30,0)));
        _items.add(new SpikeTrap(new Vector3(28,24,0)));
        _items.add(new SpikeTrap(new Vector3(29,20,0)));
        _items.add(new SpikeTrap(new Vector3(30,21,0)));
        _items.add(new SpikeTrap(new Vector3(31,23,0)));
        _items.add(new SpikeTrap(new Vector3(24,23,0)));
        _items.add(new RegenHeart(new Vector3(24,24,0)));
        _items.add(new RegenHeart(new Vector3(22,29,0)));

        // NW Room (0<=x<=11, 20<=y<=31)
        _enemies.add(new Boss(new Vector3(3,27,0)));
        _enemies.add(new Minion(new Vector3(1,23,0)));
        _enemies.add(new Minion(new Vector3(5,30,0)));
        _enemies.add(new Minion(new Vector3(7,22,0)));
        _enemies.add(new Minion(new Vector3(10,26,0)));
        _items.add(new SpikeTrap(new Vector3(8,22,0)));
        _items.add(new SpikeTrap(new Vector3(7,27,0)));
        _items.add(new SpikeTrap(new Vector3(5,30,0)));
        _items.add(new SpikeTrap(new Vector3(4,25,0)));
        _items.add(new SpikeTrap(new Vector3(9,28,0)));
        _items.add(new SpikeTrap(new Vector3(10,25,0)));
        _items.add(new SpikeTrap(new Vector3(2,21,0)));
        _items.add(new SpikeTrap(new Vector3(10,22,0)));
        _items.add(new RegenHeart(new Vector3(4,24,0)));
        _items.add(new RegenHeart(new Vector3(2,29,0)));
        _items.add(new RegenHeart(new Vector3(10,30,0)));

    }
}

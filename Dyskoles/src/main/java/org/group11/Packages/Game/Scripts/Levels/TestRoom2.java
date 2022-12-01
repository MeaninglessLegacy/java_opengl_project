package org.group11.Packages.Game.Scripts.Levels;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.*;
import org.group11.Packages.Game.Scripts.Item_Scripts.*;
import org.group11.Packages.Game.Scripts.Logic.Level;
import org.group11.Packages.Game.Scripts.Logic.Map;
import org.group11.Packages.Game.Scripts.MapGenerators.Dungeon;
import org.group11.Packages.Game.Scripts.MapGenerators.SquareRoom;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;

import java.util.List;
import java.util.Random;

public class TestRoom2 extends Level {
    public TestRoom2(){
        _mapGenerator = new Dungeon();
        Random random = new Random();

        // mc and exit
        MainCharacter testMC = new MainCharacter(new Vector3(1, 1, 0));
        Key testKey = new Key(new Vector3(4, 4, 0));
        Exit testExit = new Exit(new Vector3(4, 6, 0));
        _players.add(testMC);
        _items.add(testExit);
        _items.add(testKey);

        // items
        int items = random.nextInt(20)+20;
        for(int i=0; i < items; i++){
            if(random.nextInt(6)==0){
                RegenHeart testRegenHeart = new RegenHeart(new Vector3(6, 4, 0));
                _items.add(testRegenHeart);
            }else{
                SpikeTrap testSpikeTrap = new SpikeTrap(new Vector3(8, 4, 0));
                _items.add(testSpikeTrap);
            }
        }

        // enemies
        Boss testBoss = new Boss(new Vector3(1,5,0));
        _enemies.add(testBoss);
        int enemies = random.nextInt(20)+20;
        for(int i=0; i < enemies; i++){
            if(random.nextInt(40)==0){
                Runner testRunner = new Runner(new Vector3(15, 5, 0));
                _enemies.add(testRunner);
            }else{
                Minion testMinion1 = new Minion(new Vector3(5,3,0));
                _enemies.add(testMinion1);
            }
        }
    }

    @Override
    public void initializeLevel(Map map) {
        if(map == null) return;
        List<Tile> floorTiles = map.getFloorTiles();
        if(floorTiles.size() == 0) return;
        Random random = new Random();
        for(MainCharacter mc : _players){
            Tile emptyTile = floorTiles.get(random.nextInt(floorTiles.size()));
            if(emptyTile == null) break;
            mc.transform.setPosition(emptyTile.transform.position);
            floorTiles.remove(emptyTile);
        }
        for(Enemy enemy : _enemies){
            Tile emptyTile = floorTiles.get(random.nextInt(floorTiles.size()));
            if(emptyTile == null) break;
            enemy.transform.setPosition(emptyTile.transform.position);
            floorTiles.remove(emptyTile);
        }
        for(Item item : _items){
            Tile emptyTile = floorTiles.get(random.nextInt(floorTiles.size()));
            if(emptyTile == null) break;
            item.transform.setPosition(emptyTile.transform.position);
            floorTiles.remove(emptyTile);
        }
    }
}
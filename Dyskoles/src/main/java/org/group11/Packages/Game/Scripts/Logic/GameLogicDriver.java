package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Game.Scripts.Character_Scripts.Enemy;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.group11.Packages.Game.Scripts.Item_Scripts.Item;

import java.util.ArrayList;

/**
 * Driver for our game, only one of these should exist
 */
public class GameLogicDriver extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private static Level _gameLevel = null;
    private static Map _gameMap =  null;
    private static Pathfinder _pathfinder = null;
    private static ArrayList<MainCharacter> _playerCharacters = new ArrayList<>();
    private static ArrayList<Enemy> _enemyCharacters = new ArrayList<>();
    private static ArrayList<Item> _items = new ArrayList<Item>();

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Change _gameLevel to specified level
     * @param newLevel the level to set as
     */
    private static void set_gameLevel(Level newLevel){
        return;
    }

    /**
     * Run the game
     */
    private static void logicLoop(){
        return;
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void update() {
        super.update();
    }

    @Override
    public void start() {
        _pathfinder = new Pathfinder();
        super.start();
    }
}

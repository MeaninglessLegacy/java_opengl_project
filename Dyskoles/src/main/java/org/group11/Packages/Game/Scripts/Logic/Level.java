package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Game.Scripts.Character_Scripts.Enemy;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.group11.Packages.Game.Scripts.Item_Scripts.Item;

import java.util.ArrayList;

/**
 * Abstract level class, contains information on how to build the level
 */
public abstract class Level {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    protected MapGenerator _mapGenerator = null;
    protected ArrayList<MainCharacter> _players = new ArrayList<>();
    protected ArrayList<Enemy> _enemies = new ArrayList<>();
    protected ArrayList<Item> _items = new ArrayList<>();
    protected int levelOfEnemies;

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Moves characters, enemies and items to appropriate spawn locations.
     * @param map Map to initialize characters, enemies and items positions to.
     */
    public void initializeLevel(Map map){return;}

    /**
     * Returns this level's _mapGenerator
     * @return this level's map generator
     */
    public MapGenerator get_mapGenerator(){ return _mapGenerator; }

    /**
     * Returns this level's _players list
     * @return this level's _players list
     */
    public ArrayList<MainCharacter> get_players() { return _players; }

    /**
     * Returns this level's _enemies list
     * @return this level's _enemies list
     */
    public ArrayList<Enemy> get_enemies() { return _enemies; }

    /**
     * Returns this level's _items list
     * @return this level's _items list
     */
    public ArrayList<Item> get_items() { return _items; }
}

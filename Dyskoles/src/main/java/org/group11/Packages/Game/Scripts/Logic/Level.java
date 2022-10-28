package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Game.Scripts.Character_Scripts.Enemy;

import java.util.ArrayList;

/**
 * Abstract level class, contains information on how to build the level
 */
public abstract class Level {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    protected MapGenerator _mapGenerator = null;
    protected ArrayList<Enemy> _enemies = new ArrayList<Enemy>();
    protected int levelOfEnemies;

    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    /**
     * Returns this level's _mapGenerator
     * @return this level's map generator
     */
    public MapGenerator get_mapGenerator(){
        return _mapGenerator;
    }
}

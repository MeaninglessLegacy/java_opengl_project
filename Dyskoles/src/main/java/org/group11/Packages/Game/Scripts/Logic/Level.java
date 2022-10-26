package org.group11.Packages.Game.Scripts.Logic;

/**
 * Abstract level class, contains information on how to build the level
 */
public abstract class Level {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    protected MapGenerator _mapGenerator = null;

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

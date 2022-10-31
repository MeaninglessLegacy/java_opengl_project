package org.group11.Packages.Game.Scripts.Levels;

import org.group11.Packages.Game.Scripts.Logic.Level;
import org.group11.Packages.Game.Scripts.MapGenerators.SquareRoom;

/**
 *
 */
public class TestRoom extends Level {
    public TestRoom(){
        _mapGenerator = new SquareRoom();
    }
}

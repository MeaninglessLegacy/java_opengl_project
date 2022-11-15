package org.group11.Packages.Game.Scripts.Levels;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.group11.Packages.Game.Scripts.Logic.Level;
import org.group11.Packages.Game.Scripts.MapGenerators.TestArea;

public class TestLevel extends Level {
    public TestLevel() {
        _mapGenerator = new TestArea();

        _players.add(new MainCharacter(new Vector3(0, 0, 0)));
    }
}

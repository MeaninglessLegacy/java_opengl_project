package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Game.Scripts.Logic.GameLogicDriver;
import org.junit.Before;
import org.junit.Test;

public class MainCharacterTest {
    MainCharacter MC;

    @Before
    void setup() {
        MC = new MainCharacter();
    }

    @Test
    void testAddExp() {
        MC.addExp(2);
        assert(MC._statBlock.get_exp() == 2);

        // After this, MC should level up and exp should reset1
        MC.addExp(3);
        assert(MC._statBlock.get_exp() == 0 && MC._statBlock.get_lvl() == 2);
    }
}

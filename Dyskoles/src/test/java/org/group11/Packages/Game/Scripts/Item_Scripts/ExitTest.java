package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.junit.Before;
import org.junit.Test;

public class ExitTest {
    private Exit exit;
    private MainCharacter MC;

    @Before
    public void setup() {
        exit = new Exit();
        MC = new MainCharacter();
    }

    /**
     * Tests whether if a MainCharacter with no key in their backpack can activate an Exit
     */
    @Test
    public void noKeyActivate() {
        assert(!exit.activate(MC));
    }

    // TODO: figure out what to do when MC has a key
}

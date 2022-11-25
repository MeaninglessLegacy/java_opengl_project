package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Game.Scripts.TestSetup;
import org.junit.Test;

/**
 * Runs tests on various methods for the Exit class
 */
public class ExitTest extends TestSetup {
    //******************************************************************************************************************
    //* tests
    //******************************************************************************************************************
    /**
     * Tests Exit's activate method when the activating MainCharacter has no key in their Backpack
     */
    @Test
    public void noKeyActivate() {
        assert(!exit.activate(MC));
    }
}

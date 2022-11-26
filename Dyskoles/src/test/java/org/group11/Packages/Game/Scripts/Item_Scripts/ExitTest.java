package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Core.DataStructures.Vector3;
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
     * Tests Exit's constructor
     */
    @Test
    public void ExitConstructorTest() {
        Vector3 newPos = new Vector3(1, 1, 0);
        Exit exit1 = new Exit(newPos);

        assert(exit1.transform.position.x == newPos.x && exit1.transform.position.y == newPos.y);
        assert(exit1.spriteRenderer != null);
    }

    /**
     * Tests Exit's activate method when the activating MainCharacter has no key in their Backpack
     */
    @Test
    public void noKeyActivate() {
        assert(!exit.activate(MC));
    }
}

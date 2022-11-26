package org.group11.Packages.Game.Scripts.UI_Scripts;

import org.group11.Packages.Game.Scripts.TestSetup;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites.MoveCD1Sprite;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites.MoveCD2Sprite;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites.MoveCD3Sprite;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites.MoveCDSprites;
import org.junit.Test;


/**
 * Runs tests on various methods for the MoveCountdown class
 */
public class MoveCountdownTest extends TestSetup {
    //******************************************************************************************************************
    //* tests
    //******************************************************************************************************************
    /**
     * Tests MoveCountdown's constructor
     */
    @Test
    public void MoveCountdownConstructorTest() {
        MoveCountdown moveCountdown1 = new MoveCountdown(boss);
        for (MoveCDSprites s : moveCountdown1.CDSpriteList) {
            assert(s.getCDSprite().get_sprite() != null);
        }
    }

    /**
     * Tests that all the MoveCDSprites have been set in the correct position in moveCountdown's arraylist
     */
    @Test
    public void CDSpriteArrayListTest() {
        assert(moveCountdown.CDSpriteList.get(0) instanceof MoveCD1Sprite);
        assert(moveCountdown.CDSpriteList.get(1) instanceof MoveCD2Sprite);
        assert(moveCountdown.CDSpriteList.get(2) instanceof MoveCD3Sprite);
    }

    /**
     * Tests that MoveCountdown's method sets the correct sprite when the remaining ticks before an Enemy moves is 1
     */
    @Test
    public void MoveCD1EnabledTest() {
        moveCountdown.changeCountdown(1);
        assert(moveCountdown.CDSpriteList.get(0).getCDSprite().enabled);
        assert(!moveCountdown.CDSpriteList.get(1).getCDSprite().enabled);
        assert(!moveCountdown.CDSpriteList.get(2).getCDSprite().enabled);
    }

    /**
     * Tests that MoveCountdown's method sets the correct sprite when the remaining ticks before an Enemy moves is 2
     */
    @Test
    public void MoveCD2EnabledTest() {
        moveCountdown.changeCountdown(2);
        assert(!moveCountdown.CDSpriteList.get(0).getCDSprite().enabled);
        assert(moveCountdown.CDSpriteList.get(1).getCDSprite().enabled);
        assert(!moveCountdown.CDSpriteList.get(2).getCDSprite().enabled);
    }

    /**
     * Tests that MoveCountdown's method sets the correct sprite when the remaining ticks before an Enemy moves is 3
     */
    @Test
    public void MoveCD3EnabledTest() {
        moveCountdown.changeCountdown(3);
        assert(!moveCountdown.CDSpriteList.get(0).getCDSprite().enabled);
        assert(!moveCountdown.CDSpriteList.get(1).getCDSprite().enabled);
        assert(moveCountdown.CDSpriteList.get(2).getCDSprite().enabled);
    }
}

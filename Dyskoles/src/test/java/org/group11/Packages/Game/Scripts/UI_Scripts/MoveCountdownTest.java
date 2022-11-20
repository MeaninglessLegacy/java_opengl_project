package org.group11.Packages.Game.Scripts.UI_Scripts;

import org.group11.Packages.Game.Scripts.Character_Scripts.Boss;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites.MoveCD1Sprite;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites.MoveCD2Sprite;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites.MoveCD3Sprite;
import org.junit.Before;
import org.junit.Test;

public class MoveCountdownTest {
    private Boss boss;
    private MoveCountdown moveCountdown;

    @Before
    public void setup() {
        boss = new Boss();
        moveCountdown = new MoveCountdown(boss);
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
     * Tests that the correct sprite is enabled when the remaining ticks before move for an enemy is 1
     */
    @Test
    public void MoveCD1EnabledTest() {
        boss.set_moveCountdownNumber(1);
        assert(moveCountdown.CDSpriteList.get(0).getCDSprite().enabled);
        assert(!moveCountdown.CDSpriteList.get(1).getCDSprite().enabled);
        assert(!moveCountdown.CDSpriteList.get(2).getCDSprite().enabled);
    }

    /**
     * Tests that the correct sprite is enabled when the remaining ticks before move for an enemy is 2
     */
    @Test
    public void MoveCD2EnabledTest() {
        boss.set_moveCountdownNumber(2);
        assert(!moveCountdown.CDSpriteList.get(0).getCDSprite().enabled);
        assert(moveCountdown.CDSpriteList.get(1).getCDSprite().enabled);
        assert(!moveCountdown.CDSpriteList.get(2).getCDSprite().enabled);
    }

    /**
     * Tests that the correct sprite is enabled when the remaining ticks before move for an enemy is 3
     */
    @Test
    public void MoveCD3EnabledTest() {
        boss.set_moveCountdownNumber(3);
        assert(!moveCountdown.CDSpriteList.get(0).getCDSprite().enabled);
        assert(!moveCountdown.CDSpriteList.get(1).getCDSprite().enabled);
        assert(moveCountdown.CDSpriteList.get(2).getCDSprite().enabled);
    }
}

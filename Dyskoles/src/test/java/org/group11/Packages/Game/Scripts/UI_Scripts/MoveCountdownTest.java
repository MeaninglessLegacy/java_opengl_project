package org.group11.Packages.Game.Scripts.UI_Scripts;

import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Character_Scripts.Boss;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites.MoveCD1Sprite;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites.MoveCD2Sprite;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites.MoveCD3Sprite;
import org.junit.Before;
import org.junit.Test;

/**
 * Runs tests on various methods for the MoveCountdown class
 */
public class MoveCountdownTest {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    boolean everythingInstantiated = false;
    private Engine engine;
    private Scene scene;

    private Boss boss;
    private MoveCountdown moveCountdown;

    //******************************************************************************************************************
    //* setup
    //******************************************************************************************************************
    private class SetupClass extends GameObject {
        @Override
        public void start() {
            // General instantiations
            boss = new Boss();
            moveCountdown = new MoveCountdown(boss);

            scene.Instantiate(boss);
            scene.Instantiate(moveCountdown);

            everythingInstantiated = true;
        }
    }

    @Before
    public void setup() {
        engine = new Engine();
        engine.start();
        scene = Scene.get_scene();

        SetupClass setupClass = new SetupClass();
        scene.Instantiate(setupClass);

        while (!everythingInstantiated) {
            System.out.print("");
        }
    }

    //******************************************************************************************************************
    //* tests
    //******************************************************************************************************************
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

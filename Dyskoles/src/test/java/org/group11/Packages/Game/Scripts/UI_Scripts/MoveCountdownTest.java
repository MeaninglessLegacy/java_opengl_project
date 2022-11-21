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
            scene.Instantiate(boss);

            moveCountdown= new MoveCountdown(boss);
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

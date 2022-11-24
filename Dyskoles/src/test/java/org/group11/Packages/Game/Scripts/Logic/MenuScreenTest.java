package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.*;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites.SpaceNextLevel;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites.SpaceRetry;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites.SpaceStartGame;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites.SpaceStartOver;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class MenuScreenTest {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    boolean everythingInstantiated = false;
    private Engine engine;
    private Scene scene;

    private MenuScreen menu;

    //******************************************************************************************************************
    //* setup
    //******************************************************************************************************************
    private class SetupClass extends GameObject {
        @Override
        public void start() {
            // General instantiations
            menu = new MenuScreen();

            scene.Instantiate(menu);

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
     * Tests that the correct menu elements are enabled at the start of the game (when the MenuScreen is initialized)
     */
    @Test
    public void startMenuTest() {
        Collection<MenuElement> spriteList = menu.menuElements.values();
        for (MenuElement e : spriteList) {
            if (e instanceof Instructions || e instanceof SpaceStartGame) {
                assert(e.getSprite().enabled);
            }
            else {
                assert(!e.getSprite().enabled);
            }
        }
    }

    /**
     * Tests that the correct menu elements are enabled when the player loses the game by losing all their health
     */
    @Test
    public void playerLoseTest() {
        scene.Destroy(menu);
        menu.createMenu(false, 1, 1);

        Collection<MenuElement> spriteList = menu.menuElements.values();
        for (MenuElement e : spriteList) {
            if (e instanceof GameOver || e instanceof HealthReduced0 || e instanceof SpaceRetry) {
                assert(e.getSprite().enabled);
            }
            else {
                assert(!e.getSprite().enabled);
            }
        }
    }

    /**
     * Tests that the correct menu elements are enabled when the player beats the level by reaching the exit with a key,
     * and there are more levels afterwards
     */
    @Test
    public void playerWinNextLevelTest() {
        scene.Destroy(menu);
        menu.createMenu(true, 1, 2);

        Collection<MenuElement> spriteList = menu.menuElements.values();
        for (MenuElement e : spriteList) {
            System.out.println(e.getClass().getName() + ", " + e.getSprite().enabled);
            if (e instanceof YouWin || e instanceof ExitReached || e instanceof SpaceNextLevel) {
                assert(e.getSprite().enabled);
            }
            else {
                assert(!e.getSprite().enabled);
            }
        }
    }

    /**
     * Tests that the correct menu elements are enabled when the player beats the level by reaching the exit with a key,
     * but there are no more levels afterwards, thus game would reset to the first level
     */
    @Test
    public void playerWinEndOfLevelsTest() {
        scene.Destroy(menu);
        menu.createMenu(true, 1, 1);

        Collection<MenuElement> spriteList = menu.menuElements.values();
        for (MenuElement e : spriteList) {
            if (e instanceof YouWin || e instanceof ExitReached || e instanceof SpaceStartOver) {
                assert(e.getSprite().enabled);
            }
            else {
                assert(!e.getSprite().enabled);
            }
        }
    }
}

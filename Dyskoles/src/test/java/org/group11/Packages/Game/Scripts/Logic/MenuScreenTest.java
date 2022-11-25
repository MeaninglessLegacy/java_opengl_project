package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Game.Scripts.TestSetup;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.*;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites.SpaceNextLevel;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites.SpaceRetry;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites.SpaceStartGame;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites.SpaceStartOver;
import org.junit.Test;

import java.util.Collection;

/**
 * Runs tests on various methods for the MenuScreen class
 */
public class MenuScreenTest extends TestSetup {
    //******************************************************************************************************************
    //* tests
    //******************************************************************************************************************
    /**
     * Tests that the correct menu elements are enabled at the start of the game (when the MenuScreen is initialized)
     */
    @Test
    public void startMenuTest() {
        Collection<MenuElement> spriteList = menuScreen.menuElements.values();
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
        scene.Destroy(menuScreen);
        menuScreen.createMenu(false, 1, 1);

        Collection<MenuElement> spriteList = menuScreen.menuElements.values();
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
        scene.Destroy(menuScreen);
        menuScreen.createMenu(true, 1, 2);

        Collection<MenuElement> spriteList = menuScreen.menuElements.values();
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
        scene.Destroy(menuScreen);
        menuScreen.createMenu(true, 1, 1);

        Collection<MenuElement> spriteList = menuScreen.menuElements.values();
        for (MenuElement e : spriteList) {
            if (e instanceof YouWin || e instanceof ExitReached || e instanceof SpaceStartOver) {
                assert(e.getSprite().enabled);
            }
            else {
                assert(!e.getSprite().enabled);
            }
        }
    }

    /**
     * Tests that the sprites that say "press space to ___" properly 'blink'
     */
    @Test
    public void blinkingSpaceSpritesTest() {
        long curTime = System.currentTimeMillis();
        int timeBeforeToggle = 1000;

        menuScreen.enableSprite("spaceStartGame");
        menuScreen.enableSprite("spaceNextLevel");
        menuScreen.enableSprite("spaceRetry");
        menuScreen.enableSprite("spaceStartOver");
        assert(menuScreen.menuElements.get("spaceStartGame").getSprite().enabled);
        assert(menuScreen.menuElements.get("spaceNextLevel").getSprite().enabled);
        assert(menuScreen.menuElements.get("spaceRetry").getSprite().enabled);
        assert(menuScreen.menuElements.get("spaceStartOver").getSprite().enabled);

        boolean spritesShouldBeActivated = true;
        while (spritesShouldBeActivated) {
            if (System.currentTimeMillis() - curTime > timeBeforeToggle + 100) {
                spritesShouldBeActivated = false;
            }
        }

        assert(!menuScreen.menuElements.get("spaceStartGame").getSprite().enabled);
        assert(!menuScreen.menuElements.get("spaceNextLevel").getSprite().enabled);
        assert(!menuScreen.menuElements.get("spaceRetry").getSprite().enabled);
        assert(!menuScreen.menuElements.get("spaceStartOver").getSprite().enabled);
    }
}

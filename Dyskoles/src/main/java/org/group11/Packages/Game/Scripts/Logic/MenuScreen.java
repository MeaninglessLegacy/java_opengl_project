package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.*;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites.SpaceNextLevel;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites.SpaceRetry;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites.SpaceStartGame;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites.SpaceStartOver;

import java.util.Collection;
import java.util.HashMap;

public class MenuScreen extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private Scene scene;

    protected HashMap<MenuScreenElements, MenuElement> menuElements = new HashMap<>();

    private boolean onMenu;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public MenuScreen() {
        scene = Scene.get_scene();

        menuElements.put(MenuScreenElements.menuBackground, new MenuBackground());
        menuElements.put(MenuScreenElements.instructions, new Instructions());
        menuElements.put(MenuScreenElements.youWin, new YouWin());
        menuElements.put(MenuScreenElements.exitReached, new ExitReached());
        menuElements.put(MenuScreenElements.gameOver, new GameOver());
        menuElements.put(MenuScreenElements.healthReduced0, new HealthReduced0());
        menuElements.put(MenuScreenElements.spaceStartGame, new SpaceStartGame());
        menuElements.put(MenuScreenElements.spaceNextLevel, new SpaceNextLevel());
        menuElements.put(MenuScreenElements.spaceRetry, new SpaceRetry());
        menuElements.put(MenuScreenElements.spaceStartOver, new SpaceStartOver());

        disableSprites();
        createTitleScreen();
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Enables the sprite required to create the beginning menu
     */
    private void createTitleScreen() {
        enableSprite(MenuScreenElements.instructions);
        enableSprite(MenuScreenElements.spaceStartGame);
    }

    /**
     * Enables the correct sprites to create the appropriate menu based on the condition of the game
     * @param won whether the player has won a round or not
     * @param _gameStage the current stage that the player is on
     * @param numberOfLevels the number of levels in the game
     */
    public void createMenu(boolean won, int _gameStage, int numberOfLevels) {
        if (won) {
            enableSprite(MenuScreenElements.youWin);
            enableSprite(MenuScreenElements.exitReached);
            if (_gameStage < numberOfLevels) {
                enableSprite(MenuScreenElements.spaceNextLevel);
            }
            else {
                enableSprite(MenuScreenElements.spaceStartOver);
            }
        }
        else {
            enableSprite(MenuScreenElements.gameOver);
            enableSprite(MenuScreenElements.healthReduced0);
            enableSprite(MenuScreenElements.spaceRetry);
        }
    }

    /**
     * Gets the MenuElement specified by the parameter string and attempts to enable it
     * @param s the string by which to try and get the MenuElement
     */
    protected void enableSprite(MenuScreenElements s) {
        MenuElement element = menuElements.get(s);
        if (element != null) {
            scene.Instantiate(element);
            element.getSprite().enabled = true;
        }
        else {
            System.out.println("Unable to retrieve menu sprite with given argument");
        }
    }

    /**
     * Disables all the menu sprites
     */
    private void disableSprites() {
        Collection<MenuElement> toDisable = menuElements.values();
        for (MenuElement e : toDisable) {
            e.getSprite().enabled = false;
            scene.Destroy(e);
        }
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void start() {
        onMenu = true;
    }

    @Override
    public void onKeyDown(int key) {
        if (onMenu) {
            if (key == ' ') {
                onMenu = false;
                disableSprites();
                GameLogicDriver.startNewLevel();
            }
        }
        super.onKeyDown(key);
    }

    @Override
    public void Delete() {
        disableSprites();
    }
}

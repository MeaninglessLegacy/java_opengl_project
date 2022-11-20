package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.*;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites.SpaceNextLevel;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites.SpaceRetry;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites.SpaceStartGame;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites.SpaceStartOver;

import java.util.HashMap;
import java.util.Set;

public class MenuScreen extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private Scene scene;

    HashMap<String, MenuElement> menuElements = new HashMap<>();

    private boolean onMenu;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public MenuScreen() {
        menuElements.put("menuBackground", new MenuBackground());
        menuElements.put("Instructions", new Instructions());
        menuElements.put("youWin", new YouWin());
        menuElements.put("exitReached", new ExitReached());
        menuElements.put("gameOver", new GameOver());
        menuElements.put("healthReduced0", new HealthReduced0());
        menuElements.put("spaceStartGame", new SpaceStartGame());
        menuElements.put("spaceNextLevel", new SpaceNextLevel());
        menuElements.put("spaceRetry", new SpaceRetry());
        menuElements.put("spaceStartOver", new SpaceStartOver());
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    private void createTitleScreen() {

    }

    public void createMenu(boolean won, int _gameStage, int numberOfLevels) {
        /*
        if (won) {
            scene.Instantiate(menuElements.get("youWin"));
            scene.Instantiate(menuElements.get("exitReached"));
            if (_gameStage < numberOfLevels) {
                scene.Instantiate(menuElements.get("spaceNextLevel"));
            }
            else {
                scene.Instantiate(menuElements.get("spaceStartOver"));
            }
        }
        else {
            scene.Instantiate(menuElements.get("gameOver"));
            scene.Instantiate(menuElements.get("healthReduced0"));
            scene.Instantiate(menuElements.get("spaceRetry"));
        }
        */



        scene.Instantiate(menuElements.get("gameOver"));
        scene.Instantiate(menuElements.get("spaceRetry"));
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void update() {
        super.update();
    }

    @Override
    public void start() {
        scene = Scene.get_scene();
        onMenu = true;

        createMenu(true, 1, 1);
    }

    @Override
    public void onKeyDown(int key) {
        if (onMenu) {
            if (key == ' ') {
                onMenu = false;
                GameLogicDriver.startNewLevel();
            }
        }
        super.onKeyDown(key);
    }

    @Override
    public void Delete() {
        Set<String> toDelete = menuElements.keySet();
        for (String s : toDelete) {
            scene.Destroy(menuElements.get(s));
        }
    }
}

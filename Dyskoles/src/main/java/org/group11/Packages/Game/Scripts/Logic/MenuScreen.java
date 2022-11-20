package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.GameOver;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.PressSpaceSprites.SpaceRetry;

public class MenuScreen extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private Scene scene;
    private final GameOver gameOverSprite;
    private final SpaceRetry spaceRetrySprite;

    private boolean onMenu;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public MenuScreen() {
        gameOverSprite = new GameOver();
        spaceRetrySprite = new SpaceRetry();
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    public void createMenu() {
        scene.Instantiate(gameOverSprite);
        scene.Instantiate(spaceRetrySprite);
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

        createMenu();
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
        scene.Destroy(gameOverSprite);
        scene.Destroy(spaceRetrySprite);
    }
}

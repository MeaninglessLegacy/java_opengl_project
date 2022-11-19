package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.UI_Scripts.Menu_Scripts.GameOver;

public class MenuScreen extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private Scene scene;
    private GameLogicDriver GLD;
    private GameOver gameOverSprite;

    private boolean onMenu;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public MenuScreen() {
        gameOverSprite = new GameOver();

        onMenu = true;
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************

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
        GLD = GameLogicDriver.getInstance();
        super.start();
    }

    @Override
    public void onKeyDown(int key) {
        if (onMenu) {
            if (key == ' ') {
                System.out.println("space pressed");
                onMenu = false;
                scene.Destroy(this);
                GLD.startNewLevel();
            }
        }
        super.onKeyDown(key);
    }

    @Override
    public void Delete() {
        scene.Destroy(gameOverSprite);
    }
}

package org.group11;

import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Logic.GameLogicDriver;

public class MainTest {
    private static Engine engine;
    private static Scene scene;
    public static void main(String[] args) {
        engine = new Engine();
        engine.start();
        scene = Scene.get_scene();
        GameLogicDriver gameLogicDriver = GameLogicDriver.getInstance();
        scene.Instantiate(gameLogicDriver);
    }
}
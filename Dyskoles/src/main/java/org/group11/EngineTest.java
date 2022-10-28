package org.group11;

import org.group11.Packages.Engine.Engine;
import org.group11.Packages.Engine.Scene;
import org.group11.Packages.Game.Scripts.Logic.GameLogicDriver;

public class EngineTest {
    public static void main(String[] args){
        Engine eng = new Engine();
        eng.start();
        Scene.Instantiate(new GameLogicDriver());
    }
}

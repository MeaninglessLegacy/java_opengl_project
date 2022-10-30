package org.group11;

import org.group11.Packages.Core.Main.Engine;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Logic.GameLogicDriver;

/*
--add-exports
java.base/java.lang=ALL-UNNAMED
--add-exports
java.desktop/sun.awt=ALL-UNNAMED
--add-exports
java.desktop/sun.java2d=ALL-UNNAMED
 */

public class EngineTest {
    private static int counter = 0;
    public static void main(String[] args){
        Engine eng = new Engine();
        Scene scene = Scene.get_scene();
        eng.start();
        scene.Instantiate(new GameLogicDriver());

        GameObject newPlayer = scene.Instantiate(new PlayerObject("player-I"));
        System.out.println("Instantiated: "+newPlayer);
    }
}

class PlayerObject extends GameObject {
    private String name;
    PlayerObject(String n){
        name = n;
    }

    @Override
    public void update() {
    }

    @Override
    public void start() {
    }

    @Override
    public void onKeyDown(int key) {
        if(key==65){
            this.transform.position.x -= 0.1;
            System.out.println(this.transform.position.x +","+ this.transform.position.y+","+this.transform.position.z);
        }
    }
}

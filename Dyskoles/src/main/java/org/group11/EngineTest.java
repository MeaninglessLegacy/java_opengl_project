package org.group11;

import org.group11.Packages.Engine.Engine;
import org.group11.Packages.Engine.GameObject;
import org.group11.Packages.Engine.Scene;
import org.group11.Packages.Game.Scripts.Logic.GameLogicDriver;

public class EngineTest {
    private static int counter = 0;
    public static void main(String[] args){
        Engine eng = new Engine();
        eng.start();
        Scene.Instantiate(new GameLogicDriver());

        GameObject newPlayer = Scene.Instantiate(new PlayerObject("player-I"));
        System.out.println(newPlayer);
    }
}

class PlayerObject extends GameObject {
    private String name;
    PlayerObject(String n){
        name = n;
    }
    private long timerStart = 0;
    @Override
    public void update() {
        System.out.println("My name is: "+name);
        selfDelete();
    }

    @Override
    public void start() {
        timerStart = System.currentTimeMillis();
        if(name.length() < 10){
            Scene.Instantiate(new PlayerObject(name+"I"));
        }
    }

    public void selfDelete(){
        if(System.currentTimeMillis() - timerStart > 1000){
            System.out.println(name+" destruction status: "+Scene.Destroy(this));
        }
    }
}

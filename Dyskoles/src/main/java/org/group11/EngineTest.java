package org.group11;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector2;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Camera;
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
        scene.Instantiate(new Creator());
        //scene.Instantiate(new PlayerObject("lmao", true));
    }
}

class Creator extends GameObject {
    @Override
    public void start() {
        Scene scene = Scene.get_scene();
        long time = System.currentTimeMillis();
        GameObject newPlayer = scene.Instantiate(new PlayerObject("player-I", true));
        System.out.println("Instantiated: "+newPlayer);
        System.out.println("Time taken: "+(System.currentTimeMillis()-time));
        time = System.currentTimeMillis();
        GameObject newPlayer2 = scene.Instantiate(new PlayerObject("player-II", false));
        newPlayer2.transform.position.x = -2;
        System.out.println("Instantiated: "+newPlayer);
        System.out.println("Time taken: "+(System.currentTimeMillis()-time));
        time = System.currentTimeMillis();
        Camera newCamera = new movingCamera();
        newCamera.transform.rotation.x = 3.14f;
        newCamera.transform.position.z = -5;
        scene.Instantiate(newCamera);
        scene.set_mainCamera(newCamera);
        System.out.println("Instantiated: "+newCamera);
        System.out.println("Time taken: "+(System.currentTimeMillis()-time));
    }
}

class PlayerObject extends GameObject {
    private String name;
    private SpriteRenderer spriteRenderer;

    private double time = 0;
    private double x = 0;

    private boolean move = true;

    private Vector3 velocity = new Vector3();
    private float velocityThreshold = 0.01f;
    private char state = 0;
    private int frame = 1;
    private double lastFrameTime = 0;

    private boolean facingRight = true;
    PlayerObject(String n, boolean move){
        spriteRenderer = new SpriteRenderer(this,"./Resources/ump45/ump45.png");
        this.addComponent(spriteRenderer);
        name = n;
        this.move = move;
    }

    @Override
    public void update() {
        // animate bob
        double timepassed = System.currentTimeMillis() - time;
        if(x < 2) {
            x += timepassed / 500;
        }else{
            x = 0;
        }
        double yScale = -Math.pow((x-1),4)+1;
        spriteRenderer.get_sprite().set_scale(1, (float)(1+0.05*yScale),0);
        time = System.currentTimeMillis();
        // animate run
        lastFrameTime += timepassed;
        if(lastFrameTime > 50){
            if(state == 0){
                spriteRenderer.get_sprite().set_texture("./Resources/ump45/ump45.png");
            }else if(state == 1){
                if(frame < 18){
                    spriteRenderer.get_sprite().set_texture("./Resources/ump45/run/ump45 ("+frame+").png");
                    frame++;
                }else{
                    spriteRenderer.get_sprite().set_texture("./Resources/ump45/run/ump45 ("+frame+").png");
                    frame = 1;
                }
            }
            lastFrameTime = 0;
        }
        // slow down
        if(velocity.x > 0){
            velocity.x -= 0.001;
        }
        if(velocity.x < 0){
            velocity.x += 0.001;
        }
        if(velocity.y > 0){
            velocity.y -= 0.001;
        }
        if(velocity.y < 0){
            velocity.y += 0.001;
        }
        // mov
        if(velocity.x > velocityThreshold){
            if(facingRight == false) spriteRenderer.get_sprite().flipX();
            facingRight = true;
        }else if(velocity.x < -velocityThreshold){
            if(facingRight == true) spriteRenderer.get_sprite().flipX();
            facingRight = false;
        }
        if(Math.abs(velocity.x) > velocityThreshold){
            this.transform.position.x += velocity.x;
            if(state != 1) {
                state = 1;
            }
        }
        if(Math.abs(velocity.y) > velocityThreshold){
            this.transform.position.y += velocity.y;
            if(state != 1) {
                state = 1;
            }
        }
        if(Math.abs(velocity.x) < velocityThreshold && Math.abs(velocity.y) < velocityThreshold){
            if(state != 0) {
                state = 0;
            }
        }
    }

    @Override
    public void start() {
    }

    @Override
    public void onKeyDown(int key) {
        if(!move) return;
        if(key==65){
            if(velocity.x > -0.05){
                velocity.x -= 0.003;
            }
        }
        if(key==68){
            if(velocity.x < 0.05){
                velocity.x += 0.003;
            }
        }
        if(key==83){
            if(velocity.y > -0.05){
                velocity.y -= 0.003;
            }
        }
        if(key==87){
            if(velocity.y < 0.05){
                velocity.y += 0.003;
            }
        }
        if(key==78){ // n
            spriteRenderer.enabled = false;
        }
        if(key==77){ //m
            spriteRenderer.enabled = true;
        }
        if(key==79){ //o
            spriteRenderer.get_sprite().set_scale(new Vector3(0.5f,0.5f,0f));
        }
        if(key==90){ //z
            spriteRenderer.get_sprite().set_scale(new Vector3(1f,1f,0f));
        }
        if(key==89){ //y
            Scene.get_scene().Destroy(this);
        }
        if(key==71){ //g
            spriteRenderer.get_sprite().set_texture("./Resources/ump9.png");
        }
        if(key==72){ //h
            spriteRenderer.get_sprite().set_texture("./Resources/ump45.png");
        }
    }
}

class movingCamera extends Camera {
    @Override
    public void onKeyDown(int key) {
        if(key==74){ // j
            this.transform.position.x -= 0.02;
        }
        if(key==75){ // k
            this.transform.position.y -= 0.02;
        }
        if(key==76){ // l
            this.transform.position.x += 0.02;
        }
        if(key==73){ // i
            this.transform.position.y += 0.02;
        }
        if(key==82){ // r
            this.transform.position.z += 0.02;
        }
        if(key==84){ // t
            this.transform.position.z -= 0.02;
        }
        if(key == '1'){
            this.transform.rotation.y += 0.01;
        }
        if(key == '2'){
            this.transform.rotation.y -= 0.01;
        }
        if(key == '3'){
            this.transform.rotation.x += 0.01;
        }
        if(key == '4'){
            this.transform.rotation.x -= 0.01;
        }
    }
}
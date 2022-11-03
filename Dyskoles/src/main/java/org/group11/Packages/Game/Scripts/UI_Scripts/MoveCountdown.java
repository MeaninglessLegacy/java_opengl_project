package org.group11.Packages.Game.Scripts.UI_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Character_Scripts.Enemy;

import java.util.ArrayList;

public class MoveCountdown extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // Sprites that show how many ticks left until an enemy moves
    SpriteRenderer countdown1;
    SpriteRenderer countdown2;
    SpriteRenderer countdown3;
    // Stores all the sprites to iterate over for various methods
    ArrayList<SpriteRenderer> cdList = new ArrayList<>();

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public MoveCountdown(Enemy enemy) {
        countdown1 = new SpriteRenderer(this, "./Resources/countdown1.png");
        this.addComponent(countdown1);
        cdList.add(countdown1);
        countdown2 = new SpriteRenderer(this, "./Resources/countdown2.png");
        this.addComponent(countdown2);
        cdList.add(countdown2);
        countdown3 = new SpriteRenderer(this, "./Resources/countdown3.png");
        this.addComponent(countdown3);
        cdList.add(countdown3);

        // Binds this MoveCountdown's position to the enemy it's for
        this.transform = enemy.transform;

        setupSprites();
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    private void setupSprites() {
        for (SpriteRenderer sprite : cdList) {
            sprite.shiftSprite('y', (float)0.75);
            sprite.get_sprite().set_scale((float)0.5, (float)0.5);
        }
        disableSprites();
    }

    private void disableSprites() {
        for (SpriteRenderer sprite : cdList) {
            sprite.enabled = false;
        }
    }

    public void changeCountdown(int ticksUntilMove) {
        disableSprites();
        if (ticksUntilMove >= 1 && ticksUntilMove <= 3) {
            cdList.get(ticksUntilMove - 1).enabled = true;
        }
        else {
            cdList.get(2).enabled = true;
        }
    }
}

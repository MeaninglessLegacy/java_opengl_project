package org.group11.Packages.Game.Scripts.UI_Scripts;

import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Character_Scripts.Enemy;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites.MoveCD1Sprite;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites.MoveCD2Sprite;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites.MoveCD3Sprite;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites.MoveCDSprites;

import java.util.ArrayList;

public class MoveCountdown extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************

    MoveCD1Sprite countdown1;
    MoveCD2Sprite countdown2;
    MoveCD3Sprite countdown3;

    ArrayList<MoveCDSprites> CDSpriteList = new ArrayList<>();

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public MoveCountdown(Enemy enemy) {
        countdown1 = new MoveCD1Sprite(enemy);
        CDSpriteList.add(countdown1);
        countdown2 = new MoveCD2Sprite(enemy);
        CDSpriteList.add(countdown2);
        countdown3 = new MoveCD3Sprite(enemy);
        CDSpriteList.add(countdown3);

        setupSprites();
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Sets all the MoveCD Sprites to the correct position and scale
     */
    private void setupSprites() {
        for (MoveCDSprites CDSprite : CDSpriteList) {
            CDSprite.getCDSprite().shiftSprite('y', (float)0.75);
            CDSprite.getCDSprite().shiftSprite('z', (float)-0.6);
            CDSprite.getCDSprite().get_sprite().set_scale((float)0.5, (float)0.5);
        }
        disableSprites();
    }

    /**
     * Disables all MoveCD Sprites
     */
    private void disableSprites() {
        for (MoveCDSprites CDSprite : CDSpriteList) {
            CDSprite.disableSprite();
        }
    }

    /**
     * Enables the correct MoveCD Sprite according to how many ticks left until the Enemy moves
     * @param ticksUntilMove the amount of ticks left until the Enemy move
     */
    public void changeCountdown(int ticksUntilMove) {
        disableSprites();
        if (ticksUntilMove >= 1 && ticksUntilMove <= 3) {
            CDSpriteList.get(ticksUntilMove - 1).enableSprite();
        }
        else {
            CDSpriteList.get(2).enableSprite();
        }
    }

    /**
     * Instantiates all MoveCD Sprites
     * @param scene used to instantiate the sprites
     */
    public void instantiateCDSprites(Scene scene) {
        for (MoveCDSprites CDSprite : CDSpriteList) {
            scene.Instantiate(CDSprite);
        }
    }

    /**
     * Destroys all MoveCD Sprites
     * @param scene used to destroy the sprites
     */
    public void destroyCDSprites(Scene scene) {
        for (MoveCDSprites CDSprite : CDSpriteList) {
            scene.Destroy(CDSprite);
        }
    }
}

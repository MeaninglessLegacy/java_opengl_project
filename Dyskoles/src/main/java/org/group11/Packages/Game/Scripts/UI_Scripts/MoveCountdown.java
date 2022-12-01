package org.group11.Packages.Game.Scripts.UI_Scripts;

import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Character_Scripts.Enemy;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdownSprites.*;

import java.util.ArrayList;

/**
 * Class that stores all the moveCD sprites for an Enemy
 */
public class MoveCountdown extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // Stores all the move countdown sprites
    protected ArrayList<MoveCDSprites> CDSpriteList = new ArrayList<>();

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public MoveCountdown(Enemy enemy) {
        CDSpriteList.add(new MoveCD0Sprite(enemy));
        CDSpriteList.add(new MoveCD1Sprite(enemy));
        CDSpriteList.add(new MoveCD2Sprite(enemy));
        CDSpriteList.add(new MoveCD3Sprite(enemy));

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
            CDSprite.getCDSprite().shiftSprite('y', (float)1);
            CDSprite.getCDSprite().shiftSprite('z', (float)-0.6);
            CDSprite.getCDSprite().get_sprite().set_scale((float)0.5, (float)0.5, 0);
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
        if (ticksUntilMove >= 0 && ticksUntilMove <= 3) {
            CDSpriteList.get(ticksUntilMove).enableSprite();
        }
        else {
            CDSpriteList.get(3).enableSprite();
        }
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void Delete() {
        Scene scene = Scene.get_scene();
        for (MoveCDSprites CDSprite : CDSpriteList) {
            scene.Destroy(CDSprite);
        }
    }

    @Override
    public void update() { super.update(); }

    @Override
    public void start() {
        Scene scene = Scene.get_scene();
        for (MoveCDSprites CDSprite : CDSpriteList) {
            scene.Instantiate(CDSprite);
        }
    }


}

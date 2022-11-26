package org.group11.Packages.Game.Scripts.Item_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;

import static org.group11.Packages.Game.Scripts.Logic.GameLogicDriver.endGame;

/**
 * Exit object, when the player character runs into this object with a key they complete the level
 */
public class Exit extends Item {
    //******************************************************************************************************************
    //* constructor methods
    //******************************************************************************************************************
    public Exit() {
        setupExit();
    }

    public Exit(Vector3 pos) {
        transform.setPosition(pos);
        setupExit();
    }

    private void setupExit() {
        spriteRenderer = new SpriteRenderer(this, "./Resources/prototypeExit1.png");
        this.addComponent(spriteRenderer);
        spriteRenderer.shiftSprite('z', (float)-0.1);
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    /**
     * If the MainCharacter who triggered this Exit has a Key in their backpack, ends the game and the player wins
     * @param c the MainCharacter who triggered the item
     * @return false if the Exit wasn't triggered
     */
    @Override
    public boolean activate(MainCharacter c) {
        Key testKey = new Key();
        if (c.backpack.removeItem(testKey)) {
            endGame(true);
        }
        return false;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        super.update();
    }
}

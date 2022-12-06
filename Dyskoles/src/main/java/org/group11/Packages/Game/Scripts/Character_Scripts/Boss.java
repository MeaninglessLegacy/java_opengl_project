package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBar.HealthBarInside;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBar.HealthBarOutline;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdown;

/**
 * Boss enemy type character, chases the player blindly when activated
 */
public class Boss extends Enemy {
    //******************************************************************************************************************
    //* constructor methods
    //******************************************************************************************************************
    public Boss() {
        setupBoss();
    }

    public Boss(Vector3 pos) {
        transform.setPosition(pos);
        setupBoss();
    }

    private void setupBoss() {
        expGiven = 5;
        _statBlock.set_Atk(3);
        _statBlock.set_MaxHp(10);
        _statBlock.set_hp(10);

        characterSprite = new SpriteRenderer(this, "./Resources/ump9.png");
        setupEnemySprites();
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************

    @Override
    public void update() { super.update(); }
}
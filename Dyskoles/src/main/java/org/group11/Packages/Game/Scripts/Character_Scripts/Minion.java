package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBar.HealthBarInside;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBar.HealthBarOutline;
import org.group11.Packages.Game.Scripts.UI_Scripts.MoveCountdown;

/**
 * Basic enemy type character, Minion chases the player blindly when activated
 */
public class Minion extends Enemy{
    //******************************************************************************************************************
    //* constructor methods
    //******************************************************************************************************************
    public Minion() {
        setupMinion();
    }

    public Minion(Vector3 pos) {
        transform.setPosition(pos);
        setupMinion();
    }

    private void setupMinion() {
        _statBlock.set_Atk(1);
        _statBlock.set_MaxHp(3);
        _statBlock.set_hp(3);

        characterSprite = new SpriteRenderer(this, "./Resources/chibi_screenshot.png");
        setupEnemySprites();

        _chrIsFacingRight = false;
    }
}

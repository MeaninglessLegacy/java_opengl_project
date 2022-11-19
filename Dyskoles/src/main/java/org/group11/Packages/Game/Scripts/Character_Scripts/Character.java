package org.group11.Packages.Game.Scripts.Character_Scripts;

import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBarInside;
import org.group11.Packages.Game.Scripts.UI_Scripts.HealthBarOutline;

/**
 * Base class for Character objects within our game
 */
public abstract class Character extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    // Used to help render and control the character's sprite
    protected SpriteRenderer characterSprite;
    protected boolean facingRight = true;
    double time; // time since last update
    double x; // character scaling parameter for breathing effect

    // Stores all the stats of this character
    protected StatBlock _statBlock = new StatBlock();

    // Displays the health of the character above their sprite
    protected HealthBarOutline _healthBarOutline;
    protected HealthBarInside _healthBarInside;

    // Used to help animate the attack animation for a character
    protected boolean isAttacking = false;
    protected String isAttackingDirection;
    protected int attackAnimationCounter = 0;

    //******************************************************************************************************************
    //* setters and getters
    //******************************************************************************************************************
    /**
     * Returns the StatBlock of this character object
     * @return the StatBlock to return
     */
    public StatBlock getStatBlock() { return this._statBlock; }
    /**
     * Return the HealthBarOutline of this character object
     * @return the HealthBarOutline to return
     */
    public HealthBarOutline get_healthBarOutline() { return this._healthBarOutline; }
    /**
     * Return the HealthBarInside of this character object
     * @return the HealthBarInside to return
     */
    public HealthBarInside get_healthBarInside() { return this._healthBarInside; }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Reduces the hp value of this character by specified amount
     * @param hp value to reduce hp by
     */
    public void takeDamage(int hp) {
        _statBlock.set_hp(_statBlock.get_hp() - hp);
        _healthBarInside.changeHealthBar(((float) _statBlock.get_hp()), ((float)_statBlock.get_maxHp()));
    }

    /**
     * Increases the hp value of this character by specified amount
     * @param hp value to increase hp by
     */
    public void addHealth(int hp) {
        _statBlock.set_hp(_statBlock.get_hp() + hp);
        _healthBarInside.changeHealthBar(((float) _statBlock.get_hp()), ((float)_statBlock.get_maxHp()));
    }

    /**
     * Increases the max hp value of this character by specified amount
     * @param maxHp value to increase max hp by
     */
    public void addMaxHealth(int maxHp) { _statBlock.set_MaxHp(_statBlock.get_maxHp() + maxHp); }

    /**
     * Increases the attack value of this character by specified amount
     * @param atk value to increase attack by
     */
    public void addAttack(int atk) { _statBlock.set_Atk(_statBlock.get_atk() + atk); }

    /**
     * Calling Character attacks the parameter Character, reducing the parameter Character's health. Returns true if the
     * parameter Character's health was reduced to 0 or below, false if it hasn't
     * @param defender Character whose health is being reduced
     * @return true if defender's health is reduced to 0 or below, false if not
     */
    public boolean attackCharacter(Character defender) {
        defender.takeDamage(this._statBlock.get_atk());
        System.out.println(this.getClass().getName() + " is attacking a " + defender.getClass().getName() + "; " +
                           "Defending character now at " + defender.getStatBlock().get_hp() + " hp");

        this.isAttacking = true;
        Vector3 attackerPos = this.transform.position;
        Vector3 defenderPos = defender.transform.position;
        if (defenderPos.x == attackerPos.x && defenderPos.y > attackerPos.y) {
            isAttackingDirection = "up";
        }
        else if (defenderPos.x == attackerPos.x && defenderPos.y < attackerPos.y) {
            isAttackingDirection = "down";
        }
        else if (defenderPos.x < attackerPos.x && defenderPos.y == attackerPos.y) {
            isAttackingDirection = "left";
        }
        else if (defenderPos.x > attackerPos.x && defenderPos.y == attackerPos.y) {
            isAttackingDirection = "right";
        }
        else {
            System.out.println("Illegal character attacking direction, check attackCharacter() in Character");
        }

        return defender.getStatBlock().get_hp() <= 0;
    }

    /**
     * If a Character is attacking another Character, moves the attacking Character's sprite forwards and backwards in
     * the direction of the defending Character
     */
    public void attackAnimation() {
        float animationScale = (float)0.04;

        if (characterSprite != null) {
            if (isAttackingDirection.equals("up")) {
                if (attackAnimationCounter >= 0 && attackAnimationCounter < 6) {
                    characterSprite.shiftSprite('y', animationScale);
                }
                else {
                    characterSprite.shiftSprite('y', -animationScale);
                }
                attackAnimationCounter++;
            }
            else if (isAttackingDirection.equals("down")) {
                if (attackAnimationCounter >= 0 && attackAnimationCounter < 6) {
                    characterSprite.shiftSprite('y', -animationScale);
                }
                else {
                    characterSprite.shiftSprite('y', animationScale);
                }
                attackAnimationCounter++;
            }
            else if (isAttackingDirection.equals("left")) {
                if (attackAnimationCounter >= 0 && attackAnimationCounter < 6) {
                    characterSprite.shiftSprite('x', -animationScale);
                }
                else {
                    characterSprite.shiftSprite('x', animationScale);
                }
                attackAnimationCounter++;
            }
            else if (isAttackingDirection.equals("right")) {
                if (attackAnimationCounter >= 0 && attackAnimationCounter < 6) {
                    characterSprite.shiftSprite('x', animationScale);
                }
                else {
                    characterSprite.shiftSprite('x', -animationScale);
                }
                attackAnimationCounter++;
            }
            else {
                System.out.println("Illegal character attacking direction, check attackAnimation() in Character");
            }

            if (attackAnimationCounter == 12) {
                attackAnimationCounter = 0;
                isAttacking = false;
                isAttackingDirection = null;
            }
        }
        else {
            System.out.println("Cannot animation attack, character sprite null");
        }
    }

    /**
     * Used to instantiate all related sprites to this Character, like health bar, character sprite, etc.
     * Must be implemented in all children
     */
    public abstract void instantiateRelatedSprites(Scene scene);

    /**
     * Used to destroy all related sprites to this Character, like health bar, character sprite, etc.
     * Must be implemented in all children
     */
    public abstract void destroyRelatedSprites(Scene scene);
}

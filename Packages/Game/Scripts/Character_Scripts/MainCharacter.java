package org.group11.characters;

public class MainCharacter extends Character{

    public MainCharacter() {
        statBlock.setMaxHp(3);
        statBlock.setHp(3);
        statBlock.setAtk(1);
    }

    public void initialize() {
        // TODO: implement method
    }

    public void addExp(int exp) {
        statBlock.setExp(statBlock.getExp() + exp);
    }
}

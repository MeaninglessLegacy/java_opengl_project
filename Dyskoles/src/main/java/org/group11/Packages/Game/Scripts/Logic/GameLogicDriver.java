package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Engine.GameObject;
import org.group11.Packages.Engine.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.Character;
import org.group11.Packages.Game.Scripts.Character_Scripts.Enemy;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.group11.Packages.Game.Scripts.Item_Scripts.Item;

import java.util.ArrayList;

/**
 * Driver for our game, only one of these should exist so class exists as a singleton
 */
public class GameLogicDriver extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private static Level _gameLevel = null;
    private static Map _gameMap =  null;
    private static Pathfinder _pathfinder = null;
    private static ArrayList<MainCharacter> _playerCharacters = new ArrayList<>();
    private static ArrayList<Enemy> _enemyCharacters = new ArrayList<>();
    private static ArrayList<Item> _items = new ArrayList<Item>();
    private static int player1ArrayPosition = 0;

    //******************************************************************************************************************
    //* singleton constructor and fields
    //******************************************************************************************************************
    private static GameLogicDriver theGameLogicDriver = null;

    private GameLogicDriver() {}

    /**
     * Checks if an instance of GameLogicDriver exists yet. If it does, returns that instance. If not, creates and
     * returns a new instance
     * @return the GameLogicDriver object if it exists already, new GameLogicDriver object if it doesn't yet
     */
    public static GameLogicDriver getInstance() {
        if (theGameLogicDriver == null) {
            theGameLogicDriver = new GameLogicDriver();
        }
        return theGameLogicDriver;
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Change _gameLevel to specified level
     * @param newLevel the level to set as
     */
    private static void set_gameLevel(Level newLevel){
        _gameLevel = newLevel;
    }

    /**
     * Iterates through _playerCharacters and moves each character according to the inputted button, then iterates
     * through _enemyCharacters moves them if needed
     */
    private static void MoveCharacters(int button) {
        for (MainCharacter c : _playerCharacters) {
            if (button == 'w') {

            } else if (button == 'a') {

            } else if (button == 's') {

            } else if (button == 'd') {

            }
        }
        for (Enemy e : _enemyCharacters) {
            if (e.canEnemyMove()) {
                String moveTowards = e.get_moveTowards();
                if (moveTowards.equals("awayFromPlayer")) {
                    // TODO: figure out how to get a random point in opposite direction of player
                }
                else { // Default option, Enemy moves towards player
                    Vector3 nextMove = Pathfinder.FindPath(_gameMap, e.get_position(), _playerCharacters.get(player1ArrayPosition).get_position());
                    // TODO: check the tile to move into to see if anything needs to happen
                    e.set_position(nextMove);
                }
            }
        }
    }

    /**
     * Given 2 Characters, the second Character will have their health reduced according to the first Character's
     * attack stat
     * @param attacker the character moving into the other character and attacking
     * @param defender the character being attacked and losing health
     */
    private static void characterCombat(Character attacker, Character defender) {
        defender.takeDamage(attacker.getStatBlock().get_atk());
    }

    /**
     * Run the game
     */
    private static void logicLoop(){
        return;
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void update() {
        super.update();
    }

    @Override
    public void start() {
        _pathfinder = new Pathfinder();
        super.start();
    }

    @Override
    public void onButtonDown(int key) {
        super.onButtonDown(key);
    }
}

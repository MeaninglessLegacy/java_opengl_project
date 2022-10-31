package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Engine.GameObject;
import org.group11.Packages.Engine.Scene;
import org.group11.Packages.Engine.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.*;
import org.group11.Packages.Game.Scripts.Character_Scripts.Character;
import org.group11.Packages.Game.Scripts.Item_Scripts.Item;
import org.group11.Packages.Game.Scripts.Item_Scripts.SpikeTrap;

import java.util.ArrayList;

import static org.group11.Packages.Game.Scripts.Tile_Scripts.Tile.tileTypes.floor;

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
    private static ArrayList<MainCharacter> _playerCharacters = new ArrayList<MainCharacter>();
    private static ArrayList<Enemy> _enemyCharacters = new ArrayList<Enemy>();
    private static ArrayList<Item> _items = new ArrayList<Item>();
    private static int player1ArrayPosition = 0;

    /**
     * Change _gameLevel to specified level
     * @param newLevel the level to set as
     */
    public static void set_gameLevel(Level newLevel){ _gameLevel = newLevel; }

    /**
     * Adds a MainCharacter to the _playerCharacters arraylist, thus adding them into the game
     * @param MC the MainCharacter to add the game
     */
    public static void addMainCharacter (MainCharacter MC) { _playerCharacters.add(MC); }

    /**
     * Adds an Item to the _items arraylist, thus adding it into the game
     * @param item the Item to add the game
     */
    public static void addItem (Item item) { _items.add(item); }

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
     * If the GameLogicDriver has a Level, sets all the variables in the GameLogicDriver according to _gameLevel
     */
    private static void loadNewLevel() {
        if (_gameLevel != null) {
            MapGenerator mapGen = _gameLevel.get_mapGenerator();
            _gameMap = mapGen.generateMap();
            _enemyCharacters = _gameLevel.get_enemies();
            // TODO: get player characters and items?
        }
        else {
            System.out.println("Could not load level as GameLogicDriver has no level");
        }

    }

    /**
     * Iterates through _playerCharacters and moves each MainCharacter according to the inputted button. If a
     * MainCharacter encounters another Character or Item, deals with them appropriately.
     */
    private static void moveMainCharacters(int key) {
        for (MainCharacter c : _playerCharacters) {
            // Gets the position of where this MainCharacter is moving to next
            float playerX = c.get_position().x;
            float playerY = c.get_position().y;
            float playerZ = c.get_position().z;
            Vector3 nextMove = null;
            if (key == 'w') {
                nextMove = new Vector3(playerX, playerY + 1, playerZ);
            } else if (key == 'a') {
                nextMove = new Vector3(playerX - 1, playerY, playerZ);
            } else if (key == 's') {
                nextMove = new Vector3(playerX, playerY - 1, playerZ);
            } else if (key == 'd') {
                nextMove = new Vector3(playerX + 1, playerY, playerZ);
            }

            // Checks the type of tile the MainCharacter is attempting to moving in to
            if (_gameMap.getTile(nextMove).getTileType() == floor) {

                // Checks if there's any Character in the next tile and attempts to move into it
                Character characterInNextSpace = checkForCharacter(nextMove);
                if (characterInNextSpace == null) {
                    c.set_position(nextMove);
                /* MainCharacter attacks enemy and appropriate outcome is determined, MainCharacter then moves into the
                   tile if the enemy died */
                } else if (characterInNextSpace instanceof Enemy) {
                    boolean enemyDied = characterCombat(c, characterInNextSpace);
                    if (enemyDied) {
                        if (characterInNextSpace instanceof Boss) {
                            c.addExp(((Boss) characterInNextSpace).expGiven);
                        } else if (characterInNextSpace instanceof Minion) {
                            c.addExp(((Minion) characterInNextSpace).expGiven);
                        } else if (characterInNextSpace instanceof Runner) {
                            c.addExp(((Runner) characterInNextSpace).expGiven);
                            c.addMaxHealth(((Runner) characterInNextSpace).maxHpGiven);
                            c.addAttack(((Runner) characterInNextSpace).atkGiven);
                        }
                        _enemyCharacters.remove(characterInNextSpace);
                        Scene.Destroy(characterInNextSpace);
                        c.set_position(nextMove);
                    }
                } // If the space MainCharacter attempts to move in to isn't free or has an Enemy, nothing happens
            } // If the tileType the MainCharacter is attempting to move in to isn't of type 'floor', nothing happens

            // Checks if there's any items on the tile that the MainCharacter is now on and activates it if there is
            Item itemOnTile = checkForItem(c.get_position());
            if (itemOnTile != null) {
                if (itemOnTile.activate(c)) {
                    _items.remove(itemOnTile);
                    if (itemOnTile instanceof SpikeTrap) {
                        Scene.Destroy(itemOnTile);
                    }
                }
                if (c.getStatBlock().get_hp() <= 0) {
                    endGame(false);
                }
            } // If there is no item on the current tile, nothing happens
        }
    }

    /**
     * Iterates through _enemyCharacters and moves each Enemy if needed, deals with encounters with other Characters
     * appropriately
     */
    private static void moveEnemies() {
        for (Enemy e : _enemyCharacters) {
            if (e.canEnemyMove()) {
                String moveTowards = e.get_moveTowards();
                // Determining where the Enemy is moving towards
                if (moveTowards.equals("awayFromPlayer")) {
                    // TODO: figure out how to get a random point in opposite direction of player
                    Vector3 farAwayPosition = new Vector3(400,400,0);
                    Vector3 nextMove = _pathfinder.FindPath(_gameMap, e.get_position(), farAwayPosition);
                    Character characterInNextSpace = checkForCharacter(nextMove);
                    if (characterInNextSpace == null) {
                        e.set_position(nextMove);
                    } else {
                        e.set_ticksBeforeNextMove(1);
                    }
                }
                else { // Default option; Enemy moves towards player
                    Vector3 nextMove = _pathfinder.FindPath(_gameMap, e.get_position(), _playerCharacters.get(player1ArrayPosition).get_position());
                    Character characterInNextSpace = checkForCharacter(nextMove);
                    if (characterInNextSpace == null) {
                        e.set_position(nextMove);
                    }
                    else if (characterInNextSpace instanceof Enemy) {
                        e.set_ticksBeforeNextMove(1);
                    }
                    else if (characterInNextSpace instanceof MainCharacter) {
                        boolean MCDied = characterCombat(e, characterInNextSpace);
                        if (MCDied) {
                            endGame(false);
                        }
                    }
                }
            }
        }
    }

    /**
     * Given 2 Characters, the second Character will have their health reduced according to the first Character's
     * attack stat
     * @param attacker the character moving into the other character and attacking
     * @param defender the character being attacked and losing health
     * @return true if the defender Character's health is reduced to 0 or below, false if not
     */
    private static boolean characterCombat(Character attacker, Character defender) {
        defender.takeDamage(attacker.getStatBlock().get_atk());
        if (defender.getStatBlock().get_hp() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Iterates through both _playerCharacters and _enemyCharacters and tries to find if any character is in the given
     * Vector3 pos
     * @return Character object if there is a character in the given position, or null if there is none
     */
    private static Character checkForCharacter(Vector3 pos) {
        for (MainCharacter c : _playerCharacters) {
            if (c.get_position() == pos) {
                return c;
            }
        }
        for (Enemy e : _enemyCharacters) {
            if (e.get_position() == pos) {
                return e;
            }
        }
        return null;
    }

    /**
     * Iterates through _items and sees if there's any items on the given Vector3 pos
     * @return Item object if there is one on the given position, or null if there isn't
     */
    private static Item checkForItem(Vector3 pos) {
        for (Item i : _items) {
            if (i.get_position() == pos) {
                return i;
            }
        }
        return null;
    }

    /**
     * Ends the current game and the player either wins or loses, depending on the parameter
     * @param won true if the player won (has reached the exit with a key), false if not (player died)
     */
    public static void endGame(boolean won) {
        // TODO: end the game?
    }

    /**
     * Run the game
     */
    private static void logicLoop(int key) {
        return;
    }

    /**
     * Deletes all objects and variables in GameLogicDriver and resets everything to it's initial state
     */
    private static void clearEverything() {
        for (MainCharacter c : _playerCharacters) {
            Scene.Destroy(c);
        }
        _playerCharacters = new ArrayList<MainCharacter>();
        for (Enemy e : _enemyCharacters) {
            Scene.Destroy(e);
        }
        _enemyCharacters = new ArrayList<Enemy>();
        for (Item i : _items) {
            Scene.Destroy(i);
        }
        _items = new ArrayList<Item>();
        _gameMap.clearMap();
        _gameMap = null;
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
        MainCharacter player1 = new MainCharacter();
        Scene.Instantiate(player1);
        addMainCharacter(player1);
        _pathfinder = new Pathfinder();
    }

    @Override
    public void onButtonDown(int key) {
        moveMainCharacters(key);
        moveEnemies();
    }
}

package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Engine.GameObject;
import org.group11.Packages.Engine.Vector3;
import org.group11.Packages.Game.Scripts.Character_Scripts.*;
import org.group11.Packages.Game.Scripts.Character_Scripts.Character;
import org.group11.Packages.Game.Scripts.Item_Scripts.Exit;
import org.group11.Packages.Game.Scripts.Item_Scripts.Item;
import org.group11.Packages.Game.Scripts.Item_Scripts.Key;
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
    private static void set_gameLevel(Level newLevel){ _gameLevel = newLevel; }

    /**
     * Iterates through _playerCharacters and moves each MainCharacter according to the inputted button. If a
     * MainCharacter encounters another Character or Item, deals with them appropriately.
     * Then iterates through _enemyCharacters and moves them if needed and deals with encounters with other Characters
     * appropriately
     */
    private static void moveCharacters(int button) {
        for (MainCharacter c : _playerCharacters) {
            // Gets the position of where this MainCharacter is moving to next
            float playerX = c.get_position().x;
            float playerY = c.get_position().y;
            float playerZ = c.get_position().z;
            Vector3 nextMove = null;
            if (button == 'w') {
                nextMove = new Vector3(playerX, playerY + 1, playerZ);
            } else if (button == 'a') {
                nextMove = new Vector3(playerX - 1, playerY, playerZ);
            } else if (button == 's') {
                nextMove = new Vector3(playerX, playerY - 1, playerZ);
            } else if (button == 'd') {
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
                        c.set_position(nextMove);
                    }
                } // If the space MainCharacter attempts to move in to isn't free or has an Enemy, nothing happens
            } // If the tileType the MainCharacter is attempting to move in to isn't of type 'floor', nothing happens

            // Checks if there's any items on the tile that the MainCharacter is now on
            Item itemOnTile = checkForItem(nextMove);
            if (itemOnTile instanceof Key) {
                c.backpack.addItem(itemOnTile);
                _items.remove(itemOnTile);
            } else if (itemOnTile instanceof SpikeTrap) {
                c.takeDamage(((SpikeTrap) itemOnTile).get_spikeTrapDamage());
            } else if (itemOnTile instanceof Exit) {
                // TODO: figure out how to check if there's a key in c.backpack and subsequently end the game
            } // If there is no item on the current tile, nothing happens
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

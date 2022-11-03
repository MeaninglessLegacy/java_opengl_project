package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Camera;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Cameras.FollowingCamera;
import org.group11.Packages.Game.Scripts.Character_Scripts.*;
import org.group11.Packages.Game.Scripts.Character_Scripts.Character;
import org.group11.Packages.Game.Scripts.Item_Scripts.Item;
import org.group11.Packages.Game.Scripts.Item_Scripts.Key;
import org.group11.Packages.Game.Scripts.Levels.TestRoom;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;

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

    private static boolean _gameStarted = false;

    private static Scene scene;

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

    /**
     * Gets whether the game is currently running or not
     * @return true if the game is still running, false if not
     */
    public static boolean getGameState() { return _gameStarted; }

    //******************************************************************************************************************
    //* singleton constructor and methods
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
            _items = _gameLevel.get_items();
            for (Enemy e : _enemyCharacters) {
                e.instantiateRelatedSprites(scene);
            }
            for (Item i : _items) {
                scene.Instantiate(i);
            }
        }
        else {
            System.out.println("Could not load level as GameLogicDriver has no level");
        }
    }

    /**
     * Method is called by a MainCharacter to see if they can move to a specific Vector3 point. Checks the type of tile
     * at that point, and checks to see if there's any characters. If there's an Enemy, the calling MainCharacter
     * attacks the Enemy. If the tile is all clear to move in to, returns true. If not, returns false.
     * @param MC the MainCharacter asking if they can move
     * @param nextMove the position to which the MainCharacter is asking if they can move to
     * @return true if the calling MainCharacter can now move to the nextMove position, false if they can't
     */
    public static boolean MCCheckMove(MainCharacter MC, Vector3 nextMove) {
        Tile tile = _gameMap.getTile(nextMove);
        if (tile != null) {
            if (tile.getTileType() == floor) {
                Character characterInNextSpace = checkForCharacter(nextMove);
                if (characterInNextSpace == null) {
                    return true;
                }
                else if (characterInNextSpace instanceof Enemy) {
                    boolean enemyDied = MC.attackCharacter(characterInNextSpace);
                    if (enemyDied) {
                        ((Enemy) characterInNextSpace).giveRewards(MC);
                        _enemyCharacters.remove(characterInNextSpace);
                        characterInNextSpace.destroyRelatedSprites(scene);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Method is called by a MainCharacter to see if they are on any items. If they are, activates them
     */
    public static void MCCheckItem(MainCharacter MC, Vector3 pos) {
        Item itemOnTile = checkForItem(MC.transform.position);
        if (itemOnTile != null) {
            System.out.println("Tile player is currently on has an item");
            if (itemOnTile.activate(MC)) {
                _items.remove(itemOnTile);
                scene.Destroy(itemOnTile);
            }
            if (MC.getStatBlock().get_hp() <= 0) {
                endGame(false);
            }
        }
    }

    /**
     * Method is called by a MainCharacter after they have moved. Runs other logic that needs to be run after
     * MainCharacter movement
     */
    public static void afterMCMoveLogic(MainCharacter MC) {
        enableKeys();
        enemyLogic(MC);
        activateNearbyEnemies(MC);
    }

    /**
     * If all bosses are dead, makes all Keys visible, and therefore obtainable by a MainCharacter
     */
    private static void enableKeys() {
        if (checkForAllDeadBoss()) {
            for (Item i : _items) {
                if (i instanceof Key) {
                    ((Key) i).setKeyVisibility(true);
                }
            }
        }
    }

    /**
     * If an Enemy is within a certain range of a MainCharacter, activates the Enemy, and they will begin to pursue the
     * MainCharacter if not already activated
     */
    private static void activateNearbyEnemies(MainCharacter MC) {
        int squareActivateRadius = 3;
        float MCx = MC.transform.position.x;
        float MCy = MC.transform.position.y;
        for (Enemy e : _enemyCharacters) {
            if (!e.get_enemyActiveState()) {
                float ex = e.transform.position.x;
                float ey = e.transform.position.y;

                if (ex <= MCx + squareActivateRadius && ex >= MCx - squareActivateRadius &&
                    ey <= MCy + squareActivateRadius && ey >= MCy - squareActivateRadius) {
                        e.set_enemyActiveState(true);
                        e.set_moveCountdownNumber(e.get_ticksBeforeNextMove());
                }
            }
        }
    }

    /**
     * Iterates through each Enemy in the _enemyCharacters list, and only if the game is currently still running, calls
     * the Enemy's canEnemyMove() method to see if they can move
     */
    private static void enemyLogic(MainCharacter MC) {
        for (Enemy e : _enemyCharacters) {
            if (getGameState()) {
                e.canEnemyMove(_pathfinder, _gameMap, MC);
            }
        }
    }

    /**
     * Method is called by an Enemy to see if they can move to a specific Vector3 point. Checks to see if there's any
     * characters. If there's a MainCharacter, the calling Enemy attacks the MainCharacter. If the tile is all clear to
     * move in to, returns true. If there's something obstructing the Enemy, returns false
     */
    public static Character enemyCheckMove(Enemy e, Vector3 nextMove) {
        Character characterInNextSpace = checkForCharacter(nextMove);
        if (characterInNextSpace == null) {
            return null;
        }
        else if (characterInNextSpace instanceof MainCharacter) {
            boolean MCDied = e.attackCharacter(characterInNextSpace);
            if (MCDied) {
                endGame(false);
            }
            return characterInNextSpace;
        }
        else if (characterInNextSpace instanceof Enemy) {
            return characterInNextSpace;
        }
        else {
            System.out.println("Illegal Enemy movement, check GameLogicDriver enemyCheckMove()");
            return null;
        }
    }

    /**
     * Iterates through both _playerCharacters and _enemyCharacters and tries to find if any character is in the given
     * Vector3 pos
     * @return Character object if there is a character in the given position, or null if there is none
     */
    public static Character checkForCharacter(Vector3 pos) {
        for (MainCharacter c : _playerCharacters) {
            if (c.transform.position.x == pos.x && c.transform.position.y == pos.y) {
                return c;
            }
        }
        for (Enemy e : _enemyCharacters) {
            if (e.transform.position.x == pos.x && e.transform.position.y == pos.y) {
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
            if (i.transform.position.x == pos.x && i.transform.position.y == pos.y) {
                return i;
            }
        }
        return null;
    }

    /**
     * Iterates through _enemyCharacters to see if all Boss enemies are dead
     * @return true if there are no more Boss enemies, false if there is at least one more
     */
    private static boolean checkForAllDeadBoss() {
        for (Enemy e : _enemyCharacters) {
            if (e instanceof Boss) {
                return false;
            }
        }
        return true;
    }

    /**
     * Ends the current game and the player either wins or loses, depending on the parameter
     * @param won true if the player won (has reached the exit with a key), false if not (player died)
     */
    public static void endGame(boolean won) {
        System.out.println("The game has ended");
        clearEverything();
        _gameStarted = false;
    }

    /**
     * Deletes all objects and variables in GameLogicDriver and resets everything to it's initial state
     */
    private static void clearEverything() {
        for (MainCharacter c : _playerCharacters) {
            c.destroyRelatedSprites(scene);
        }
        _playerCharacters = new ArrayList<MainCharacter>();
        for (Enemy e : _enemyCharacters) {
            e.destroyRelatedSprites(scene);
        }
        _enemyCharacters = new ArrayList<Enemy>();
        for (Item i : _items) {
            scene.Destroy(i);
        }
        _playerCharacters = new ArrayList<MainCharacter>();
        _enemyCharacters = new ArrayList<Enemy>();
        _items = new ArrayList<Item>();
        _gameMap.clearMap();
        _gameMap = null;
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void update() {
        if(!_gameStarted){
            _gameStarted = true;

            // Gets and loads a level
            Level newLevel = new TestRoom();
            set_gameLevel(newLevel);
            loadNewLevel();

            // Creates the player character
            MainCharacter mc = new MainCharacter();
            addMainCharacter(mc);
            mc.instantiateRelatedSprites(scene);

            // Creates the camera that will follow the player character
            Camera followingCamera = new FollowingCamera(mc);
            scene.Instantiate(followingCamera);
            scene.set_mainCamera(followingCamera);
        }
        super.update();
    }

    @Override
    public void start() {
        scene = Scene.get_scene();
        _pathfinder = new Pathfinder();
    }

    @Override
    public void onKeyDown(int key) { super.onKeyDown(key); }
}

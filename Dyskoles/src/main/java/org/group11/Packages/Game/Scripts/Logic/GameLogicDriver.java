package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Camera;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Cameras.FollowingCamera;
import org.group11.Packages.Game.Scripts.Cameras.MenuCamera;
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
    protected static Scene scene;

    protected static ArrayList<Level> _gameLevelList = new ArrayList<>();
    protected static ArrayList<Level> _defaultGameLevelList = new ArrayList<>();
    protected static Map _gameMap =  null;
    protected static Pathfinder _pathfinder = null;
    protected static ArrayList<MainCharacter> _playerCharacters = new ArrayList<>();
    protected static ArrayList<Enemy> _enemyCharacters = new ArrayList<>();
    protected static ArrayList<Item> _items = new ArrayList<>();

    protected static boolean _gameStarted = false;

    // Used to switch between different Levels
    protected static int _gameStage = 1;

    // Cameras used by the engine
    private static Camera _followingCamera;
    private static Camera _menuCamera;

    // Used to display the menu
    protected static MenuScreen _menu;

    private static final int _player1ArrayPosition = 0;

    //******************************************************************************************************************
    //* getters and setters
    //******************************************************************************************************************
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
     * Sets the Level at the specified stage index (which is a 1-based index) in the GameLogicDriver's _gameLevelList
     * arraylist (0 based index). Given stage index must be: 1 <= stage index <= number of Levels in _gameLevelList + 1.
     * If a Level already exists at the specified stage index, then overwrites the current Level with the new Level.
     * @param newLevel the Level to set at the specified 1-based index
     * @param stage the 1-based index of where to set the given Level
     * @return true if the Level was successfully set at the specified 1-based index, false if the given index was out
     * of range
     */
    public static boolean set_gameLevelAtStage(Level newLevel, int stage) {
        if (stage - 1 > _gameLevelList.size() || stage - 1 < 0) {
            return false;
        }
        else if (stage - 1 == _gameLevelList.size()) {
            _gameLevelList.add(newLevel);
        }
        else {
            _gameLevelList.set(stage - 1, newLevel);
        }
        return true;
    }

    /**
     * Returns the Level at the specified stage index (1-based index) from the GameLogicDriver's _gameLevelList
     * arraylist
     * @param stage the 1-based index of where to get the Level
     * @return the Level at the specified 1-based index
     */
    public static Level get_gameLevelAtStage(int stage) {
        if (stage > _gameLevelList.size() || stage - 1 < 0) {
            return null;
        }
        return _gameLevelList.get(stage - 1);
    }

    /**
     * Returns the Level at the specified stage index (1-based index) from the GameLogicDriver's _defaultGameLevelList
     * arraylist
     * @param stage the 1-based index of where to get the Level
     * @return the Level at the specified 1-based index
     */
    private static Level get_defaultGameLevelAtStage(int stage) {
        if (stage > _defaultGameLevelList.size() || stage - 1 < 0) {
            return null;
        }
        return _defaultGameLevelList.get(stage - 1);
    }

    /**
     * If levels have been set in _gameLevelList, then GameLogicDriver loads the appropriate Level from that list by
     * getting that Level's list of MainCharacters, Enemies, Items, and it's tile map. Otherwise loads the appropriate
     * level from _defaultGameLevelList
     */
    protected static void loadNewLevel() {
        Level curLevel;
        if (_gameLevelList.size() > 0) {
            curLevel = get_gameLevelAtStage(_gameStage);
        }
        else {
            curLevel = get_defaultGameLevelAtStage(_gameStage);
        }

        if (curLevel != null) {
            MapGenerator mapGen = curLevel.get_mapGenerator();
            _gameMap = mapGen.generateMap();
            curLevel.initializeLevel(_gameMap);
            _playerCharacters = curLevel.get_players();
            _enemyCharacters = curLevel.get_enemies();
            _items = curLevel.get_items();
            for (MainCharacter c : _playerCharacters) {
                scene.Instantiate(c);
            }
            for (Enemy e : _enemyCharacters) {
                scene.Instantiate(e);
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
     * Is called after exiting a menu. Closes the menu, resets all the levels then loads the current level and sets the
     * main camera to the camera that follows the player
     */
    public static void startNewLevel() {
        // Gets rid of the menu
        scene.Destroy(_menu);

        // Sets/Resets all the default levels
        Level newLevel2 = new TestRoom();
        _defaultGameLevelList.add(newLevel2);

        /* If the _gameStage is greater than the number of Levels available, resets _gameStage to the stage of
         the last Level */
        if (_gameStage > _gameLevelList.size()) {
            _gameStage--;
        }

        // Loads the appropriate level
        loadNewLevel();

        // Creates the camera that will follow the player character
        if(!_playerCharacters.isEmpty()){
            MainCharacter mc = _playerCharacters.get(_player1ArrayPosition);
            _followingCamera = new FollowingCamera(mc);
            scene.Instantiate(_followingCamera);
            scene.set_mainCamera(_followingCamera);
            _followingCamera.transform.setPosition(mc.transform.position);
            _followingCamera.transform.position.z = -5;
        }

        _gameStarted = true;
    }


    /**
     * Removes the given Enemy from the game
     * @param enemy the Enemy to remove
     */
    public static void removeEnemy(Enemy enemy) {
        _enemyCharacters.remove(enemy);
        scene.Destroy(enemy);
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
                        removeEnemy((Enemy)characterInNextSpace);
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
    public static void MCCheckItem(MainCharacter MC) {
        Item itemOnTile = checkForItem(MC.transform.position);
        if (itemOnTile != null) {
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
    protected static void enableKeys() {
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
    protected static void activateNearbyEnemies(MainCharacter MC) {
        int squareActivateRadius = 3;
        double MCx = MC.transform.position.x;
        double MCy = MC.transform.position.y;
        for (Enemy e : _enemyCharacters) {
            if (!e.get_enemyActiveState()) {
                double ex = e.transform.position.x;
                double ey = e.transform.position.y;

                if (ex <= MCx + squareActivateRadius && ex >= MCx - squareActivateRadius &&
                    ey <= MCy + squareActivateRadius && ey >= MCy - squareActivateRadius) {
                        System.out.println("activating enemy");
                        e.set_enemyActiveState(true);
                        e.set_moveCountdownNumber(e.get_ticksBeforeNextMove());
                }
            }
        }
    }

    /**
     * <p>Iterates through each Enemy in the _enemyCharacters list, and only if the game is currently still running, calls
     * the Enemy's canEnemyMove() method to see if they can move.</p>
     * <p>If the enemy is a Runner, calls decrementTicksUntilVanish to see if they need to disappear. If they do, then
     * deletes that Runner</p>
     */
    protected static void enemyLogic(MainCharacter MC) {
        ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
        for (Enemy e : _enemyCharacters) {
            if (getGameState()) {
                e.canEnemyMove(_pathfinder, _gameMap, MC);
                if (e instanceof Runner) {
                    if (((Runner) e).decrementTicksUntilVanish()) {
                        enemiesToRemove.add(e);
                        scene.Destroy(e);
                    }
                }
            }
        }
        _enemyCharacters.removeAll(enemiesToRemove);
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
    protected static Item checkForItem(Vector3 pos) {
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
    protected static boolean checkForAllDeadBoss() {
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
        if (won) {
            _gameStage++;
        }
        clearEverything();
        _gameStarted = false;

        scene.Instantiate(_menuCamera);
        scene.set_mainCamera(_menuCamera);

        _menu.createMenu(won, _gameStage, _gameLevelList.size());
        scene.Instantiate(_menu);
    }

    /**
     * Deletes all objects and variables in GameLogicDriver and resets everything to it's initial state
     */
    protected static void clearEverything() {
        for (MainCharacter c : _playerCharacters) {
            scene.Destroy(c);
        }
        _playerCharacters = new ArrayList<>();
        for (Enemy e : _enemyCharacters) {
            scene.Destroy(e);
        }
        _enemyCharacters = new ArrayList<>();
        for (Item i : _items) {
            scene.Destroy(i);
        }
        _items = new ArrayList<>();

        if (_gameMap != null) {
            _gameMap.clearMap();
        }
        _gameMap = null;

        if (_followingCamera != null) {
            scene.Destroy(_followingCamera);
        }

        _gameLevelList = new ArrayList<>();
        _defaultGameLevelList = new ArrayList<>();
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
        scene = Scene.get_scene();
        _pathfinder = new Pathfinder();

        _menuCamera = new MenuCamera();
        scene.Instantiate(_menuCamera);
        scene.set_mainCamera(_menuCamera);

        _menu = new MenuScreen();
        scene.Instantiate(_menu);
    }

    @Override
    public void onKeyDown(int key) { super.onKeyDown(key); }
}

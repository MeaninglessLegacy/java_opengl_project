package org.group11.Packages.Game.Scripts.Logic;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Character_Scripts.Boss;
import org.group11.Packages.Game.Scripts.Character_Scripts.Character;
import org.group11.Packages.Game.Scripts.Character_Scripts.Enemy;
import org.group11.Packages.Game.Scripts.Character_Scripts.MainCharacter;
import org.group11.Packages.Game.Scripts.Item_Scripts.Item;
import org.group11.Packages.Game.Scripts.Item_Scripts.Key;

import java.util.ArrayList;

public class EntityStorage {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private Scene scene;

    protected ArrayList<MainCharacter> _playerCharacters = new ArrayList<>();
    protected ArrayList<Enemy> _enemyCharacters = new ArrayList<>();
    protected ArrayList<Item> _items = new ArrayList<>();

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public EntityStorage(Level level) {
        scene = Scene.get_scene();
        _playerCharacters = level.get_players();
        _enemyCharacters = level.get_enemies();
        _items = level.get_items();

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

    //******************************************************************************************************************
    //* setters and getters
    //******************************************************************************************************************
    /**
     * Returns this EntityStorage's list of main characters
     * @return this EntityStorage's list of main characters
     */
    public ArrayList<MainCharacter> getMCList() {
        return _playerCharacters;
    }

    /**
    * Returns this EntityStorage's list of enemies
    * @return this EntityStorage's list of enemies
    */
    public ArrayList<Enemy> getEnemyList() {
        return _enemyCharacters;
    }

    /**
     * Returns this EntityStorage's list of items
     * @return this EntityStorage's list of items
     */
    public ArrayList<Item> getItemList() {
        return _items;
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Adds the given entity into the appropriate arraylist
     * @param entity the GameObject to add into the appropriate arraylist
     * @return true if the given entity was successfully added in, false if not
     */
    protected boolean addEntity(GameObject entity) {
        if (entity instanceof MainCharacter) {
            _playerCharacters.add((MainCharacter) entity);
            return true;
        }
        else if (entity instanceof Enemy) {
            _enemyCharacters.add((Enemy) entity);
            return true;
        }
        else if (entity instanceof Item) {
            _items.add((Item) entity);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Removes the given enemy from the game
     * @param enemy the enemy to remove from the game
     * @return true if the enemy was successfully removed, false if not
     */
    public boolean removeEnemy(Enemy enemy) {
        if (_enemyCharacters.remove(enemy)) {
            scene.Destroy(enemy);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Removes the given item from the game
     * @param item the item to remove from the game
     * @return true if the item was successfully removed, false if not
     */
    public boolean removeItem(Item item) {
        if (_items.remove(item)) {
            scene.Destroy(item);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Iterates through both _playerCharacters and _enemyCharacters and tries to find if any character is in the given
     * Vector3 pos
     * @param pos the Vector3 position to search for a character
     * @return Character object if there is a character in the given position, or null if there is none
     */
    public Character getCharacter(Vector3 pos) {
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
     * @param pos the Vector3 position to search for an item
     * @return Item object if there is one on the given position, or null if there isn't
     */
    public Item getItem(Vector3 pos) {
        for (Item i : _items) {
            if (i.transform.position.x == pos.x && i.transform.position.y == pos.y) {
                return i;
            }
        }
        return null;
    }

    /**
     * If all bosses are dead, makes all Keys visible, and therefore obtainable by a MainCharacter
     */
    protected void enableKeys() {
        boolean allBossDead = true;

        for (Enemy e : _enemyCharacters) {
            if (e instanceof Boss) {
                allBossDead = false;
            }
        }

        if (allBossDead) {
            for (Item i : _items) {
                if (i instanceof Key) {
                    ((Key) i).setKeyVisibility(true);
                }
            }
        }
    }

    /**
     * Deletes all entities
     */
    public void clearEntities() {
        for (MainCharacter c : _playerCharacters) {
            scene.Destroy(c);
        }
        for (Enemy e : _enemyCharacters) {
            scene.Destroy(e);
        }
        for (Item i : _items) {
            scene.Destroy(i);
        }
        _playerCharacters = new ArrayList<>();
        _enemyCharacters = new ArrayList<>();
        _items = new ArrayList<>();
    }
}

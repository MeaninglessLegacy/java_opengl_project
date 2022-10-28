package org.group11.Packages.Engine;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Scene has methods to store, remove, and render GameObjects. Only one scene can exist at a time.
 */
public class Scene {
    //******************************************************************************************************************
    //* workspace variables
    //******************************************************************************************************************
    private static ArrayList<GameObject> _workspace = new ArrayList<>(); // GameObject storage space
    // _workspace must in most cases be performed on with synchronized(_workspace) to avoid

    //******************************************************************************************************************
    //* internal methods
    //******************************************************************************************************************
    /**
     *
     */
    private static void updateGameObjects(){
        Iterator<GameObject> workspaceIterator = _workspace.iterator(); // call update() on GameObjects
        while(workspaceIterator.hasNext()){
            workspaceIterator.next().update();
        }
    }

    //******************************************************************************************************************
    //* public methods
    //******************************************************************************************************************
    /**
     *
     * @param obj
     * @return
     */
    public static GameObject Instantiate(GameObject obj){
        return null;
    }

    /**
     *
     * @param obj
     * @return
     */
    public static boolean Destroy(GameObject obj){ return false; }

    /**
     *
     */
    public static void update(){
        synchronized (_workspace){
            updateGameObjects();
        }
    }

    /**
     *
     */
    public static void render(){}
}

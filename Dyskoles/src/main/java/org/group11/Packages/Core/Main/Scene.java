package org.group11.Packages.Core.Main;

import org.group11.Packages.Core.Renderer.Renderer;

import java.util.*;

/**
 * Scene is designed as a singleton that stores GameObjects for updates and rendering. Scene only begins to update,
 * render, and process event listeners once Engine has been started.
 */
public class Scene {

    //******************************************************************************************************************
    //* workspace variables
    //******************************************************************************************************************
    private static ArrayList<GameObject> _workspace = new ArrayList<>(); // GameObject storage space
    // _workspace must be accessed with synchronized(_workspace) to avoid concurrency problems when multithreading
    // _queuedCreation & _queuedDestruction are to avoid concurrency problems when calling Instantiate() & Destroy()
    private static ArrayList<GameObject> _queuedCreation = new ArrayList<>(); // GameObjects to Instantiate on the
    // start of the next update cycle
    private static ArrayList<GameObject> _queuedDestruction = new ArrayList<>(); // GameObjects to Destroy on the start
    // of the next update cycle

    private static Renderer _renderer = Renderer.get_renderer(); // Renderer to render the _workspace
    private static Camera _mainCamera; // Camera to view the _workspace through

    // keyboard input
    private static Map<Integer, Integer> _keysState = new HashMap();

    //******************************************************************************************************************
    //* singleton constructor and methods
    //******************************************************************************************************************
    private static Scene _scene;

    private Scene() {}

    /**
     * Checks if an instance of Scene exists yet. If it does, returns that instance. If not, creates and returns a new
     * instance
     * @return the Scene object if it exists already, new Scene object if it doesn't yet
     */
    public static Scene get_scene(){
        if(Scene._scene == null){
            Scene._scene = new Scene();
        }
        return Scene._scene;
    }

    //******************************************************************************************************************
    //* internal methods
    //******************************************************************************************************************
    /**
     * <p>Update() helper function.</p>
     * Calls the update methods of all GameObjects within _workspace.
     */
    private void updateGameObjects(){
        Iterator<GameObject> workspaceIterator = _workspace.iterator(); // call update() on GameObjects
        while(workspaceIterator.hasNext()){
            workspaceIterator.next().update();
        }
    }

    /**
     * <p>Update() helper function.</p>
     * Moves GameObjects from _queuedCreation to _workspace if they don't already exist in workspace and then calling
     * each GameObject's start() method.
     */
    private void createGameObjects(){
        /*
        All objects must be created and _queuedCreation must be wiped before more Instantiate method calls. Since
        GameObject.start() is a user overridden method, calling GameObject.start() may call the Instantiate method.
        The workaround is below is to track which new GameObjects were created, clearing _queuedCreation and then
        calling the start() method of our newly created GameObjects which may fill _queuedCreation again.
         */
        Iterator<GameObject> queuedCreationIterator = _queuedCreation.iterator();
        ArrayList<GameObject> creations = new ArrayList<>();
        while(queuedCreationIterator.hasNext()){
            GameObject gameObject = queuedCreationIterator.next();
            if(!_workspace.contains(gameObject)){
                _workspace.add(gameObject);
                creations.add(gameObject);
            }
        }
        _queuedCreation.clear();
        Iterator<GameObject> creationsIterator = creations.iterator();
        while(creationsIterator.hasNext()){
            creationsIterator.next().start();
        }
        creations.clear();
    }

    /**
     * <p>Update() helper function.</p>
     * Removes GameObjects from _workspace that are in _queuedDestruction.
     */
    private void destroyGameObjects(){
        Iterator<GameObject> queuedDestructionIterator = _queuedDestruction.iterator();
        while(queuedDestructionIterator.hasNext()){
            _workspace.remove(queuedDestructionIterator.next());
        }
        _queuedDestruction.clear();
    }

    /**
     * <p>Update() helper function.</p>
     * Calls the appropriate key listener methods on GameObjects based on the _keyState of the Scene.
     */
    private void callKeyListeners(){
        synchronized (_keysState){
            for(int key : _keysState.keySet()){
                if(_keysState.get(key) == 1){
                    for(GameObject gameObject : _workspace){
                        gameObject.onKeyDown(key);
                    }
                }else{
                    for(GameObject gameObject : _workspace){
                        gameObject.onKeyUp(key);
                    }
                }
            }
        }
    }

    //******************************************************************************************************************
    //* public methods
    //******************************************************************************************************************
    /**
     * Queues a GameObject to be added to the Scene on the following Scene.update() call.
     * @param obj The GameObject to add to the Scene.
     * @return Returns the GameObject if it can be added to the Scene else returns null.
     */
    public GameObject Instantiate(GameObject obj){
        if(_queuedCreation.contains(obj)) return null;
        _queuedCreation.add(obj); // queue object to add to workspace
        _renderer.add(obj); // add object to render list
        return obj;
    }

    /**
     * Queues a GameObject to be removed from the Scene on the following Scene.update() call.
     * @param obj The GameObject to remove from the Scene.
     * @return Returns Boolean false if the GameObject is already queued for destruction else returns Boolean true.
     */
    public boolean Destroy(GameObject obj){
        if(_queuedDestruction.contains(obj)) {
            return false;
        }
        _queuedDestruction.add(obj);
        _renderer.remove(obj);
        return true;
    }

    /**
     * <p><b>ENGINE USE ONLY</b></p>
     * Call this when the scene is first created to initialize the Renderer and create a default Camera.
     */
    public void run(){
        _renderer = Renderer.get_renderer();
        _renderer.init();
        _mainCamera = new Camera();
    }

    /**
     * <p><b>ENGINE USE ONLY</b></p>
     * Processes all Instantiate & Destroy calls on the previous Scene.update() call before calling the update() method
     * of all the GameObjects inside of Scene._workspace.
     */
    public void update(){
        synchronized (_workspace){
            /*
            the following must be done in a specific order to avoid concurrency problems
                1. Destroy GameObjects queued for destruction
                2. Create GameObjects queued for creation
                3. Update all remaining GameObjects
                4. Call event listeners of all GameObjects
             most importantly step 3 &4 will fail if the _workspace is modified during iteration
             */
            destroyGameObjects();
            createGameObjects();
            updateGameObjects();
            callKeyListeners();
        }
    }

    /**
     * <p><b>ENGINE USE ONLY</b></p>
     */
    public void render(){_renderer.render(_mainCamera);}

    /**
     * Returns the _mainCamera of the Scene.
     * @return The main Camera object.
     */
    public Camera get_mainCamera() {
        if(_mainCamera == null) _mainCamera = new Camera();
        return _mainCamera;
    }

    /**
     * Sets the _mainCamera of the Scene.
     * @param camera The Camera object to set as the _mainCamera.
     */
    public void set_mainCamera(Camera camera){
        if(camera != null) {
            _mainCamera = camera;
        }
    }

    //******************************************************************************************************************
    //* event listeners
    //******************************************************************************************************************
    /**
     * When called, sets the state of the given key to 1 (key is down) inside the Scene's _keyState. Scene uses these
     * key states to call the appropriate event listener of each GameObject.
     * @param key Integer value of the key.
     */
    public static void onKeyDown(int key){
        synchronized (_keysState){
            _keysState.put(key, 1);
        }
    }

    /**
     * When called, sets the state of the given key to 0 (key is up) inside the Scene's _keyState. Scene uses these
     * key states to call the appropriate event listener of each GameObject.
     * @param key Integer value of the key.
     */
    public static void onKeyUp(int key){
        synchronized (_keysState){
            _keysState.put(key, 0);
        }
    }
}

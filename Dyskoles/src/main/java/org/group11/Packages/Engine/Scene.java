package org.group11.Packages.Engine;

/**
 * Every scene contain GameObjects, the scene runs each GameObjects update() function on every logical tick and calls
 * the methods to render the scene
 */
public class Scene {
    /*
    implement logic on main thread, set updates/second
    implement rendering on background thread
     */
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    //******************************************************************************************************************
    //* internal methods
    //******************************************************************************************************************
    /**
     *
     */
    private void run(){

    }

    //******************************************************************************************************************
    //* public methods
    //******************************************************************************************************************
    /**
     *
     * @param obj
     * @return
     */
    public GameObject Instantiate(GameObject obj){
        return null;
    }

    /**
     *
     * @param obj
     * @return
     */
    public boolean Destroy(GameObject obj){
        return false;
    }

    /**
     *
     */
    public void start(){}

    /**
     * 
     */
    public void stop(){}
}

package org.group11.Packages.Core.Main;

import org.group11.Packages.Core.DataStructures.Transform;

import java.util.ArrayList;
import java.util.List;

/**
 * Engine's abstract GameObject class, GameObjects exist within the scene of the game and are operated on by the engine.
 */
abstract public class GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    //Transform of this GameObject in it's corresponding scene's world space
    public Transform transform;

    // Components of this GameObject which the engine performs additional operations on
    List<Component> components = new ArrayList<>();

    //******************************************************************************************************************
    //* engine calls
    //******************************************************************************************************************
    /**
     * Method is called by the game engine on every frame.
     */
    public void update() {
        return;
    }

    /**
     * Method is called when the GameObject is first created.
     */
    public void start() {
        return;
    }

    //******************************************************************************************************************
    //* event listeners
    //******************************************************************************************************************

    /**
     * Called when event listener records a button being pressed.
     * @param key Ascii value of the key.
     */
    public void onKeyDown(int key) {
        return;
    }

    /**
     * Called when event listener records a button being released.
     * @param key Ascii value of the key pressed.
     */
    public void onKeyUp(int key){
        return;
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Returns component of GameObject with given componentClass.
     * @param componentClass The class of the GameObject to get.
     * @return The component of the GameObject matching the class given.
     * @param <T> The class of the component.
     */
    public <T extends Component> T getComponent(Class<T> componentClass){
        for(Component c : components){
            if(componentClass.isAssignableFrom(c.getClass())){
                try{
                    return componentClass.cast(c);
                }catch (ClassCastException e){
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        }
        return null;
    }
}

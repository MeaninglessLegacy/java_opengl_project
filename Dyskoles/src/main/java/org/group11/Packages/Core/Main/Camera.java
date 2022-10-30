package org.group11.Packages.Core.Main;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.GameObject;

/**
 * Camera object that the game can be viewed through when set as the main camera of the scene.
 */
public class Camera extends GameObject {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private Vector3 viewSize = new Vector3(10,10,0);
    //******************************************************************************************************************
    //* override
    //******************************************************************************************************************
    @Override
    public void update() {
        super.update();
    }

    @Override
    public void start() {
        super.start();
    }
}

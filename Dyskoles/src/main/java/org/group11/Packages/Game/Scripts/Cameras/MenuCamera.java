package org.group11.Packages.Game.Scripts.Cameras;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Camera;

public class MenuCamera extends Camera {
    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    public MenuCamera() {
        this.transform.position = new Vector3(0,0,-8);
        this.transform.rotation = new Vector3(-2.6f,0f,0f);
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void update() { super.update(); }

    @Override
    public void start() { super.start(); }
}

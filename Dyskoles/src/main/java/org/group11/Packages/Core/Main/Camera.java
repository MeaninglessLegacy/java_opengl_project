package org.group11.Packages.Core.Main;

import org.group11.Packages.Core.DataStructures.Transform;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Util.Constants;

/**
 * Camera object that the game can be viewed through when set as the main camera of the scene.
 */
public class Camera extends GameObject {
    // camera projection settings
    private static final int ORTHOGRAPHIC = 0;

    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************
    private Vector3 viewSize = new Vector3(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT,0);

    private int cameraMode = Camera.ORTHOGRAPHIC; // individual camera's projection mode

    /**
     * Constructor for Camera, makes the orientation orthographic and sets the Camera's position in terms of z
     */
    public Camera(){
        this.cameraMode = Camera.ORTHOGRAPHIC;
        this.transform.position.z = -5;
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     *
     * @param worldPos
     * @return
     */
    public Vector3 distortPoint(Vector3 worldPos){
        Vector3 output = new Vector3();
        // translation
        output.x = worldPos.x - this.transform.position.x;
        output.y = worldPos.y - this.transform.position.y;
        output.z = worldPos.z - this.transform.position.z;
        // rotation (maybe)
        // distort XY by INV Z difference
        // larger = smaller object
        // smaller = bigger object
        if(output.z == 0){
            output.z = (float)(0.00000001);
        }
        double zDistort = (viewSize.x/2)/output.z;
        output.x *= zDistort;
        output.y *= zDistort;

        // center 0,0
        output.x += viewSize.x/2;
        output.y += viewSize.y/2;

        return output;
    }

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

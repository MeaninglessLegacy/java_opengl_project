package org.group11.Packages.Core.Main;

import org.group11.Packages.Core.DataStructures.Transform;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Util.Constants;

/**
 * Camera object that the game can be viewed through when set as the main camera of the scene.
 */
public class Camera extends GameObject {
    // used by Renderer to project points from world space to the screen.
    private Vector3 viewSize = new Vector3(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT,0);

    /**
     * Constructs a Camera object at (0,0,-5).
     */
    public Camera(){
        this.transform.position.z = -5;
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * <p><b>This function should be moved to Renderer.</b></p>
     * Performs a projection using the pin-hole camera model onto an image plane. Focal length is set to (viewSize.x/2).
     * @param worldPos Vector3 coordinate of the point to transform.
     * @return Vector Vector3 coordinate of our transformed point with z-component = distance between camera and object.
     */
    public Vector3 pinHoleModel(Vector3 worldPos){
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
        // Translation onto image plane (focal length is viewSize.x/2)
        // Greater the focal length the more (zoomed?) in it will be, just draw out a pin-hole camera. Similar triangles
        // argument
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

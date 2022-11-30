package org.group11.Packages.Core.Main;

import org.group11.Packages.Core.DataStructures.Vector2;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Util.Constants;

/**
 * Camera object that the game can be viewed through when set as the main camera of the scene.
 */
public class Camera extends GameObject {
    // used by Renderer to project points from world space to the screen.
    private Vector3 viewSize = new Vector3(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT,0);
    private Vector3 _viewingDir = new Vector3(0,0,1);

    /**
     * Constructor for Camera, makes the orientation orthographic and sets the Camera's position in terms of z
     */
    public Camera(){
        this.transform.position.z = -5;
        this.transform.rotation.x = 3.14f;
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Rotation of point (x,y,z) about axis (a,b,c) along view axis (nu,nv, nw) by theta
     * https://stackoverflow.com/questions/44792495/c-sharp-rotating-points-about-axis
     */
    private Vector3 Rotate(double x, double y, double z, double a, double b, double c, double nu, double nv, double nw, double theta) {
        Vector3 rP = new Vector3();

        rP.x = (float)((a * (nv * nv + nw * nw) - nu * (b * nv + c * nw - nu * x - nv * y - nw * z)) * (1 - Math.cos(theta)) + x * Math.cos(theta) + (-c * nv + b * nw - nw * y + nv * z) * Math.sin(theta));
        rP.y = (float)((b * (nu * nu + nw * nw) - nv * (a * nu + c * nw - nu * x - nv * y - nw * z)) * (1 - Math.cos(theta)) + y * Math.cos(theta) + (c * nu - a * nw + nw * x - nu * z) * Math.sin(theta));
        rP.z = (float)((c * (nu * nu + nv * nv) - nw * (a * nu + b * nv - nu * x - nv * y - nw * z)) * (1 - Math.cos(theta)) + z * Math.cos(theta) + (-b * nu + a * nv - nv * x + nu * y) * Math.sin(theta));

        return rP;
    }

    /**
     * <p><b>This function should be moved to Renderer.</b></p>
     * Performs a projection using the pin-hole camera model onto an image plane. Focal length is set to (viewSize.x/2).
     * @param worldPos Vector3 coordinate of the point to transform.
     * @return Vector Vector3 coordinate of our transformed point with z-component = distance between camera and object.
     */
    public Vector3 pinHoleModel(Vector3 worldPos){
        Vector3 output = new Vector3(worldPos.x, worldPos.y, worldPos.z);
        // rotation (maybe), remove y and z rotation if it's too much
        output = Rotate(output.x, output.y, output.z, this.transform.position.x, this.transform.position.y, this.transform.position.z, 1, 0, 0, transform.rotation.x);
        output = Rotate(output.x, output.y, output.z, this.transform.position.x, this.transform.position.y, this.transform.position.z, 0, 1, 0, transform.rotation.y);
        output = Rotate(output.x, output.y, output.z, this.transform.position.x, this.transform.position.y, this.transform.position.z, 0, 0, 1, transform.rotation.z);
        // translate
        output.x = this.transform.position.x - output.x;
        output.y = this.transform.position.y - output.y;
        output.z = this.transform.position.z - output.z;
        // distort XY by INV Z difference
        if(output.z <= 0){
            output.z = (float)(0.0001);
        }
        // view (focal length is viewSize.x/2)
        double zDistort = (viewSize.x/2)/output.z;
        // somehow x is flipped
        output.x *= -zDistort;
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

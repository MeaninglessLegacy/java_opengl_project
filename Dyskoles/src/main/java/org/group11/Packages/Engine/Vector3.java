package org.group11.Packages.Engine;

/**
 * Defines a 3-dimensional vector
 */
public class Vector3 {
    // Components of the 3-dimensional vector
    public float x, y ,z;

    //******************************************************************************************************************
    //* constructors
    //******************************************************************************************************************

    /**
     * Constructs the 0 vector
     */
    public Vector3() {
        x = 0;
        y = 0;
        z = 0;
    }

    /**
     * Constructs a vector with the given components
     * @param x float, x component of vector
     * @param y float, y component of vector
     * @param z float, z component of vector
     */
    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

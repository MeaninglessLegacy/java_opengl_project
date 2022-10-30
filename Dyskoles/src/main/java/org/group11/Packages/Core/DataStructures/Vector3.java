package org.group11.Packages.Core.DataStructures;

/**
 * Defines a 3-dimensional vector.
 */
public class Vector3 {
    // Components of the 3-dimensional vector
    public float x, y ,z;

    //******************************************************************************************************************
    //* constructors
    //******************************************************************************************************************

    /**
     * Constructs the 0 vector.
     */
    public Vector3() {
        x = 0;
        y = 0;
        z = 0;
    }

    /**
     * Constructs a vector with the given components.
     * @param x X component of vector.
     * @param y Y component of vector.
     * @param z Z component of vector.
     */
    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

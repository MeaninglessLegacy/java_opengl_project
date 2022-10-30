package org.group11.Packages.Core.DataStructures;

/**
 * Defines a 4-dimensional vector.
 */
public class Vector4 {
    // Components of the 4-dimensional vector
    public float x, y ,z, w;

    //******************************************************************************************************************
    //* constructors
    //******************************************************************************************************************

    /**
     * Constructs the 0 vector.
     */
    public Vector4() {
        x = 0;
        y = 0;
        z = 0;
        w = 0;
    }

    /**
     * Constructs a vector with the given components.
     * @param x X component of vector.
     * @param y Y component of vector.
     * @param z Z component of vector.
     * @param w W component of vector.
     */
    public Vector4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
}

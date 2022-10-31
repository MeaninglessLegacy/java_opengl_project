package org.group11.Packages.Core.DataStructures;

/**
 * Defines a 2-dimensional vector.
 */
public class Vector2 {
    // Components of the 2-dimensional vector
    public float x, y;

    //******************************************************************************************************************
    //* constructors
    //******************************************************************************************************************

    /**
     * Constructs the 0 vector.
     */
    public Vector2() {
        x = 0;
        y = 0;
    }

    /**
     * Constructs a vector with the given components.
     * @param x X component of vector.
     * @param y Y component of vector.
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

package org.group11.Packages.Core.DataStructures;

/**
 * Spatial information for GameObjects.
 */
public class Transform {
    //******************************************************************************************************************
    //* variables
    //******************************************************************************************************************

    // Cartesian position of the Transform
    public Vector3 position;
    // Rotation of the Transform in euler angles
    public Vector3 rotation;

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    /**
     * Default constructor for a Transform, position and rotation are default Vector3 objects.
     */
    public Transform() {
        this.position = new Vector3();
        this.rotation = new Vector3();
    }

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    /**
     * Sets the Vector3 position of the Transform.
     * @param position The Vector3 to set the transform to.
     */
    public void setPosition(Vector3 position) {
        this.position = position;
    }
}

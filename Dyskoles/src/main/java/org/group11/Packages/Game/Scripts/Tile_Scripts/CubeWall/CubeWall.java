package org.group11.Packages.Game.Scripts.Tile_Scripts.CubeWall;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;

import java.util.*;

/**
 * A cube shaped wall.
 */
public class CubeWall extends Tile {
    //******************************************************************************************************************
    //* list of faces
    //******************************************************************************************************************
    private List<CubeFace> _faces = new ArrayList<>(); // a cube has 6 faces.

    //******************************************************************************************************************
    //* constructor
    //******************************************************************************************************************
    /**
     * Create a new cube wall.
     */
    public CubeWall(){
        _tileType = tileTypes.wall;
        SetupFaces();
    }

    /**
     * Sets up the cube faces.
     */
    private void SetupFaces(){
        Random r = new Random();
        Vector3[] cubeVertices = new Vector3[]{
                new Vector3(-0.5f, 0.5f, -0.5f),
                new Vector3(0.5f, 0.5f, -0.5f),
                new Vector3(0.5f, -0.5f, -0.5f),
                new Vector3(-0.5f, -0.5f, -0.5f),
                new Vector3(-0.5f, 0.5f, 0.5f),
                new Vector3(0.5f, 0.5f, 0.5f),
                new Vector3(0.5f, -0.5f, 0.5f),
                new Vector3(-0.5f, -0.5f, 0.5f)
        };
        // each face is technically just a copy of another face so 3 faces only need to be defined and shifted
        Vector3[][] quads = new Vector3[][]{
                new Vector3[]{cubeVertices[1], cubeVertices[2],cubeVertices[3],cubeVertices[0]},
                new Vector3[]{cubeVertices[0], cubeVertices[3],cubeVertices[7],cubeVertices[4]},
                new Vector3[]{cubeVertices[4], cubeVertices[5],cubeVertices[1],cubeVertices[0]}
        };
        for(int i = 0; i < 6; i++){ // create walls
            CubeFace face = new CubeFace("./Resources/CubeWallSideGrey.png");
            // create 2 copies of each wall
            face.get_spriteRenderer().get_sprite().renderComponent.set_quadCords(quads[i%3]);
            _faces.add(face);
            // link each face transform to this GameObject
            face.transform = this.transform;
        }
        // setup each face that needs to be shifted
        _faces.get(3).get_spriteRenderer().get_sprite().transform.position.z += 1;
        _faces.get(4).get_spriteRenderer().get_sprite().transform.position.x += 1;
        _faces.get(5).get_spriteRenderer().get_sprite().transform.position.y -= 1;

        // set top face
        //_faces.get(0).get_spriteRenderer().get_sprite().set_texture("./Resources/CubeWallTopGrey.png");
    }

    //******************************************************************************************************************
    //* methods
    //******************************************************************************************************************
    /**
     * Instantiates all cubeFaces.
     */
    private void InstantiateCubeFaces(){
        Scene scene = Scene.get_scene();
        for(CubeFace face : _faces){
            scene.Instantiate(face);
        }
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void start() {
        InstantiateCubeFaces();
    }

    /**
     * Destroys each cube face.
     */
    @Override
    public void Delete() {
        Scene scene = Scene.get_scene();
        for(CubeFace face : _faces){
            scene.Destroy(face);
        }
    }

}

package org.group11.Packages.Game.Scripts.MapGenerators;

import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Logic.Map;
import org.group11.Packages.Game.Scripts.Logic.MapGenerator;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Floor;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Wall;

public class FourConnectedRooms extends MapGenerator {
    private Scene scene;

    @Override
    public Map generateMap() {
        scene = Scene.get_scene();
        if(scene == null) return null;
        Map newMap = new Map();

        // Change roomDimension to change size of each room, MUST BE MULTIPLE OF 2
        // Change sideLength to change the distance and hallway length between each room, MUST BE MULTIPLE OF 2
        // sideLength - roomDimension - 2 = length of each hallway between rooms
        int roomDimension = 12;
        int sideLength = 20;

        // Generate 2 quadrangles which connect all 4 rooms together
        Vector3 firstQuadrangleBottomLeftFloorTile = new Vector3(roomDimension/2, roomDimension/2, 0);
        Vector3 secondQuadrangleBottomLeftFloorTile = new Vector3(roomDimension/2 - 1, roomDimension/2 - 1, 0);
        newMap = generateQuadrangle(newMap, secondQuadrangleBottomLeftFloorTile, sideLength + 2);
        newMap = generateQuadrangle(newMap, firstQuadrangleBottomLeftFloorTile, sideLength);

        // Generate 4 rooms
        Vector3 firstRoomBottomLeftFloorTile = new Vector3(0, 0, 0);
        Vector3 secondRoomBottomLeftFloorTile = new Vector3(sideLength, 0, 0);
        Vector3 thirdRoomBottomLeftFloorTile = new Vector3(sideLength, sideLength, 0);
        Vector3 fourthRoomBottomLeftFloorTile = new Vector3(0, sideLength, 0);
        newMap = generateRoom(newMap, firstRoomBottomLeftFloorTile, roomDimension);
        newMap = generateRoom(newMap, secondRoomBottomLeftFloorTile, roomDimension);
        newMap = generateRoom(newMap, thirdRoomBottomLeftFloorTile, roomDimension);
        newMap = generateRoom(newMap, fourthRoomBottomLeftFloorTile, roomDimension);

        // Generates walls for the hallways which connect all 4 rooms
        newMap = generateHallwayWallsHorizontal(newMap, firstRoomBottomLeftFloorTile, roomDimension, sideLength);
        newMap = generateHallwayWallsHorizontal(newMap, fourthRoomBottomLeftFloorTile, roomDimension, sideLength);
        newMap = generateHallwayWallsVertical(newMap, firstRoomBottomLeftFloorTile, roomDimension, sideLength);
        newMap = generateHallwayWallsVertical(newMap, secondRoomBottomLeftFloorTile, roomDimension, sideLength);

        return newMap;
    }

    /**
     * Given the Vector3 position of the bottom left most Floor tile of the quadrangle, generates a
     * sideLength x sideLength sized quadrangle of Floors
     * @param newMap the map to generate tiles in
     * @param bottomLeft the Vector3 position of where the most bottom left Floor tile goes
     * @param sideLength side length of the quadrangle to be generated
     * @return the map with the room generated in
     */
    private Map generateQuadrangle(Map newMap, Vector3 bottomLeft, int sideLength) {
        // Generates the bottom line of the quadrangle
        for (int x = (int)bottomLeft.x; x < (int)bottomLeft.x + sideLength; x++) {
            Vector3 pos = new Vector3(x, bottomLeft.y, 0);
            Tile newTile = new Floor(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }
        // Generates the top line of the quadrangle
        for (int x = (int)bottomLeft.x; x < (int)bottomLeft.x + sideLength; x++) {
            Vector3 pos = new Vector3(x, bottomLeft.y + sideLength - 1, 0);
            Tile newTile = new Floor(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }
        // Generates the left side of the quadrangle
        for (int y = (int)bottomLeft.y; y < (int)bottomLeft.y + sideLength; y++) {
            Vector3 pos = new Vector3(bottomLeft.x, y, 0);
            Tile newTile = new Floor(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }
        // Generates the right side of the quadrangle
        for (int y = (int)bottomLeft.y; y < (int)bottomLeft.y + sideLength; y++) {
            Vector3 pos = new Vector3(bottomLeft.x + sideLength - 1, y, 0);
            Tile newTile = new Floor(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }

        return newMap;
    }


    /**
     * Given the Vector3 position of the bottom left most Floor tile of the room, generates a
     * roomDimension x roomDimension sized room with walls around it
     * @param newMap the map to generate tiles in
     * @param bottomLeft the Vector3 position of where the most bottom left Floor tile goes
     * @param roomDimension the dimension of the room to be generated
     * @return the map with the room generated in
     */
    private Map generateRoom(Map newMap, Vector3 bottomLeft, int roomDimension) {
        // roomDimension x roomDimension sized square of Floor tiles
        for (int x = (int)bottomLeft.x; x < bottomLeft.x + roomDimension; x++) {
            for (int y = (int)bottomLeft.y; y < bottomLeft.y + roomDimension; y++) {
                Vector3 pos = new Vector3(x, y, 0);
                Tile newTile = new Floor(pos);
                newMap.setTile(pos, newTile);
                scene.Instantiate(newTile);
            }
        }

        // Generates the top walls of the room
        for (int x = (int)bottomLeft.x - 1; x < bottomLeft.x + roomDimension + 1; x++) {
            Vector3 pos = new Vector3(x, bottomLeft.y + roomDimension, 0);
            Tile newTile = new Wall(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }
        // Generates the bottom walls of the room
        for (int x = (int)bottomLeft.x - 1; x < bottomLeft.x + roomDimension + 1; x++) {
            Vector3 pos = new Vector3(x, bottomLeft.y - 1, 0);
            Tile newTile = new Wall(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }
        // Generates the right walls of the room
        for (int y = (int)bottomLeft.y; y < bottomLeft.y + roomDimension; y++) {
            Vector3 pos = new Vector3(bottomLeft.x - 1, y, 0);
            Tile newTile = new Wall(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }
        // Generates the left walls of the room
        for (int y = (int)bottomLeft.y; y < bottomLeft.y + roomDimension; y++) {
            Vector3 pos = new Vector3(bottomLeft.x + roomDimension, y, 0);
            Tile newTile = new Wall(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }
        return newMap;
    }

    /**
     * Given the position where the room to the left was generated, and the length of the quadrangles generated,
     * calculates where to generate Wall tiles to wall in horizontal hallways
     * @param newMap the map to generate tiles in
     * @param leftRoomBottomLeft the Vector3 position of where the room to the left was generated
     * @param roomDimension the size of each room
     * @param sideLength the length of the quadrangles that were generated
     * @return the map with horizontal hallways' walls filled in
     */
    private Map generateHallwayWallsHorizontal(Map newMap, Vector3 leftRoomBottomLeft, int roomDimension, int sideLength) {
        // Generates the bottom walls of the hallway
        for (int x = (int)leftRoomBottomLeft.x + roomDimension + 1; x < (int)leftRoomBottomLeft.x + sideLength - 1; x++){
            Vector3 pos = new Vector3(x, (int)leftRoomBottomLeft.y + (roomDimension/2) - 2, 0);
            Tile newTile = new Wall(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }
        // Generates the top walls of the hallway
        for (int x = (int)leftRoomBottomLeft.x + roomDimension + 1; x < (int)leftRoomBottomLeft.x + sideLength - 1; x++){
            Vector3 pos = new Vector3(x, (int)leftRoomBottomLeft.y + (roomDimension/2) + 1, 0);
            Tile newTile = new Wall(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }
        return newMap;
    }

    /**
     * Given the position where the room below the hallway was generated, and the length of the quadrangles generated,
     * calculates where to generate Wall tiles to wall in vertical hallways
     * @param newMap the map to generate tiles in
     * @param belowRoomBottomLeft the Vector3 position of where the room above the hallway was generated
     * @param roomDimension the size of each room
     * @param sideLength the length of the quadrangles that were generated
     * @return the map with horizontal hallways' walls filled in
     */
    private Map generateHallwayWallsVertical(Map newMap, Vector3 belowRoomBottomLeft, int roomDimension, int sideLength) {
        // Generates the left walls of the hallway
        for (int y = (int)belowRoomBottomLeft.y + roomDimension + 1; y < (int)belowRoomBottomLeft.y + sideLength - 1; y++){
            Vector3 pos = new Vector3((int)belowRoomBottomLeft.x + (roomDimension/2) - 2, y, 0);
            Tile newTile = new Wall(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }
        // Generates the right walls of the hallway
        for (int y = (int)belowRoomBottomLeft.y + roomDimension + 1; y < (int)belowRoomBottomLeft.y + sideLength - 1; y++){
            Vector3 pos = new Vector3((int)belowRoomBottomLeft.x + (roomDimension/2) + 1, y, 0);
            Tile newTile = new Wall(pos);
            newMap.setTile(pos, newTile);
            scene.Instantiate(newTile);
        }
        return newMap;
    }
}

package org.group11.Packages.Game.Scripts.MapGenerators;

import org.group11.Packages.Core.DataStructures.Vector2;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Scene;
import org.group11.Packages.Game.Scripts.Logic.Map;
import org.group11.Packages.Game.Scripts.Logic.MapGenerator;
import org.group11.Packages.Game.Scripts.Tile_Scripts.CubeWall.CubeWall;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Floor;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Tile;
import org.group11.Packages.Game.Scripts.Tile_Scripts.Wall;

import javax.sound.sampled.Line;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Shamelessly copying and modifying to work in Java an old room generator algorithm I made based on someone
 * else's idea. https://github.com/MeaninglessLegacy/test_project_1 -Eric
 */
public class Dungeon extends MapGenerator {
    private class Tile {
        public Vector2 position;
        public int type;
        public Tile(Vector2 pos){
            this.position = pos;
        }
    }

    private class Room {
        public Vector2 midpoint;
        public Vector2 size;

        public int node;
        public int roomType = 0; // 0-room, 1-major

        public Room(float x, float y)
        {
            midpoint = new Vector2(x,y);
        }
    }

    private class LineSegment {
        public Vector2 node_1_position;
        public Vector2 node_2_position;
        public int node_1_number;
        public int node_2_number;
        public double magnitude;

        public LineSegment(Vector2 node_1, Vector2 node_2){
            node_1_position = node_1;
            node_2_position = node_2;
        }

        public double ComputeMagnitude()
        {
            double x = node_2_position.x - node_1_position.x;
            double y = node_2_position.y - node_1_position.y;
            magnitude = Math.sqrt(x * x + y * y);
            return magnitude;
        }
    }

    private class KurskalNode
    {
        public int node_number = -1;
        public int parent = -1;
        public int rank = -1;
    }

    private static Vector2 getCirclePoint(double circle_radius, double angle)
    {
        float x = (float)(1.5*circle_radius * Math.cos(angle));
        float y = (float)(circle_radius * Math.sin(angle));
        Vector2 point = new Vector2(x, y);
        return point;
    }

    private static boolean ComputeRoomOverlap(Room A, Room B)
    {
        float roomALeft = A.midpoint.x - A.size.x / 2; float roomARight = A.midpoint.x + A.size.x / 2;
        float roomBLeft = B.midpoint.x - B.size.x / 2; float roomBRight = B.midpoint.x + B.size.x / 2;
        float roomATop = A.midpoint.y + A.size.y / 2; float roomABottom = A.midpoint.y - A.size.y / 2;
        float roomBTop = B.midpoint.y + B.size.y / 2; float roomBBottom = B.midpoint.y - B.size.y / 2;
        boolean x_overlap = (roomALeft >= roomBLeft && roomARight <= roomBRight)
                ||(roomALeft <= roomBLeft && roomARight <= roomBRight && roomARight >= roomBLeft)
                ||(roomALeft >= roomBLeft && roomARight >= roomBRight && roomALeft <= roomBRight)
                ||(roomALeft <= roomBLeft && roomARight >= roomBRight);
        boolean y_overlap = (roomATop >= roomBTop && roomABottom <= roomBBottom)
                ||(roomATop <= roomBTop && roomABottom <= roomBBottom && roomATop >= roomBBottom)
                ||(roomATop >= roomBTop && roomABottom >= roomBBottom && roomABottom <= roomBTop)
                ||(roomATop <= roomBTop && roomABottom >= roomBBottom);
        return x_overlap == true && y_overlap == true;
    }


    public static Vector2 computeSeperation(Room room, List<Room> all_rooms, int spacing)
    {
        Vector2 separation_vector = new Vector2(0, 0);
        int neighborCount = 0;
        for(Room otherRoom : all_rooms){
            if (otherRoom != room)
            {
                // add spacing to room
                room.size.x += spacing;
                room.size.y += spacing;
                otherRoom.size.x += spacing;
                otherRoom.size.y += spacing;
                if (ComputeRoomOverlap(room, otherRoom))
                {
                    separation_vector.x += otherRoom.midpoint.x - room.midpoint.x;
                    separation_vector.y += otherRoom.midpoint.y - room.midpoint.y;
                    neighborCount++;
                }
                // remove spacing to room
                room.size.x -= spacing;
                room.size.y -= spacing;
                otherRoom.size.x -= spacing;
                otherRoom.size.y -= spacing;
            }
        }
        if (neighborCount == 0) return separation_vector;
        double vector_magnitude = Math.sqrt(separation_vector.x * separation_vector.x + separation_vector.y * separation_vector.y);
        separation_vector.x *= -1 / vector_magnitude;
        separation_vector.y *= -1 / vector_magnitude;
        return separation_vector;
    }

    private static boolean ComputeIfBetween(double x, double x_1, double x_2)
    {
        if(x_1 == x_2)
        {
            return (x == x_1 && x == x_2);
        }
        return ((x_1 < x && x < x_2) || (x_2 < x && x < x_1));
    }

    private static boolean ComputeIfIntersect(LineSegment line_1, LineSegment line_2)
    {
        double a_1 = line_1.node_2_position.y - line_1.node_1_position.y;
        double b_1 = line_1.node_1_position.x - line_1.node_2_position.x;
        double c_1 = a_1 * line_1.node_1_position.x + b_1 * line_1.node_1_position.y;

        double a_2 = line_2.node_2_position.y - line_2.node_1_position.y;
        double b_2 = line_2.node_1_position.x - line_2.node_2_position.x;
        double c_2 = a_2 * line_2.node_1_position.x + b_2 * line_2.node_1_position.y;

        double det = a_1 * b_2 - b_1 * a_2;
        double epsilon = 0.00001;

        if(Math.abs(det) < epsilon)
        {
            // parallel lines, check if they overlap
            if(Math.abs(line_1.node_1_position.x - line_2.node_1_position.x) < epsilon)
            {
                // vertical line case, check if y ranges overlap
                if (ComputeIfBetween(line_2.node_1_position.y, line_1.node_1_position.y, line_1.node_2_position.y) || ComputeIfBetween(line_2.node_2_position.y, line_1.node_1_position.y, line_1.node_2_position.y)) return true;
            }
            else
            {
                // any other case, check if x_ranges overlap
                if (ComputeIfBetween(line_2.node_1_position.x, line_1.node_1_position.x, line_1.node_2_position.x) || ComputeIfBetween(line_2.node_2_position.x, line_1.node_1_position.x, line_1.node_2_position.x)) return true;
            }
            // parallel no overlap
            return false;
        }

        double intersect_x = (b_2 * c_1 - b_1 * c_2) / det;

        if (ComputeIfBetween(intersect_x, line_1.node_1_position.x, line_1.node_2_position.x) && ComputeIfBetween(intersect_x, line_2.node_1_position.x, line_2.node_2_position.x)) return true;

        return false;
    }

    private static void ConnectKurskalNodes(int old_parent, int new_parent, List<KurskalNode> all_nodes) {
        for(KurskalNode kNode : all_nodes){
            if(kNode.parent == old_parent){
                kNode.parent = new_parent;
            }
        }
    }

    @Override
    public Map generateMap() {
        // generation parameters
        int gen_rooms = 50;
        double generation_radius = 20;
        int min_size = 20;
        int max_size = 40;
        int size_threshold = 10;
        int min_hallway_size = 4;
        int max_hallway_size = 5;
        int room_max_count = 10;
        int room_connectivity = 100;
        int room_spacing = 10;
        // Randomly generate rooms
        List<Room> room_points = new ArrayList<>();
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < gen_rooms; i++){
            double angle = 2* Math.PI * r.nextDouble();
            double radius = generation_radius * r.nextDouble();
            Vector2 room_midpoint = getCirclePoint(radius, angle);
            Room room = new Room(room_midpoint.x, room_midpoint.y);
            room_points.add(room);
            int room_width = (int)Math.ceil(r.nextInt((max_size-min_size))+min_size);
            int room_height = (int)Math.ceil(r.nextInt((max_size-min_size)/2)+min_size);
            room.size = new Vector2(room_width, room_height);
            room.node = i;
        }
        // Separate rooms by flocking behaviour
        List<Vector2> separationVectors = new ArrayList<>();
        for(int i = 0; i < room_points.size(); i++){
            separationVectors.add(i, new Vector2());
        }
        boolean simulate = true;
        int simSteps = 0;
        while(simulate == true && simSteps < 1000){
            boolean continue_simulation = false;
            simSteps++;
            for (int i = 0; i < room_points.size(); i++) {
                Vector2 sepVec = separationVectors.get(i);
                sepVec = computeSeperation(room_points.get(i), room_points, room_spacing);
                if (Math.sqrt(sepVec.x * sepVec.x + sepVec.y * sepVec.y) != 0)
                {
                    continue_simulation = true;
                }
            }
            for (int i = 0; i < room_points.size(); i++) {
                room_points.get(i).midpoint.x += separationVectors.get(i).x*3;
                room_points.get(i).midpoint.y += separationVectors.get(i).y*3;
            }
            if (!continue_simulation) break;
        }
        // 1/4 room sizes
        for(Room room : room_points){
            room.size.x = Math.round(room.size.x/4);
            room.size.y = Math.round(room.size.y/4);
        }
        // Snap all rooms back to grid alignment
        for(Room room : room_points){
            room.midpoint.x = (float)Math.floor(room.midpoint.x);
            room.midpoint.y = (float)Math.floor(room.midpoint.y);
            // rooms with odd lengths need to have their centered shifted over, this does cause overlap
            if(room.size.x % 2 != 0) {
                room.midpoint.x = (float)(Math.floor(room.midpoint.x)+0.5);
            }
            if (room.size.y % 2 != 0) {
                room.midpoint.y = (float)(Math.floor(room.midpoint.y)+0.5);
            }
        }
        // select the largest rooms to use
        List<Room> majorRooms = new ArrayList<>();
        for(Room room : room_points){
            int roomSize = (int)(room.size.x*room.size.y);
            if (roomSize >= size_threshold){
                majorRooms.add(room);
            }
        }
        // select some major rooms
        List<Room> selectedRooms = new ArrayList<>();
        int z = 0;
        while(z < room_max_count && z < majorRooms.size()){
            Room selectedRoom = majorRooms.get(r.nextInt(majorRooms.size()));
            if(selectedRoom.roomType != 1){
                selectedRoom.roomType = 1;
                selectedRooms.add(selectedRoom);
                z++;
            }
        }
        //sort from left to right by x value
        Collections.sort(selectedRooms, (o1,o2) -> (int) (o1.midpoint.x - o2.midpoint.x));
        // create line segement connections between rooms and triangulate
        List<LineSegment> lineSegs = new ArrayList<>();
        if(selectedRooms.size() < 4 && selectedRooms.size() > 1){
            for(int i = 1; i < selectedRooms.size(); i++){
                Vector2 n1 = new Vector2(selectedRooms.get(i).midpoint.x, selectedRooms.get(i).midpoint.y);
                Vector2 n2 = new Vector2(selectedRooms.get(i-1).midpoint.x, selectedRooms.get(i-1).midpoint.y);
                LineSegment sN = new LineSegment(n1, n2);
                sN.node_1_number = selectedRooms.get(i).node;
                sN.node_2_number = selectedRooms.get(i-1).node;
                lineSegs.add(sN);
            }
        }else if(selectedRooms.size() >= 4){
            Vector2 n1 = new Vector2(selectedRooms.get(0).midpoint.x, selectedRooms.get(0).midpoint.y);
            Vector2 n2 = new Vector2(selectedRooms.get(1).midpoint.x, selectedRooms.get(1).midpoint.y);
            Vector2 n3 = new Vector2(selectedRooms.get(2).midpoint.x, selectedRooms.get(2).midpoint.y);
            LineSegment sN1 = new LineSegment(n1, n2);
            sN1.node_1_number = selectedRooms.get(0).node;
            sN1.node_2_number = selectedRooms.get(1).node;
            LineSegment sN2 = new LineSegment(n1, n3);
            sN2.node_1_number = selectedRooms.get(0).node;
            sN2.node_2_number = selectedRooms.get(2).node;
            LineSegment sN3 = new LineSegment(n2, n3);
            sN3.node_1_number = selectedRooms.get(0).node;
            sN3.node_2_number = selectedRooms.get(2).node;
            lineSegs.add(sN1);
            lineSegs.add(sN2);
            lineSegs.add(sN3);
            for(int i=3; i < selectedRooms.size(); i++){
                Vector2 N1 = new Vector2(selectedRooms.get(i).midpoint.x, selectedRooms.get(i).midpoint.y);
                Vector2 N2 = new Vector2(selectedRooms.get(i-1).midpoint.x, selectedRooms.get(i-1).midpoint.y);
                LineSegment SN = new LineSegment(N1, N2);
                SN.node_1_number = selectedRooms.get(i).node;
                SN.node_2_number = selectedRooms.get(i-1).node;
                lineSegs.add(SN);
                for(z = i-2; z >= 0;z--){
                    Vector2 N3 = new Vector2(selectedRooms.get(i).midpoint.x, selectedRooms.get(i).midpoint.y);
                    LineSegment SN2 = new LineSegment(N1, N3);
                    SN2.node_1_number = selectedRooms.get(i).node;
                    SN2.node_2_number = selectedRooms.get(z).node;
                    boolean checkSegGood = true;
                    for(LineSegment lineSeg : lineSegs){
                        if(ComputeIfIntersect(lineSeg,SN2)){
                            checkSegGood = false;
                            break;
                        }
                    }
                    if(checkSegGood) lineSegs.add(SN2);
                }
            }
            // calculate mags
            for(LineSegment lineSeg : lineSegs) lineSeg.ComputeMagnitude();
            // connect rooms by min span tree, sort line segs by ascending order
            Collections.sort(lineSegs, (o1, o2) -> (int)(o2.magnitude - o1.magnitude));
            // Kurskal's minimm spanning tree algorithm
            List<LineSegment> minTree = new ArrayList<>();
            List<LineSegment> discardedSegs = new ArrayList<>();
            List<KurskalNode> kNodes = new ArrayList<>();
            for(LineSegment lineSeg : lineSegs){
                boolean node1Good = false;
                boolean node2Good = false;
                // check existing nodes and create new nodes for kruskal tree
                for(KurskalNode kNode : kNodes){
                    if(kNode.node_number == lineSeg.node_1_number){
                        node1Good = true;
                        break;
                    }
                    if(kNode.node_number == lineSeg.node_2_number){
                        node2Good = true;
                        break;
                    }
                }
                if (node1Good == false)
                {
                    KurskalNode kn = new KurskalNode();
                    kn.node_number = lineSeg.node_1_number;
                    kn.parent = lineSeg.node_1_number;
                    kn.rank = 1;
                    kNodes.add(kn);
                }
                if (node2Good == false)
                {
                    KurskalNode kn = new KurskalNode();
                    kn.node_number = lineSeg.node_2_number;
                    kn.parent = lineSeg.node_2_number;
                    kn.rank = 1;
                    kNodes.add(kn);
                }
            }
            // make tree
            for(LineSegment lineSeg : lineSegs){
                KurskalNode kn1 = new KurskalNode();
                KurskalNode kn2 = new KurskalNode();
                for(KurskalNode kNode : kNodes){
                    if(kNode.node_number == lineSeg.node_1_number)kn1 = kNode;
                    if(kNode.node_number == lineSeg.node_2_number)kn2 = kNode;
                }
                if(kn1.parent != kn2.parent){
                    minTree.add(lineSeg);
                    if(kn1.rank > kn2.rank){
                        kn1.rank = kn1.rank+1;
                        ConnectKurskalNodes(kn2.parent, kn1.parent, kNodes);
                    }else{
                        kn2.rank = kn2.rank+1;
                        ConnectKurskalNodes(kn1.parent, kn2.parent, kNodes);
                    }
                }else{
                    discardedSegs.add(lineSeg);
                }
            }
            // add segs back if want some extra connections
            if(room_connectivity > 0){
                int extraConnections = discardedSegs.size()*room_connectivity/100;
                if(extraConnections > discardedSegs.size()) extraConnections = discardedSegs.size();
                for(int i = 0; i < extraConnections; i++){
                    int randIndex = r.nextInt(discardedSegs.size());
                    minTree.add(discardedSegs.get(randIndex));
                    discardedSegs.remove(randIndex);
                }
            }
            // create hallways
            List<LineSegment> hallways = new ArrayList<>();
            for(LineSegment lineSeg : minTree){
                if(r.nextInt(2)==0){
                    Vector2 v1 = new Vector2(lineSeg.node_1_position.x, lineSeg.node_1_position.y);
                    Vector2 v2 = new Vector2(lineSeg.node_1_position.x, lineSeg.node_2_position.y);
                    LineSegment v_seg = new LineSegment(v1, v2);
                    Vector2 h1 = new Vector2(lineSeg.node_1_position.x, lineSeg.node_2_position.y);
                    Vector2 h2 = new Vector2(lineSeg.node_2_position.x, lineSeg.node_2_position.y);
                    LineSegment h_seg = new LineSegment(h1, h2);
                    hallways.add(v_seg);
                    hallways.add(h_seg);
                }else{
                    Vector2 h1 = new Vector2(lineSeg.node_1_position.x, lineSeg.node_1_position.y);
                    Vector2 h2 = new Vector2(lineSeg.node_2_position.x, lineSeg.node_2_position.y);
                    LineSegment v_seg = new LineSegment(h1, h2);
                    Vector2 v1 = new Vector2(lineSeg.node_2_position.x, lineSeg.node_1_position.y);
                    Vector2 v2 = new Vector2(lineSeg.node_2_position.x, lineSeg.node_2_position.y);
                    LineSegment h_seg = new LineSegment(v1, v2);
                    hallways.add(v_seg);
                    hallways.add(h_seg);
                }
            }
            // connect rooms with hallways, cut off rooms that don't touch hallways
            List<Room> finalRooms = new ArrayList<>();
            for(LineSegment lineSeg : hallways){
                double room_width = Math.ceil(Math.abs(lineSeg.node_1_position.x - lineSeg.node_2_position.x));
                double room_height = Math.ceil(Math.abs(lineSeg.node_1_position.y - lineSeg.node_2_position.y));
                double room_center_x = Math.floor((lineSeg.node_1_position.x + lineSeg.node_2_position.x)/2);
                double room_center_y = Math.floor((lineSeg.node_1_position.y + lineSeg.node_2_position.y)/2);
                if (room_width < min_hallway_size) {
                    room_width = Math.ceil(r.nextInt(max_hallway_size-min_hallway_size) + min_hallway_size);
                    room_center_x = Math.floor(lineSeg.node_1_position.x);
                };
                if(room_height < min_hallway_size) {
                    room_height = Math.ceil(r.nextInt(max_hallway_size-min_hallway_size) + min_hallway_size);
                    room_center_y = Math.floor(lineSeg.node_1_position.y);
                }
                // center shifts
                if (room_width % 2 != 0) {
                    room_center_x = Math.floor(room_center_x) + 0.5;
                }
                if (room_height % 2 != 0) {
                    room_center_y = Math.floor(room_center_y) + 0.5;
                }
                Room new_hallway = new Room((float)room_center_x, (float)room_center_y);
                new_hallway.size = new Vector2();
                new_hallway.size.x = (float)room_width;
                new_hallway.size.y = (float)room_height;
                new_hallway.roomType = 2;
                finalRooms.add(new_hallway);
            }
            int hallwaysCount = finalRooms.size();
            for(Room room : selectedRooms){
                for(int i=0; i <hallwaysCount;i++){
                    if(ComputeRoomOverlap(room, finalRooms.get(i))){
                        finalRooms.add(room);
                        break;
                    }
                }
            }
            List<Tile> tiles = new ArrayList<>();
            for(Room room : finalRooms){
                Vector2 startCorner = new Vector2(room.midpoint.x - room.size.x / 2, room.midpoint.y + room.size.y / 2);
                Vector2 fileOffset = new Vector2(0f, 0f);
                for(int w = 0; w < room.size.x; w++){
                    for(int h = 0; h < room.size.y; h++){
                        Vector2 newTilePos = new Vector2((startCorner.x + fileOffset.x + w), (float)(startCorner.y - fileOffset.y - h));
                        boolean old = false;
                        for(Tile t : tiles){
                            if(t.position.x == newTilePos.x && t.position.y == newTilePos.y){
                                old = true;
                                if(room.roomType == 1){
                                    t.type = 1;
                                }
                                if(room.roomType == 1 && t.type != 1){
                                    t.type = 0;
                                }
                                break;
                            }
                        }
                        if(old == false){
                            Tile nT = new Tile(newTilePos);
                            nT.type = room.roomType;
                            tiles.add(nT);
                        }
                    }
                }
            }
            // convert edge tiles to walls
            for(Tile t : tiles){
                Vector2 yPlus = new Vector2(t.position.x, t.position.y+1);
                Vector2 yNeg = new Vector2(t.position.x, t.position.y-1);
                Vector2 xPlus = new Vector2(t.position.x+1, t.position.y);
                Vector2 xNeg = new Vector2(t.position.x-1, t.position.y);
                int neigh = 0;
                for(Tile testT : tiles){
                    if (
                            testT.position.x == yPlus.x && testT.position.y == yPlus.y
                                    || testT.position.x == yNeg.x && testT.position.y == yNeg.y
                                    || testT.position.x == xPlus.x && testT.position.y == xPlus.y
                                    || testT.position.x == xNeg.x && testT.position.y == xNeg.y)
                        neigh++;
                }
                if(neigh != 4) t.type = 3;
            }
            // now we can make tiles at last
            Map newMap = new Map();
            Scene scene = Scene.get_scene();
            String[] _floorTextures = {
                    "./Resources/WhiteTile.png",
                    "./Resources/WhiteTile-2.png",
                    "./Resources/WhiteTile-3.png"
            };
            for(Tile t : tiles){
                org.group11.Packages.Game.Scripts.Tile_Scripts.Tile newTile;
                Vector3 pos = new Vector3(t.position.x, t.position.y, 0);
                if(t.type != 3){
                    newTile = new Floor(pos, _floorTextures[r.nextInt(_floorTextures.length)]);
                }else{
                    newTile = new CubeWall();
                }
                newTile.transform.setPosition(pos);
                newMap.setTile(pos, newTile);
                scene.Instantiate(newTile);
            }
            return newMap;
        }
        return null;
    }
}

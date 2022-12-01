package org.group11.Packages.Core.Renderer;

import org.group11.Packages.Core.Components.Sprite;
import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Camera;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Util.Constants;
import org.lwjgl.opengl.GL45;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.lwjgl.opengl.GL11C.*;

/**
 * Stores a list of objects to render and is called by the Scene to
 */
public class Renderer {
    private static Renderer _renderer = null; // shared renderer object

    //******************************************************************************************************************
    //* objects that need rendering
    //******************************************************************************************************************
    public static List<SpriteRenderer> spriteRenderers = new ArrayList<>();

    //******************************************************************************************************************
    //* core
    //******************************************************************************************************************
    /**
     * <p>Initializes the renderer and matches the projection matrix with the window resolution.</p>
     * If the window resolution changes both the projection matrix and the _mainCamera's viewSize needs to be reshaped
     * to match the new window resolution
     * the active camera viewing the scene through.
     */
    public void init(){
        System.out.println("Initializing renderer");

        // unbind textures
        GL45.glBindTexture(GL45.GL_TEXTURE_2D, -1);

        //match projection to window resolution (could be in reshape callback)
        GL45.glMatrixMode(GL45.GL_PROJECTION);
        /* Set near far clipping planes between 0-1.
        When an object is being rendered, it's z-distance (distance) from the camera needs to be normalized between 0-1,
        0 = on the near plane, 1 = on the far plane. Anything behind the near plane will not be rendered.
        */
        GL45.glOrtho(0, Constants.SCREEN_WIDTH, 0, Constants.SCREEN_HEIGHT, 0, 1);
        GL45.glMatrixMode(GL45.GL_MODELVIEW);

        System.out.println("Renderer initialized");
    }

    /*
    major changes needed:
    1. first pass to render opaques using depth buffer
    2. second pass to render transparent objects without depth buffer
    3. third pass to render opaques again with depth buffer
     */
    private int untilSort = 1;
    /**
     * <p><i>Under development: currently renders all SpriteRenderers stored in _spriteRenderers using a given
     * Camera object and a pin-hole camera projection.</i></p>
     */
    public void render(Camera camera){
        if(camera == null) return; // is camera does not exist do not render
        // enable depth test to render opaques
        glEnable(GL45.GL_DEPTH_TEST);
        // disable depth test to render transparent
        GL45.glDisable(GL45.GL_DEPTH_TEST);
        // enable GL_TEXTURE_2D
        GL45.glEnable(GL45.GL_TEXTURE_2D);
        // slow method to sort transparent objects for rendering by layers
        if(untilSort-- == 0){
            this.sortSpriteRenderers(camera);
            untilSort = 1;
        }
        // render sprite renders with our camera using the pin-hole camera model
        for(SpriteRenderer renderer : spriteRenderers){
            if(renderer.enabled){
                // get objects information
                Sprite sprite = renderer.get_sprite();
                GameObject gameObject = renderer.get_gameObject();
                RenderComponent renderComponent = sprite.renderComponent;
                // get texture and quad cords
                Vector3[] texCords = renderComponent.get_texCords();
                Vector3[] quadCords = renderComponent.get_quadCords();
                Vector3 gameObjectPos = gameObject.transform.position;
                Vector3 spriteObjectPos = sprite.transform.position;
                Vector3[] quadVertices = new Vector3[4];
                // process quad vertices through camera
                for(int i = 0; i < quadVertices.length; i++){
                    // quad position is the sum of GameObject + SpriteObject + Quad
                    quadVertices[i] = new Vector3(
                            spriteObjectPos.x + gameObjectPos.x + quadCords[i].x,
                            spriteObjectPos.y + gameObjectPos.y+ quadCords[i].y,
                            spriteObjectPos.z+gameObjectPos.z + quadCords[i].z);
                    quadVertices[i] = camera.pinHoleModel(quadVertices[i]); // create projection
                    /*
                    Z-distance (distance) from the camera needs to be normalized between 0-1,
                    0 = on the near plane, 1 = on the far plane.
                     */
                    //quadVertices[i].z =  -quadVertices[i].z/( quadVertices[i].z+1);
                }
                // get texture and bind texture
                Texture texture = renderComponent.get_texture();
                texture.bind();
                // draw object
                GL45.glBegin(GL45.GL_QUADS);
                for(int i=0; i < texCords.length && i < quadCords.length; i++){
                    Vector3 vex = quadVertices[i];
                    // draw transparent objects without depth buffer
                    GL45.glTexCoord2f((int)texCords[i].x, (int)texCords[i].y); GL45.glVertex2d( vex.x, vex.y);
                    // vex.z is negative because somehow our world units are inverted (with depth buffer)
                    //GL45.glTexCoord3f((int)texCords[i].x, (int)texCords[i].y, vex.z); GL45.glVertex3f( vex.x, vex.y, vex.z);
                }
                // end drawing object
                GL45.glEnd();
                // unbind texture
                texture.unbind();
            }
        }
        // disable GL_TEXTURE_2D
        GL45.glDisable(GL45.GL_TEXTURE_2D);
    }

    //******************************************************************************************************************
    //* add and remove objects from the renderList
    //******************************************************************************************************************
    /**
     * Add game object to rendering list.
     * @param gameObject
     */
    public void add(GameObject gameObject){
        SpriteRenderer spriteRenderer = gameObject.getComponent(SpriteRenderer.class);
        if(spriteRenderer != null){
            add(spriteRenderer);
        }
    }

    /**
     * Remove game object from rendering list.
     * @param gameObject
     */
    public void remove(GameObject gameObject){
        SpriteRenderer spriteRenderer = gameObject.getComponent(SpriteRenderer.class);
        if(spriteRenderer != null){
            remove(spriteRenderer);
        }
    }

    /**
     * Add spriteRenderer to rendering list.
     * @param spriteRenderer
     */
    public void add(SpriteRenderer spriteRenderer){
        spriteRenderers.add(spriteRenderer);
    }

    /**
     * Remove spriteRenderer from rendering list.
     * @param spriteRenderer
     */
    public void remove(SpriteRenderer spriteRenderer){
        spriteRenderers.remove(spriteRenderer);
    }

    //******************************************************************************************************************
    //* renderList sorting for transparent objects
    //******************************************************************************************************************
    /**
     * Find's the distance from the first world point to the second world point.
     * @param worldPoint1 The first position.
     * @param worldPoint2 The second position.
     * @return The distance between the two points.
     */
    private double distCheck(Vector3 worldPoint1, Vector3 worldPoint2){
        double xDiff = worldPoint1.x - worldPoint2.x;
        double yDiff = worldPoint1.y - worldPoint2.y;
        double zDiff = worldPoint1.z - worldPoint2.z;
        return Math.sqrt(xDiff*xDiff+yDiff*yDiff+zDiff*zDiff);
    }

    /**
     * Compares the magnitude of two doubles.
     * @param d1 The first double.
     * @param d2 The second double.
     * @return 1 if d1 > d2. -1 if d2 > d1. 0 if d1 == d2.
     */
    private int compareMagnitude(double d1, double d2){
        if(d1 > d2) return 1;
        if(d2 > d1) return -1;
        return 0;
    }

    /**
     * Sorts all sprites based on z-distance.
     */
    private void sortSpriteRenderers(){
        Collections.sort(spriteRenderers, (o1, o2) -> compareMagnitude(o2.get_gameObject().transform.position.z+o2.get_sprite().transform.position.z ,o1.get_gameObject().transform.position.z+o1.get_sprite().transform.position.z));
    }

    /**
     * Sorts by the one SpriteRenderer with the closest quad point.
     */
    private class SortByDistance implements Comparator<SpriteRenderer>
    {
        private Camera _camera;
        private Vector3 _cameraPos;
        SortByDistance(Camera camera){
            _camera = camera;
            _cameraPos=camera.transform.position;
        }
        public int compare(SpriteRenderer a, SpriteRenderer b)
        {
            Vector3 apos = new Vector3(a.get_gameObject().transform.position.x+a.get_sprite().transform.position.x, a.get_gameObject().transform.position.y+a.get_sprite().transform.position.y,a.get_gameObject().transform.position.z+a.get_sprite().transform.position.z);
            Vector3 bpos = new Vector3(b.get_gameObject().transform.position.x+b.get_sprite().transform.position.x, b.get_gameObject().transform.position.y+b.get_sprite().transform.position.y,b.get_gameObject().transform.position.z+b.get_sprite().transform.position.z);
            Vector3[] aquad = a.get_sprite().renderComponent.get_quadCords();
            Vector3[] bquad = b.get_sprite().renderComponent.get_quadCords();
            double farA = 0;
            double farB = 0;
            boolean start = false;
            for(int i = 0; i < 4; i++){
                Vector3 acom = new Vector3(apos.x+aquad[i].x, apos.y+aquad[i].y,apos.z+aquad[i].z);
                Vector3 bcom = new Vector3(bpos.x+bquad[i].x, bpos.y+bquad[i].y,bpos.z+bquad[i].z);
                //acom = _camera.pinHoleModel(acom);
                //bcom = _camera.pinHoleModel(bcom);
                double aDist = distCheck(acom, _cameraPos);
                double bDist = distCheck(bcom, _cameraPos);
                if(start == false){
                    start = true;
                    farA = aDist;
                    farB = bDist;
                }
                if(aDist > farA) farA = aDist;
                if(bDist > farB) farB = bDist;
            }
            return compareMagnitude(farB, farA);
        }
    }

    /**
     * Sorts all sprites based on distance to camera.
     */
    private void sortSpriteRenderers(Camera camera){
        // this sorts by quad with the point that is far away
        Collections.sort(spriteRenderers, new SortByDistance(camera));
    }

    //******************************************************************************************************************
    //* getters
    //******************************************************************************************************************
    /**
     * Return the only instance of the Renderer class or create a new Renderer.
     * @return Renderer object.
     */
    public static Renderer get_renderer(){
        if(_renderer == null){
            _renderer = new Renderer();
        }
        return _renderer;
    }
}

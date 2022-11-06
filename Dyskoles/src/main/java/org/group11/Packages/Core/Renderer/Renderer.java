package org.group11.Packages.Core.Renderer;

import org.group11.Packages.Core.Components.Sprite;
import org.group11.Packages.Core.Components.SpriteRenderer;
import org.group11.Packages.Core.DataStructures.Vector2;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Camera;
import org.group11.Packages.Core.Main.GameObject;
import org.group11.Packages.Core.Util.Constants;
import org.lwjgl.opengl.GL45;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores a list of objects to render and is called by the Scene to
 */
public class Renderer {
    //******************************************************************************************************************
    //* singleton constructor and methods
    //******************************************************************************************************************
    private static Renderer _renderer;

    private Renderer() {}

    /**
     * Checks if an instance of Renderer exists yet. If it does, returns that instance. If not, creates and returns a
     * new instance
     * @return the Renderer object if it exists already, new Renderer object if it doesn't yet
     */
    public static Renderer get_renderer(){
        if(_renderer == null){
            _renderer = new Renderer();
        }
        return _renderer;
    }

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

    /**
     * <p><i>Under development: currently renders all SpriteRenderers stored in _spriteRenderers using a given
     * Camera object and a pin-hole camera projection.</i></p>
     */
    public void render(Camera camera){
        if(camera == null) return; // is camera does not exist do not render
        // enable GL_TEXTURE_2D
        GL45.glEnable(GL45.GL_TEXTURE_2D);
        // render sprite renders with our camera using the pin-hole camera model
        for(SpriteRenderer renderer : spriteRenderers){
            if(renderer.enabled){
                // get objects information
                Sprite sprite = renderer.get_sprite();
                GameObject gameObject = renderer.get_gameObject();
                RenderComponent renderComponent = sprite.renderComponent;
                // get texture and quad cords
                Vector2[] texCords = renderComponent.get_texCords();
                Vector2[] quadCords = renderComponent.get_quadCords();
                Vector3 gameObjectPos = gameObject.transform.position;
                Vector3 spriteObjectPos = sprite.transform.position;
                Vector3[] quadVertices = new Vector3[4];
                // process quad vertices through camera
                for(int i = 0; i < quadVertices.length; i++){
                    quadVertices[i] = new Vector3(
                            spriteObjectPos.x + gameObjectPos.x + quadCords[i].x,
                            spriteObjectPos.y + gameObjectPos.y+ quadCords[i].y,
                            spriteObjectPos.z+gameObjectPos.z);
                    quadVertices[i] = camera.pinHoleModel(quadVertices[i]); // create projection
                    /*
                    Z-distance (distance) from the camera needs to be normalized between 0-1,
                    0 = on the near plane, 1 = on the far plane.
                     */
                    quadVertices[i].z =  quadVertices[i].z/( quadVertices[i].z+1);
                }
                // get texture and bind texture
                Texture texture = renderComponent.get_texture();
                texture.bind();
                // draw object
                GL45.glBegin(GL45.GL_QUADS);
                for(int i=0; i < texCords.length && i < quadCords.length; i++){
                    Vector3 vex = quadVertices[i];
                    // vex.z is negative because our world space units for Z-ONLY are inverted compared to OpenGL
                    GL45.glTexCoord3f((int)texCords[i].x, (int)texCords[i].y, -vex.z); GL45.glVertex3f( vex.x, vex.y, -vex.z);
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
     * Adds a GameObject's SpriteRenderer to the renderList
     * @param gameObject GameObject who's SpriteRenderer to add to renderList
     */
    public void add(GameObject gameObject){
        SpriteRenderer spriteRenderer = gameObject.getComponent(SpriteRenderer.class);
        if(spriteRenderer != null){
            add(spriteRenderer);
        }
    }

    /**
     * Removes a GameObject SpriteRenderer to the renderList
     * @param gameObject GameObject who's SpriteRenderer to remove from the renderList
     */
    public void remove(GameObject gameObject){
        SpriteRenderer spriteRenderer = gameObject.getComponent(SpriteRenderer.class);
        if(spriteRenderer != null){
            remove(spriteRenderer);
        }
    }

    /**
     * Adds a SpriteRenderer to the renderList
     * @param spriteRenderer SpriteRenderer to add to the renderList
     */
    public void add(SpriteRenderer spriteRenderer){
        spriteRenderers.add(spriteRenderer);
    }

    /**
     * Removes a SpriteRenderer from the renderList
     * @param spriteRenderer SpriteRenderer to remove to the renderList
     */
    public void remove(SpriteRenderer spriteRenderer){
        spriteRenderers.remove(spriteRenderer);
    }
}

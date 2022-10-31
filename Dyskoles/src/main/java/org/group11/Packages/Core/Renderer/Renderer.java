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
 *
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
     * Initializes the renderer and then
     */
    public void init(){
        System.out.println("Initializing renderer");

        // unbind textures
        GL45.glBindTexture(GL45.GL_TEXTURE_2D, -1);

        //match projection to window resolution (could be in reshape callback)
        GL45.glMatrixMode(GL45.GL_PROJECTION);
        GL45.glOrtho(0, Constants.SCREEN_WIDTH, 0, Constants.SCREEN_HEIGHT, -1, 1);
        GL45.glMatrixMode(GL45.GL_MODELVIEW);
        System.out.println("Renderer initialized");
    }

    /**
     *
     */
    public void render(Camera camera){

        if(camera == null) return; // is camera does not exist do not render

        // enable GL_TEXTURE_2D
        GL45.glEnable(GL45.GL_TEXTURE_2D);

        // render sprite renders
        for(SpriteRenderer renderer : spriteRenderers){
            if(renderer.enabled){
                // get rendering information
                Sprite sprite = renderer.get_sprite();
                GameObject gameObject = renderer.get_gameObject();
                RenderComponent renderComponent = sprite.renderComponent;

                // get texture and quad cords
                Vector2[] texCords = renderComponent.get_texCords();
                Vector2[] quadCords = renderComponent.get_quadCords();
                Vector3 gameObjectPos = gameObject.transform.position;
                Vector3 spriteObjectPos = sprite.transform.position;
                Vector3[] quadVertices = new Vector3[] {
                        new Vector3(spriteObjectPos.x + gameObjectPos.x + quadCords[0].x, spriteObjectPos.y + gameObjectPos.y+ quadCords[0].y, gameObjectPos.z),
                        new Vector3(spriteObjectPos.x + gameObjectPos.x + quadCords[1].x, spriteObjectPos.y + gameObjectPos.y+ quadCords[1].y, gameObjectPos.z),
                        new Vector3(spriteObjectPos.x + gameObjectPos.x + quadCords[2].x, spriteObjectPos.y + gameObjectPos.y+ quadCords[2].y, gameObjectPos.z),
                        new Vector3(spriteObjectPos.x + gameObjectPos.x + quadCords[3].x, spriteObjectPos.y + gameObjectPos.y+ quadCords[3].y, gameObjectPos.z),
                };
                // process quad vertices through camera
                for(int i = 0; i < quadVertices.length; i++){
                    quadVertices[i] = camera.distortPoint(quadVertices[i]);
                }

                // get texture and bind texture
                Texture texture = renderComponent.get_texture();
                texture.bind();

                // draw object
                GL45.glBegin(GL45.GL_QUADS);

                for(int i=0; i < texCords.length && i < quadCords.length; i++){
                    int x = (int)(quadVertices[i].x);
                    int y = (int)(quadVertices[i].y);
                    // draw texture at quad cord
                    GL45.glTexCoord2i((int)texCords[i].x, (int)texCords[i].y); GL45.glVertex2i( x, y);
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
     *
     * @param gameObject
     */
    public void add(GameObject gameObject){
        SpriteRenderer spriteRenderer = gameObject.getComponent(SpriteRenderer.class);
        if(spriteRenderer != null){
            add(spriteRenderer);
        }
    }

    /**
     *
     * @param gameObject
     */
    public void remove(GameObject gameObject){
        SpriteRenderer spriteRenderer = gameObject.getComponent(SpriteRenderer.class);
        if(spriteRenderer != null){
            remove(spriteRenderer);
        }
    }

    /**
     *
     * @param spriteRenderer
     */
    public void add(SpriteRenderer spriteRenderer){
        spriteRenderers.add(spriteRenderer);
    }

    /**
     *
     * @param spriteRenderer
     */
    public void remove(SpriteRenderer spriteRenderer){
        spriteRenderers.remove(spriteRenderer);
    }
    //******************************************************************************************************************
    //* get renderer
    //******************************************************************************************************************
    /**
     *
     * @return
     */
    public static Renderer get_renderer(){
        if(_renderer == null){
            _renderer = new Renderer();
        }
        return _renderer;
    }
}

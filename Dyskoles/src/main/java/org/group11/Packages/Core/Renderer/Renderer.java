package org.group11.Packages.Core.Renderer;

import org.lwjgl.opengl.GL45;

/**
 *
 */
public class Renderer {
    private static Renderer _renderer = null; // shared renderer object

    Texture texture;
    /**
     *
     */
    public void init(){
        System.out.println("Initializing renderer");
        GL45.glBindTexture(GL45.GL_TEXTURE_2D, 0); // unbind textures
        //match projection to window resolution (could be in reshape callback)
        GL45.glMatrixMode(GL45.GL_PROJECTION);
        GL45.glOrtho(0, 1920, 0, 1080, -1, 1);
        GL45.glMatrixMode(GL45.GL_MODELVIEW);

        texture = new Texture("./Resources/ump45.png");
    }

    /**
     *
     */
    public void render(){
        texture.bind();
        GL45.glEnable(GL45.GL_TEXTURE_2D);
        GL45.glBegin(GL45.GL_QUADS);
        GL45.glTexCoord2i(0, 1); GL45.glVertex2i(0, 100);
        GL45.glTexCoord2i(0, 0); GL45.glVertex2i(0, 400);
        GL45.glTexCoord2i(1, 0); GL45.glVertex2i(400, 400);
        GL45.glTexCoord2i(1, 1); GL45.glVertex2i(400, 100);
        GL45.glEnd();

        GL45.glBegin(GL45.GL_QUADS);
        GL45.glTexCoord2i(1, 1); GL45.glVertex2i(400, 100);
        GL45.glTexCoord2i(1, 0); GL45.glVertex2i(400, 400);
        GL45.glTexCoord2i(0, 0); GL45.glVertex2i(800, 400);
        GL45.glTexCoord2i(0, 1); GL45.glVertex2i(800, 100);
        GL45.glEnd();
        GL45.glDisable(GL45.GL_TEXTURE_2D);
        texture.unbind();
    }

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

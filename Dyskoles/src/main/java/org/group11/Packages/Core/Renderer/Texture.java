package org.group11.Packages.Core.Renderer;

import org.lwjgl.BufferUtils;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL45;
import org.lwjgl.stb.STBImage;

/**
 * Texture class that implements generating and binding to a texture ID through OpenGL, then storing this texture ID
 * within the object.
 */
public class Texture {
    private String _filePath;
    private int _textureID;
    private boolean _opaque = false;

    /**
     * Constructor that generates and binds a texture ID with a given BufferedImage.
     * @param image BufferedImage object to generate texture with.
     * @param hasAlpha Boolean True/False if this texture has Alpha values.
     */
    public Texture(BufferedImage image, boolean hasAlpha){
        // Check OpenGL context, will crash if trying to bind a texture without a OpenGL context
        if(glfwGetCurrentContext() == NULL){
            System.out.println("Failed to load texture. Reason: No current OpenGL context.");
            System.exit(-1);
        }
        /* taken from and modified from
        https://stackoverflow.com/questions/59856706/how-can-i-load-bufferedimage-as-opengl-texture */
        int bytesPerPixel = 3; // rgb components
        if(hasAlpha) bytesPerPixel = 4; // alpha component
        int[] pixels = new int[image.getWidth() * image.getHeight()]; // make pixel array
        // load rgb into pixel array
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        // create byte buffer and transfer pixel data to byte buffer
        ByteBuffer buffer = ByteBuffer.allocateDirect(image.getWidth() * image.getHeight() * bytesPerPixel);
        for(int h = 0; h < image.getHeight(); h++) {
            for(int w = 0; w < image.getWidth(); w++) {
                int pixel = pixels[h * image.getWidth() + w];
                buffer.put((byte) ((pixel >> 16) & 0xFF)); // R
                buffer.put((byte) ((pixel >> 8) & 0xFF)); // G
                buffer.put((byte) (pixel & 0xFF)); // B
                if(hasAlpha){
                    buffer.put((byte) ((pixel >> 24) & 0xFF)); // A
                }
            }
        }
        buffer.flip(); // do not forget this

        // ByteBuffer is now filled with color data of each pixel
        // Create a texture ID and bind it in openGL

        GL45.glGenTextures(); // generate texture ID
        GL45.glBindTexture(GL45.GL_TEXTURE_2D, _textureID); // bind texture ID

        // Set texture parameters
        // when stretching pixelate
        GL45.glTexParameteri(GL45.GL_TEXTURE_2D, GL45.GL_TEXTURE_MIN_FILTER, GL45.GL_NEAREST);
        // when shrinking pixelate
        GL45.glTexParameteri(GL45.GL_TEXTURE_2D, GL45.GL_TEXTURE_MAG_FILTER, GL45.GL_NEAREST);
        // clamp to border
        GL11.glTexParameteri(GL45.GL_TEXTURE_2D, GL45.GL_TEXTURE_WRAP_S, GL45.GL_CLAMP_TO_BORDER);
        GL11.glTexParameteri(GL45.GL_TEXTURE_2D, GL45.GL_TEXTURE_WRAP_T, GL45.GL_CLAMP_TO_BORDER);

        GL45.glTexImage2D(GL45.GL_TEXTURE_2D, 0, GL45.GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL45.GL_RGBA, GL45.GL_UNSIGNED_BYTE, buffer);
        //GL45.glBindTexture(GL45.GL_TEXTURE_2D, 0); // unbind texture

        buffer.clear(); // do not forget this
    }

    /**
     * Generates and binds a texture ID with a texture given by a file path.
     * @param filePath String path to the texture file.
     * @param opaque If this texture should be treated as opaque. The texture may still be loaded with alpha but it will
     *               be treated as if it was an opaque object when rendering.
     */
    private void genTexture(String filePath, boolean opaque){
        this._filePath = filePath;
        this._opaque = opaque;

        // Check OpenGL context, will crash if trying to bind a texture without a OpenGL context
        if(glfwGetCurrentContext() == NULL){
            System.out.println("Failed to load texture \""+filePath+"\" Reason: No current OpenGL context.");
            System.exit(-1);
        }

        // get ByteButter with pixel color data
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1); // rgba...
        ByteBuffer image = STBImage.stbi_load(filePath, width, height, channels, 0);

        // Generate texture ID and bind texture with openGL
        _textureID = GL45.glGenTextures(); // generate texture ID
        GL45.glBindTexture(GL45.GL_TEXTURE_2D, _textureID); // bind texture ID
        System.out.println("Generated texture: "+filePath+" With texture ID: "+_textureID); // debug

        // Set texture parameters
        // when stretching pixelate
        GL45.glTexParameteri(GL45.GL_TEXTURE_2D, GL45.GL_TEXTURE_MIN_FILTER, GL45.GL_NEAREST);
        // when shrinking pixelate
        GL45.glTexParameteri(GL45.GL_TEXTURE_2D, GL45.GL_TEXTURE_MAG_FILTER, GL45.GL_NEAREST);
        // clamp to border
        GL11.glTexParameteri(GL45.GL_TEXTURE_2D, GL45.GL_TEXTURE_WRAP_S, GL45.GL_CLAMP_TO_BORDER);
        GL11.glTexParameteri(GL45.GL_TEXTURE_2D, GL45.GL_TEXTURE_WRAP_T, GL45.GL_CLAMP_TO_BORDER);

        if(image != null){
            if(channels.get(0)==4){ //rgba
                GL45.glTexImage2D(GL45.GL_TEXTURE_2D, 0, GL45.GL_RGBA8, width.get(0), height.get(0), 0, GL45.GL_RGBA, GL45.GL_UNSIGNED_BYTE, image);
            }else if(channels.get(0)==3){ // rgb
                GL45.glTexImage2D(GL45.GL_TEXTURE_2D, 0, GL45.GL_RGB, width.get(0), height.get(0), 0, GL45.GL_RGB, GL45.GL_UNSIGNED_BYTE, image);
                _opaque = false;
            }else{
                System.out.println("Failure to load texture: "+filePath);
                System.exit(-1);
            }
        }else{
            System.out.println("Failure to load texture: "+filePath);
            System.exit(-1);
        }

        STBImage.stbi_image_free(image); // free image
    }

    /**
     * Constructor that calls the genTexture method to generate a new texture with given parameters.
     * @param filePath String path to the texture file.
     * @param opaque If this texture should load as opaque.
     */
    public Texture(String filePath, boolean opaque){
        this._filePath = filePath;
        this.genTexture(filePath, opaque);
    }

    /**
     * Constructor that calls the genTexture method to generate a new texture with opaque=true.
     * @param filePath String path to the texture file.
     */
    public Texture(String filePath){
        this._filePath = filePath;
        this.genTexture(filePath, true);
    }

    /**
     * Binds this texture to the texture target.
     */
    public void bind(){
        GL45.glBindTexture(GL45.GL_TEXTURE_2D, _textureID);
    }

    /**
     * Unbinds this texture from the texture target.
     */
    public void unbind(){
        GL45.glBindTexture(GL45.GL_TEXTURE_2D, 0);
    }

    /**
     * Returns this texture's texture ID.
     * @return Int of this texture's texture ID.
     */
    public int get_textureID(){
        return _textureID;
    }

    /**
     * Returns if this texture was loaded as opaque or not.
     * @return If this texture was opaque.
     */
    public boolean is_opaque() {
        return _opaque;
    }
}

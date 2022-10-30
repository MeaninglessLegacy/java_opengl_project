package org.group11.Packages.Core.Renderer;

import org.lwjgl.BufferUtils;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL45;
import org.lwjgl.stb.STBImage;

/**
 *
 */
public class Texture {
    private String filePath;
    private int textureID;

    /**
     *
     * @param image
     * @param hasAlpha
     */
    Texture(BufferedImage image, boolean hasAlpha){
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
        GL45.glBindTexture(GL45.GL_TEXTURE_2D, textureID); // bind texture ID

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
     *
     * @param filePath
     */
    Texture(String filePath){
        this.filePath = filePath;

        // get ByteButter with pixel color data
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1); // rgba...
        ByteBuffer image = STBImage.stbi_load(filePath, width, height, channels, 0);

        // Generate texture ID and bind texture with openGL
        GL45.glGenTextures(); // generate texture ID
        GL45.glBindTexture(GL45.GL_TEXTURE_2D, textureID); // bind texture ID

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
                GL45.glTexImage2D(GL45.GL_TEXTURE_2D, 0, GL45.GL_RGB8, width.get(0), height.get(0), 0, GL45.GL_RGB, GL45.GL_UNSIGNED_BYTE, image);
            }else{
                System.out.println("Failure to load texture: "+filePath);
                System.exit(-1);
            }
        }

        //GL45.glBindTexture(GL45.GL_TEXTURE_2D, 0); // unbind texture
        STBImage.stbi_image_free(image); // free image
    }

    /**
     *
     */
    public void bind(){
        GL45.glBindTexture(GL45.GL_TEXTURE_2D, textureID);
    }

    /**
     *
     */
    public void unbind(){
        GL45.glBindTexture(GL45.GL_TEXTURE_2D, 0);
    }
}

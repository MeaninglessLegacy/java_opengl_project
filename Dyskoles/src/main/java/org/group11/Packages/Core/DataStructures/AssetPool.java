package org.group11.Packages.Core.DataStructures;

import org.group11.Packages.Core.Renderer.Texture;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Public storage of assets loaded from files.
 */
public class AssetPool {
    //******************************************************************************************************************
    //* storage
    //******************************************************************************************************************
    static Map<String, Texture> _textures = new HashMap();

    //******************************************************************************************************************
    //* has methods
    //******************************************************************************************************************
    /**
     * Checks if a texture exists inside the AssetPool given the URL path. Does not check if URL path is a valid path.
     * @param textureFile The path to the texture file.
     * @return Returns True if the texture exists inside the AssetPool else returns False.
     */
    public static boolean hasTexture(String textureFile){
        File file = new File(textureFile);
        if(_textures.containsKey(file.getPath())) return true;
        return false;
    }

    //******************************************************************************************************************
    //* get methods
    //******************************************************************************************************************
    /**
     * Searches the AssetPool for the texture given the URL path. Returns the texture if it exists or creates a new
     * texture if it does not exist. Does not check if URL path is a valid path.
     * @param textureFile The path to the texture file.
     * @return The Texture object from the AssetPool.
     */
    public static Texture getTexture(String textureFile){
        File file = new File(textureFile);
        if(hasTexture(textureFile)){
            return _textures.get(file.getPath());
        }else{
            Texture texture = new Texture(file.getPath());
            _textures.put(file.getPath(), texture);
            return texture;
        }
    }

}

package org.group11.Packages.Core.Main;


import org.group11.Packages.Core.DataStructures.Vector4;
import org.group11.Packages.Core.Util.Constants;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;
import org.lwjgl.opengl.GL;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.opengl.GL11C.*;

/**
 * Application window. Methods to initialize gflw, openal, and opengl.
 */
public class Window {
    //******************************************************************************************************************
    //* window object, clear color, size, aspect ratio, and title
    //******************************************************************************************************************
    private static Window _window = null;
    private int _width, _height;
    private String _title;
    private float _aspectRatio;
    private Vector4 _clearColor = new Vector4(0f, 0f, 0f, 0f); // background color

    //******************************************************************************************************************
    //* ids for initialized glfwWindow and ALC
    //******************************************************************************************************************
    private long _glfwWindow = 0L;
    private long _audioContext = 0L;
    private long _audioDevice = 0L;

    //******************************************************************************************************************
    //* other variables
    //******************************************************************************************************************
    private Scene _scene; // scene object

    /**
     * Default window constructor.
     */
    public Window(){
        this._width = Constants.SCREEN_WIDTH;
        this._height = Constants.SCREEN_HEIGHT;
        this._title = Constants.TITLE;
        this._aspectRatio = _width / _height;
    }

    //******************************************************************************************************************
    //* start and init
    //******************************************************************************************************************
    /**
     * When a new window is created call this to run the window.
     */
    public void run(){
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        _scene = Scene.get_scene(); // get shared scene object
        init();
    }

    /**
     * Creates a window and initializes GLFW, ALC, and OpenGL.
     */
    public void init(){
        // Setup error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if(!glfwInit())
            throw new IllegalStateException("Cannot initialize GLFW");
        // GLFW Configuration
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // Create Window
        _glfwWindow = glfwCreateWindow(this._width, this._height, this._title, NULL, NULL);
        if(_glfwWindow == NULL)
            throw new RuntimeException("Cannot create GLFW window");

        // Add a callback to call every time there are keyboard inputs
        glfwSetKeyCallback(_glfwWindow, (window, key, scancode, action, mods) -> {
            if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE){
                glfwSetWindowShouldClose(window, true);
            }
            if(action == GLFW_PRESS){
                // add key press event
                Scene.onKeyDown(key);
            }
            if(action == GLFW_RELEASE){
                Scene.onKeyUp(key);
            }
        });

        // Set OpenGL context current
        glfwMakeContextCurrent(_glfwWindow);
        glfwSwapInterval(1); // v-sync

        glfwShowWindow(_glfwWindow); // show this window

        // Initialize audio device
        String defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
        _audioDevice = alcOpenDevice(defaultDeviceName);
        int[] attributes = {0};
        _audioContext = alcCreateContext(_audioDevice, attributes);
        alcMakeContextCurrent(_audioContext);

        ALCCapabilities alcCapabilities = ALC.createCapabilities(_audioDevice);
        ALCapabilities alCapabilities = AL.createCapabilities(alcCapabilities);

        if(!alCapabilities.OpenAL10) {
            assert false : "Audio Library not supported.";
        }

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
        glDepthFunc(GL_LESS);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glClearColor(_clearColor.x, _clearColor.y, _clearColor.z, _clearColor.w);
    }

    //******************************************************************************************************************
    //* core
    //******************************************************************************************************************
    /**
     * Rendering loop to be called by the Engine. Polls glfw events.
     */
    public void update() {
        // poll window events
        glfwPollEvents();
        // clear the color and depth buffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        // render the current scene
        _scene.render();
        // swap the color buffers
        glfwSwapBuffers(_glfwWindow);
    }

    //******************************************************************************************************************
    //* getters and setters
    //******************************************************************************************************************
    /**
     * Returns the ID of the current glfwWindow or NULL if the window does not exist.
     * @return The window ID.
     */
    public long get_glfwWindow() {
        return _glfwWindow;
    }

    /**
     * Returns the application window or creates one if it doesn't exist.
     * @return the application window.
     */
    public static Window get_window() {
        if (Window._window == null) {
            Window._window = new Window();
        }

        return Window._window;
    }
}

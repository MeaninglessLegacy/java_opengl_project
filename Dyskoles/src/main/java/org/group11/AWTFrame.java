package org.group11;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import java.awt.*;

public class AWTFrame implements GLEventListener {

    @Override
    public void init(GLAutoDrawable obj) {

    }

    @Override
    public void display(GLAutoDrawable obj) {

    }

    @Override
    public void reshape(GLAutoDrawable obj1, int obj2, int obj3, int obj4, int obj5) {

    }

    @Override
    public void dispose(GLAutoDrawable obj) {

    }

    /*
    jogl is bugged this will cause a crash in compiled source code unless you add the jvm arguments:
    --add-exports java.base/java.lang=ALL-UNNAMED
    --add-exports java.desktop/sun.awt=ALL-UNNAMED
    --add-exports java.desktop/sun.java2d=ALL-UNNAMED
     */
    public static void main(String[] args) {
        final GLProfile gp = GLProfile.get(GLProfile.GL4);
        GLCapabilities cap = new GLCapabilities(gp);


        final GLCanvas gc = new GLCanvas(cap);
        AWTFrame af = new AWTFrame();
        //gc.addGLEventListener(af);
        gc.setSize(350, 350);

        final Frame frame = new Frame("AWT Frame");
        frame.add(gc);
        frame.setSize(400,350);
        frame.setVisible(true);
    }

}
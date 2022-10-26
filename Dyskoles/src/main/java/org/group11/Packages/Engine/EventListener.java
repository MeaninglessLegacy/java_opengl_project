package org.group11.Packages.Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Listens to inputs and calls corresponding listener functions on GameObjects
 */
public class EventListener implements KeyListener {
    //******************************************************************************************************************
    //* awt overrides
    //******************************************************************************************************************

    /**
     * Override awt keyTyped event, converts awt key to ascii value before calling events
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Override awt keyPressed event, converts awt key to ascii value before calling events
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
    }

    /**
     * Override awt keyReleased event, converts awt key to ascii value before calling events
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}


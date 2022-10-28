package org.group11.Packages.Engine;

/**
 * Main driver for the Engine package. This will run your game. Only one of these can exist at any given time.
 */
public class Engine extends Thread{
    //******************************************************************************************************************
    //* core variables
    //******************************************************************************************************************
    private static Thread t; // thread to run self on
    private static boolean runCoreLoop = true; // condition for terminating core engine loop under run()
    private final String threadName = "Engine";

    private static int UPS = 60; // target updates per second, engine prioritizes this
    private static int UPDATE_TIME_IN_MS = 1000/UPS;
    private static int FPS = 30; // target fps also number of updates per second, engine will prioritize updates
    private static int FRAME_TIME_IN_MS = 1000/FPS;
    // maximum frames to skip before rendering next frame if not reaching target updates per second
    private static int MAX_FRAME_SKIP = 2;


    //******************************************************************************************************************
    //* core
    //******************************************************************************************************************
    /**
     * Core logical loop of engine. Calls methods to update the scene then render the scene. Prioritizes performing all
     * logical operations before rendering the next scene. It does this by skipping frame rendering if the logical
     * loop has fallen behind.
     */
    @Override
    public void run() {
        long cycleStartTime = 0; // when did the current logical cycle start
        long cycleDuration = 0; // the time it took for the logical cycle to finish in ms
        int threadSleepTime = 0; // how long to sleep the thread if updates and rendering finish early
        int framesSkipped = 0; // number of frames currently skipped
        /*
        Operations:
        1.Update the scene.
        2.Render the scene.
        3.If cycleDuration > FRAME_TIME_IN_MS then skip rendering frames until update has caught up or engine has
          skipped the maximum number of frame skips.
         */
        while(runCoreLoop){
            //System.out.println("Time since last cycle in MS: " + (System.currentTimeMillis() - cycleStartTime));
            cycleStartTime = System.currentTimeMillis();
            framesSkipped = 0;
            // update & render scene
            Scene.update();
            Scene.render();
            // sleep logical thread if possible else catch up logical thread
            cycleDuration = System.currentTimeMillis() - cycleStartTime;
            threadSleepTime = (int)(FRAME_TIME_IN_MS - cycleDuration);
            if(cycleDuration < FRAME_TIME_IN_MS){
                try{
                    Thread.sleep(threadSleepTime);
                }catch (InterruptedException e){}
            }
            while(threadSleepTime < 0 && framesSkipped < MAX_FRAME_SKIP){
                // perform as many updates as threadSleepTime%FRAME_TIME_IN_MS
                // the MOD gives us how many updates where missed by updating and rendering this frame
                Scene.update();
                threadSleepTime += FRAME_TIME_IN_MS;
                framesSkipped++;
            }
        }
    }

    /**
     * Only creates and starts a new thread if a thread currently does not exist to run the engine
     */
    @Override
    public synchronized void start() {
        System.out.println("Thread " + threadName + " starting");
        runCoreLoop = true; // enable core engine loop
        if(t == null){
            t = new Thread (this, threadName);
            t.start();
        }
    }

    /**
     * Stops the core logical loop if it is running
     */
    public void stopCoreLoop(){
        runCoreLoop = false;
    }
}

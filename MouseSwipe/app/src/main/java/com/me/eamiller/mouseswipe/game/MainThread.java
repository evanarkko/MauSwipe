package com.me.eamiller.mouseswipe.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by eamiller on 9.3.2017.
 */
public class MainThread extends  Thread {
    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private Game game;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder s, Game game){
        super();
        this.surfaceHolder = s;
        this.game = game;
    }

    @Override
    public void run(){
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000/FPS;



        while(running){
            startTime = System.nanoTime();
            canvas = null;

            //try locking the canvas for pixel editing
            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.game.setCanvas(canvas);//set canvas for game so it can be updated in panel each iteration
                    this.game.advance();//make one game iteration here where updating and and drawing commands happen through game class
                }
            }
            catch (Exception e){}
            finally{
                if(canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch(Exception e){e.printStackTrace();}
                }
            }

            timeMillis = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime - timeMillis;

            try{
                this.sleep(waitTime);
            }catch(Exception e){}

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if(frameCount == FPS){
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
        }

    }


    public void setRunning(boolean r){
        this.running = r;
    }

    public boolean isRunning() {
        return running;
    }

}

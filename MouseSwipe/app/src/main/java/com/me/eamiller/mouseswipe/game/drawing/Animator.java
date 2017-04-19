package com.me.eamiller.mouseswipe.game.drawing;

import android.graphics.Bitmap;

import java.util.ArrayList;
import com.me.eamiller.mouseswipe.game.interfaces.*;

/**
 * Created by eamiller on 13.3.2017.
 */
public class Animator implements updateable{
    private Bitmap[] frames;
    private int currentIndex;

    private long startTime;
    private long delay = 100;


    public Animator(){
    }

    public void setFrames(ArrayList<Bitmap> list){
        this.frames = new Bitmap[list.size()];
        for(int i = 0; i < list.size(); i++){
            frames[i] = list.get(i);
        }
        indexCheck();
    }

    public Bitmap getImage(){
        return frames[currentIndex];
    }

    @Override
    public void update() {
        if(delay == -1) return;

        long elapsed = (System.nanoTime()-startTime)/1000000;
        if(elapsed > delay){
            currentIndex++;
            startTime = System.nanoTime();
        }
        indexCheck();
    }

    private void indexCheck(){
        if(currentIndex >= frames.length){
            currentIndex = 0;
        }
    }
}


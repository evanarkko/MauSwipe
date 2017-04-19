package com.me.eamiller.mouseswipe.game.drawing.motionEvents;

/**
 * Created by eamiller on 15.3.2017.
 */
public class SwipeMemory {
    int x1, y1;

    public SwipeMemory(){

    }

    public void setInitialCoordinates(int x, int y){
        this.x1 = x;
        this.y1 = y;
    }

    public int getX1(){
        return x1;
    }

    public int getY1() {
        return y1;
    }
}

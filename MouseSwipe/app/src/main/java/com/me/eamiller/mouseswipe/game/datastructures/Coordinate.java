package com.me.eamiller.mouseswipe.game.datastructures;

import android.content.Context;

/**
 * Created by eamiller on 23.3.2017.
 */
public class Coordinate {
    private int x, y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

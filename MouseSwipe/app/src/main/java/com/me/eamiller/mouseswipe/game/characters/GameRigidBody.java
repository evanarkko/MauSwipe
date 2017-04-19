package com.me.eamiller.mouseswipe.game.characters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.me.eamiller.mouseswipe.R;
import com.me.eamiller.mouseswipe.game.interfaces.*;

/**
 * Created by eamiller on 18.3.2017.
 */
public class GameRigidBody implements updateable, drawable {
    protected Context context;
    protected Bitmap image;
    protected int x, y, cmx, cmy; //top left corner AND center of mass coordinates
    protected int width, height;
    protected int angle;

    public GameRigidBody(Context c, int x, int y){
        this.context = c;
        this.x = x;
        this.y = y;
        setCM();
    }


    @Override
    public void draw(Canvas canvas) {

        canvas.save();
        canvas.rotate(angle, cmx, cmy);
        canvas.drawBitmap(image, (int)x, (int)y, null);
        canvas.restore();
    }

    @Override
    public void update() {//ehkä
        //animaatiota varten tms
    }

    protected void setImage(){

    }

    protected void setCM(){
        this.cmx = this.x + this.width/2;
        this.cmy = this.y + this.height/2;
    }

    public int getCmx() {
        return cmx;
    }

    public int getCmy() {
        return cmy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setX(int x) {
        this.x = x;
        setCM();
    }

    public void setY(int y) {
        this.y = y;
        setCM();
    }
}

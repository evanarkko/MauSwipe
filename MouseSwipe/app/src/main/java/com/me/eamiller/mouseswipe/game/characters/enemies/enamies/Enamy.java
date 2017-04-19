package com.me.eamiller.mouseswipe.game.characters.enemies.enamies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.me.eamiller.mouseswipe.game.characters.enemies.Enemy;
import com.me.eamiller.mouseswipe.game.characters.mouse.Mouse;
import com.me.eamiller.mouseswipe.game.drawing.GamePanel;
import com.me.eamiller.mouseswipe.game.enums.SuperpowerName;
import com.me.eamiller.mouseswipe.game.physics.Collider;

/**
 * Created by eamiller on 29.3.2017.
 */
public class Enamy extends Enemy {
    private static final int DEFAULT_ANEMY_HEIGHT = 60;
    private static final int DEFAULT_ANEMY_WIDTH = 85;
    private static final int DEFAULT_ANEMY_VELOCITY = 8;
    protected int detectingRadius = (int)(700*GamePanel.scaleFactor);
    protected Bitmap rightImage, leftImage;

    public Enamy(Context c, int x, int y, Mouse m) {
        super(c, x, y, m);
        width = DEFAULT_ANEMY_WIDTH;
        height = DEFAULT_ANEMY_HEIGHT;
        this.velocity = DEFAULT_ANEMY_VELOCITY;
    }

    @Override
    protected void setImage() {
        this.image = leftImage;
        if(this.dx > 0){
            this.image = rightImage;
        }
    }

    @Override
    public void update() {
        super.update();
        if(Collider.distanceBetweenCoordinates(this.cmx, this.cmy, preyMouse.getCmx(), preyMouse.getCmy())<detectingRadius){
            turnTowardsPrey();
        }else{
            stop();//maybe rather move a bit according to AI
        }
        setImage();
    }

    protected void turnTowardsPrey(){
        double x = preyMouse.getCmx() - this.cmx;
        double y = preyMouse.getCmy() - this.cmy;
        double radians = Math.atan(y/x);
        if(this.cmx>preyMouse.getCmx())radians+=Math.PI;

        double angleAddition = 0;
        if(preyMouse.getSuperpower()!= null){
            if(preyMouse.getSuperpower().getName()== SuperpowerName.INVINCIBLE){
                angleAddition = Math.PI;
                this.velocity = getDefalultVelocity() /2;
            }
        }
        setMovingDirection(radians+0.1+angleAddition);
        this.velocity = getDefalultVelocity();
    }

    protected void setColor(){

    }

    protected void setMovingImages(){
    }

    protected void setMovingImages(int idRight, int idLeft){
        this.rightImage = BitmapFactory.decodeResource(context.getResources(), idRight);
        this.rightImage = Bitmap.createScaledBitmap(this.rightImage, width, height, true);
        this.leftImage = BitmapFactory.decodeResource(context.getResources(), idLeft);
        this.leftImage = Bitmap.createScaledBitmap(this.leftImage, width, height, true);
    }

    protected int getDefalultVelocity() {
        return DEFAULT_ANEMY_VELOCITY;
    }
}

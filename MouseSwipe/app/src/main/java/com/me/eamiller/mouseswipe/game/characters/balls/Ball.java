package com.me.eamiller.mouseswipe.game.characters.balls;

import android.content.Context;

import com.me.eamiller.mouseswipe.game.characters.GameCharacter;

/**
 * Created by eamiller on 13.3.2017.
 */
public class Ball extends GameCharacter {
    public static final int DEFAULT_TORQUE = 12;
    public static final int DEFAULT_R = 10;

    protected int r;
    protected int torque = DEFAULT_TORQUE;


    public Ball(Context c, int x, int y) {
        super(c, x, y);
        r = Ball.DEFAULT_R;
        this.width = r*2;
        this.height = r*2;
        setImage();
    }

    @Override
    public void update() {
        super.update();
        this.angle += torque;
        if(angle > 360){
            angle -= 360;
        }
    }
}

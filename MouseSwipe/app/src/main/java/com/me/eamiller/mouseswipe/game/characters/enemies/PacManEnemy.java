package com.me.eamiller.mouseswipe.game.characters.enemies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.me.eamiller.mouseswipe.R;
import com.me.eamiller.mouseswipe.game.characters.mouse.Mouse;
import com.me.eamiller.mouseswipe.game.drawing.Animator;

import java.util.ArrayList;

/**
 * Created by eamiller on 22.3.2017.
 */
public class PacManEnemy extends Enemy {
    public static final int DEFAULT_PACMAN_R = 200;
    private static final int DEFALULT_PACMAN_VELOCITY = 4;


    public PacManEnemy(Context c, int x, int y, Mouse m) {
        super(c, x, y, m);
        setAngleBySpeedVector();
        this.width = DEFAULT_PACMAN_R;
        this.height = DEFAULT_PACMAN_R;
        this.velocity = DEFALULT_PACMAN_VELOCITY;
        createAnimator();
    }

    protected void setAngleBySpeedVector(){//important
        double radians = Math.atan(dy/dx);
        this.angle = (int)((360/(2*Math.PI))*radians);
    }


    protected void createAnimator() {
        this.animator = new Animator();
        ArrayList<Bitmap> frames = new ArrayList<>();
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_pac_man_1));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_pac_man_2));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_pac_man_3));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_pac_man_4));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_pac_man_5));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_pac_man_4));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_pac_man_3));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_pac_man_2));
        for(int i = 0; i < frames.size(); i++){
            Bitmap b = Bitmap.createScaledBitmap(frames.get(i), width, height, true);
            frames.set(i, b);
        }
        animator.setFrames(frames);
    }

    @Override
    public void update() {
        super.update();
        turnTowardsPrey();
        animator.update();
        this.image = animator.getImage();
    }

    protected void turnTowardsPrey(){
        double x = preyMouse.getCmx() - this.cmx;
        double y = preyMouse.getCmy() - this.cmy;
        double radians = Math.atan(y/x);
        if(this.cmx>preyMouse.getCmx())radians+=Math.PI;
        this.angle = (int)((360/(2*Math.PI))*radians);
        setMovingDirection(radians+0.1);
    }
}

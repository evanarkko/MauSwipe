package com.me.eamiller.mouseswipe.game.characters.balls.pointBalls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.me.eamiller.mouseswipe.R;
import com.me.eamiller.mouseswipe.game.characters.balls.Ball;
import com.me.eamiller.mouseswipe.game.drawing.Animator;
import com.me.eamiller.mouseswipe.game.drawing.GamePanel;

import java.util.ArrayList;

/**
 * Created by eamiller on 15.3.2017.
 */
public class PointBall extends Ball {
    private Animator animator;
    private int value = 1;


    public PointBall(Context c, int x, int y) {
        super(c, x, y);
        createAnimator();
    }

    private void createAnimator(){
        this.animator = new Animator();
        ArrayList<Bitmap> frames = new ArrayList<>();
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.point_ball_yellow1));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.point_ball_yellow2));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.point_ball_yellow3));
        for(int i = 0; i < frames.size(); i++){
            Bitmap b = Bitmap.createScaledBitmap(frames.get(i), width, height, true);
            frames.set(i, b);
        }
        animator.setFrames(frames);
    }

    @Override
    public void update() {
        super.update();
        animator.update();
        this.image = animator.getImage();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public int getValue() {
        return value;
    }
}

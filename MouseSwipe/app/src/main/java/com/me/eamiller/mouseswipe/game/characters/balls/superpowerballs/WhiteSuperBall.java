package com.me.eamiller.mouseswipe.game.characters.balls.superpowerballs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.me.eamiller.mouseswipe.R;
import com.me.eamiller.mouseswipe.game.superpowers.Ghost;
import com.me.eamiller.mouseswipe.game.superpowers.Superpower;

/**
 * Created by eamiller on 13.3.2017.
 */
public class WhiteSuperBall extends SuperBall {

    public WhiteSuperBall(Context c, int x, int y) {
        super(c, x, y);
    }

    @Override
    protected void setImage() {
        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball_white);
        this.image = Bitmap.createScaledBitmap(this.image, width, height, true);
    }

    @Override
    public Superpower getSuperpower() {
        return new Ghost(context);
    }
}

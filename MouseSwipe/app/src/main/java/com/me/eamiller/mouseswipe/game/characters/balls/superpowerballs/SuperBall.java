package com.me.eamiller.mouseswipe.game.characters.balls.superpowerballs;

import android.content.Context;

import com.me.eamiller.mouseswipe.game.characters.balls.Ball;
import com.me.eamiller.mouseswipe.game.superpowers.Superpower;

/**
 * Created by eamiller on 13.3.2017.
 */
public class SuperBall extends Ball {
    public static final int DEFAULT_R = 40;
    private static final int DEFAULT_SPEED = 7;
    protected Superpower superpower;

    public SuperBall(Context c, int x, int y) {
        super(c, x, y);
        r = SuperBall.DEFAULT_R;
        this.width = r*2;
        this.height = r*2;
        this.dx = DEFAULT_SPEED;
        this.dy = DEFAULT_SPEED;
        setImage();
    }

    public Superpower getSuperpower() {
        return superpower;
    }
}

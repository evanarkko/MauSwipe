package com.me.eamiller.mouseswipe.game.characters.enemies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.me.eamiller.mouseswipe.R;
import com.me.eamiller.mouseswipe.game.characters.GameCharacter;
import com.me.eamiller.mouseswipe.game.characters.mouse.Mouse;
import com.me.eamiller.mouseswipe.game.drawing.Animator;
import com.me.eamiller.mouseswipe.game.physics.Collider;

import java.util.ArrayList;

/**
 * Created by eamiller on 22.3.2017.
 */
public class Enemy extends GameCharacter {
    protected Mouse preyMouse;
    protected Animator animator;
    protected int velocity;

    public Enemy(Context c, int x, int y, Mouse m) {
        super(c, x, y);
        this.preyMouse = m;
    }

    @Override
    public void update() {
        super.update();
    }

    protected void setMovingDirection(double radians){
        this.dx = Math.cos(radians)*velocity;
        this.dy = Math.sin(radians)*velocity;
    }
}

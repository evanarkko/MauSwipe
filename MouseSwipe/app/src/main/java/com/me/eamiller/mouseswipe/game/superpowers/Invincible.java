package com.me.eamiller.mouseswipe.game.superpowers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.me.eamiller.mouseswipe.R;
import com.me.eamiller.mouseswipe.game.characters.mouse.Mouse;
import com.me.eamiller.mouseswipe.game.drawing.Animator;
import com.me.eamiller.mouseswipe.game.drawing.GamePanel;
import com.me.eamiller.mouseswipe.game.enums.SuperpowerName;

import java.util.ArrayList;

/**
 * Created by eamiller on 11.4.2017.
 */
public class Invincible extends Superpower {
    public Invincible(Context c) {
        super(c, 1.5);
        createAnimator();
        this.name = SuperpowerName.INVINCIBLE;
    }

    @Override
    protected void createAnimator() {
        int width = (int)(Mouse.defaultWidth * GamePanel.xFactor*sizeFactor);
        int height = (int)(Mouse.defaultHeight* GamePanel.yFactor*sizeFactor);
        this.animator = new Animator();
        ArrayList<Bitmap> frames = new ArrayList<>();
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.invincible_1));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.invincible_2));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.invincible_3));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.invincible_2));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.invincible_1));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.invincible_4));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.invincible_5));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.invincible_4));
        for(int i = 0; i < frames.size(); i++){
            Bitmap b = Bitmap.createScaledBitmap(frames.get(i), width, height, true);
            frames.set(i, b);
        }
        animator.setFrames(frames);
    }
}

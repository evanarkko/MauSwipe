package com.me.eamiller.mouseswipe.game.characters.enemies.enamies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.me.eamiller.mouseswipe.R;
import com.me.eamiller.mouseswipe.game.characters.mouse.Mouse;

/**
 * Created by eamiller on 14.4.2017.
 */
public class YellowEnamy extends Enamy {
    private static final int defaultVelocity = 8;
    public YellowEnamy(Context c, int x, int y, Mouse m) {
        super(c, x, y, m);
        setMovingImages(R.drawable.enamy_right_yellow, R.drawable.enamy_left_yellow);
        this.velocity = defaultVelocity;
    }

    @Override
    protected int getDefalultVelocity() {
        return defaultVelocity;
    }




}

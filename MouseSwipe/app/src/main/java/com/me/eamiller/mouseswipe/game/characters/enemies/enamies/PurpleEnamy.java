package com.me.eamiller.mouseswipe.game.characters.enemies.enamies;

import android.content.Context;

import com.me.eamiller.mouseswipe.R;
import com.me.eamiller.mouseswipe.game.characters.mouse.Mouse;

/**
 * Created by eamiller on 14.4.2017.
 */
public class PurpleEnamy extends Enamy{
    private static final int defaultVelocity = 11;
    public PurpleEnamy(Context c, int x, int y, Mouse m) {
        super(c, x, y, m);
        setMovingImages(R.drawable.enamy_right_purple, R.drawable.enamy_left_purple);
        this.velocity = defaultVelocity;
    }

    @Override
    protected int getDefalultVelocity() {
        return defaultVelocity;
    }
}

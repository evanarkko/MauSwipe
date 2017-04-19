package com.me.eamiller.mouseswipe.game.characters.characterDispensers;

import android.content.Context;

import com.me.eamiller.mouseswipe.game.characters.enemies.enamies.*;
import com.me.eamiller.mouseswipe.game.characters.mouse.Mouse;

/**
 * Created by eamiller on 15.4.2017.
 */
public class EnamyDispenser extends Dispenser{
    public EnamyDispenser(Context context) {
        super(context);
    }

    public RedEnamy dispenseRedEnamy(int x, int y, Mouse mouse){
        return new RedEnamy(context, x, y, mouse);
    }
    public PurpleEnamy dispensePurpleEnamy(int x, int y, Mouse mouse){
        return new PurpleEnamy(context, x, y, mouse);
    }
    public BlueEnamy dispenseBlueEnamy(int x, int y, Mouse mouse){
        return new BlueEnamy(context, x, y, mouse);
    }
    public GreenEnamy dispenseGreenEnamy(int x, int y, Mouse mouse){
        return new GreenEnamy(context, x, y, mouse);
    }
    public YellowEnamy dispenseYellowEnamy(int x, int y, Mouse mouse){
        return new YellowEnamy(context, x, y, mouse);
    }
}

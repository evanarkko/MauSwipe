package com.me.eamiller.mouseswipe.game.characters.characterDispensers;

import android.content.Context;

import com.me.eamiller.mouseswipe.game.characters.balls.superpowerballs.*;

/**
 * Created by eamiller on 15.4.2017.
 */
public class SuperBallDispenser extends Dispenser {
    public SuperBallDispenser(Context context) {
        super(context);
    }

    public WhiteSuperBall dispenseWhiteSuperBall(int x, int y){
        return new WhiteSuperBall(context,x ,y);
    }
    public RedSuperBall dispenseRedSuperBall(int x, int y){
        return new RedSuperBall(context,x ,y);
    }
    /*public YellowSuperBall dispenseYellowSuperBall(int x, int y){
        return new YellowSuperBall(context,x ,y);
    }*/
}

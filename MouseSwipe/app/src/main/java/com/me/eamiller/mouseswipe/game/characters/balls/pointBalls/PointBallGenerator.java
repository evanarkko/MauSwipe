package com.me.eamiller.mouseswipe.game.characters.balls.pointBalls;

import android.content.Context;

import com.me.eamiller.mouseswipe.game.drawing.GamePanel;

/**
 * Created by eamiller on 15.3.2017.
 */
public class PointBallGenerator {
    public static PointBall randomPointBall(Context c){
        double screenWidth = (GamePanel.initialScreenWidth - 50)*GamePanel.xFactor;
        double screenHeight = (GamePanel.initialScreenHeight - 50)*GamePanel.yFactor;
        int x = (int)(Math.random()*screenWidth+25);
        int y = (int)(Math.random()*screenHeight+25);
        return new PointBall(c, x, y);
    }
}

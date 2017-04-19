package com.me.eamiller.mouseswipe.game.player;

import android.content.Context;
import android.graphics.Canvas;

import com.me.eamiller.mouseswipe.game.interfaces.*;
/**
 * Created by eamiller on 15.3.2017.
 */
public class Player implements drawable {
    public static final int INITIAL_LIVES = 1;
    private PlayerScoreBar statusBar;
    private Context context;
    private int lives = INITIAL_LIVES;
    private int score = 0;

    public Player(Context c){
        statusBar = new PlayerScoreBar(this, c);
    }

    @Override
    public void draw(Canvas canvas) {
        this.statusBar.draw(canvas);
    }

    public void incScore(int increment){
        this.score += increment;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public void loseLife(){
        this.lives--;
    }

    public void resetScore(){
        this.score = 0;
    }

    public void restoreLives(){
        this.lives= INITIAL_LIVES;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void gainLife(){
        this.lives++;
    }
}

package com.me.eamiller.mouseswipe.game.superpowers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.me.eamiller.mouseswipe.R;
import com.me.eamiller.mouseswipe.game.characters.mouse.Mouse;
import com.me.eamiller.mouseswipe.game.drawing.Animator;
import com.me.eamiller.mouseswipe.game.enums.SuperpowerName;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by eamiller on 10.4.2017.
 */
public class Superpower {
    protected SuperpowerName name;
    protected Context context;
    protected Animator animator;
    protected double sizeFactor;

    public Superpower(Context c,  double sf){
        this.sizeFactor=sf;
        this.context = c;
    }

    public void resizeMouse(Mouse mouse){
        mouse.adjustSize(sizeFactor);
    }

    protected void createAnimator(){

    }

    public Animator getAnimator() {
        return animator;
    }

    public double getSizeFactor() {
        return sizeFactor;
    }

    public SuperpowerName getName() {
        return name;
    }
}

package com.me.eamiller.mouseswipe.game.drawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.me.eamiller.mouseswipe.R;

import java.util.HashMap;

/**
 * Created by eamiller on 11.4.2017.
 */
public class TimerImageHolder {
    private HashMap<Integer, Bitmap> imageHolder;

    public TimerImageHolder(Context context){
        initializeImageHolder(context);
    }

    public Bitmap getImage(int i){
        return imageHolder.get(i);
    }

    private void initializeImageHolder(Context context){
        this.imageHolder = new HashMap<>();
        imageHolder.put(16, BitmapFactory.decodeResource(context.getResources(), R.drawable.clock16));
        imageHolder.put(15, BitmapFactory.decodeResource(context.getResources(), R.drawable.clock15));
        imageHolder.put(14, BitmapFactory.decodeResource(context.getResources(), R.drawable.clock14));
        imageHolder.put(13, BitmapFactory.decodeResource(context.getResources(), R.drawable.clock13));
        imageHolder.put(12, BitmapFactory.decodeResource(context.getResources(), R.drawable.clock12));
        imageHolder.put(11, BitmapFactory.decodeResource(context.getResources(), R.drawable.clock11));
        imageHolder.put(10, BitmapFactory.decodeResource(context.getResources(), R.drawable.clock10));
        imageHolder.put(9, BitmapFactory.decodeResource(context.getResources(), R.drawable.clock9));
        imageHolder.put(8, BitmapFactory.decodeResource(context.getResources(), R.drawable.clock8));
        imageHolder.put(7, BitmapFactory.decodeResource(context.getResources(), R.drawable.clock7));
        imageHolder.put(6, BitmapFactory.decodeResource(context.getResources(), R.drawable.clock6));
        imageHolder.put(5, BitmapFactory.decodeResource(context.getResources(), R.drawable.clock5));
        imageHolder.put(4, BitmapFactory.decodeResource(context.getResources(), R.drawable.clock4));
        imageHolder.put(3, BitmapFactory.decodeResource(context.getResources(), R.drawable.clock3));
        imageHolder.put(2, BitmapFactory.decodeResource(context.getResources(), R.drawable.clock2));
        imageHolder.put(1, BitmapFactory.decodeResource(context.getResources(), R.drawable.clock1));
        imageHolder.put(0, BitmapFactory.decodeResource(context.getResources(), R.drawable.clock0));
        for(int i = 0; i < 17; i++){
            Bitmap b = Bitmap.createScaledBitmap(imageHolder.get(i), (int)(120*GamePanel.xFactor), (int)(170*GamePanel.yFactor), true);
            imageHolder.put(i, b);
        }
    }
}

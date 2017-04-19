package com.me.eamiller.mouseswipe.game.drawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.me.eamiller.mouseswipe.R;
import com.me.eamiller.mouseswipe.game.interfaces.*;

import java.util.ArrayList;

/**
 * Created by eamiller on 10.3.2017.
 */
public class Background implements updateable, drawable{
    private Context context;
    private int width, height;
    private ArrayList<Bitmap> allImages = new ArrayList<>();
    private Bitmap currentImage;



    public Background(Context context){
        this.context = context;
        width = (int)(GamePanel.initialScreenWidth*GamePanel.xFactor);
        height = (int)(GamePanel.initialScreenHeight*GamePanel.yFactor);
        addImages();
        currentImage = Bitmap.createScaledBitmap(allImages.get(0), width, height, true);
    }

    private void addImages(){
         allImages.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.background));//null => BitmapFactory.decodeResource(context.getResources(), R.drawable.background1_blue)
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(currentImage, 0, 0, null);
    }

    @Override
    public void update() {

    }
}

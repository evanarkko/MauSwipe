package com.me.eamiller.mouseswipe.game.characters.walls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.me.eamiller.mouseswipe.R;
import com.me.eamiller.mouseswipe.game.characters.GameCharacter;
import com.me.eamiller.mouseswipe.game.characters.GameRigidBody;
import com.me.eamiller.mouseswipe.game.drawing.GamePanel;
import com.me.eamiller.mouseswipe.game.enums.Alignment;

/**
 * Created by eamiller on 19.3.2017.
 */
public class Wall extends GameRigidBody {
    public static final int sideUnit = 25;

    private Alignment alignment;

    public Wall(Context c, int x, int y, int length, Alignment alignment) {
        super(c, x, y);
        this.alignment = alignment;
        setDimensions(length);
        setImage();
    }

    private void setDimensions(int length){
        if(alignment == Alignment.HORIZONTAL){
            height = (int)(sideUnit* GamePanel.yFactor);
            width = (int)(length*GamePanel.xFactor);
        }else{
            height = (int)(length*GamePanel.yFactor);
            width = (int)(sideUnit*GamePanel.xFactor);
        }
    }

    @Override
    protected void setImage() {
        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.brick);
        this.image = Bitmap.createScaledBitmap(this.image, width, height, true);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void stopGameCharacter(GameCharacter gc){

    }
}

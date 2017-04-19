package com.me.eamiller.mouseswipe.game.player;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.me.eamiller.mouseswipe.R;
import com.me.eamiller.mouseswipe.game.drawing.GamePanel;
import com.me.eamiller.mouseswipe.game.interfaces.*;
/**
 * Created by eamiller on 15.3.2017.
 */
public class PlayerScoreBar implements drawable {
    private int x, y, dx, dx2, dy, dy2;
    private Player player;
    private Paint paint;
    private Context context;
    private Bitmap lifeImage;

    public PlayerScoreBar(Player player, Context context){
        this.player = player;
        this.context = context;
        this.paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize((int)(55*GamePanel.scaleFactor));
        setCoordinates();
        setLifeImage();
    }

    private void setCoordinates(){
        this.x = (int)(10*GamePanel.xFactor);
        this.y = (int)(((GamePanel.initialScreenHeight-80)*GamePanel.yFactor));
        dx = (int)(45*GamePanel.xFactor);
        dy = (int)(50*GamePanel.yFactor);

        dx2 = (int)(145*GamePanel.xFactor);;
        dy2 = (int)(15*GamePanel.yFactor);
    }

    private void setLifeImage(){
        this.lifeImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.mouse_life);
        this.lifeImage = Bitmap.createScaledBitmap(this.lifeImage, (int)(40*GamePanel.xFactor), (int)(40*GamePanel.yFactor), true);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText("Score: " + player.getScore(), x, y, paint);
        canvas.drawText("Lives: ", x, y+dy, paint);
        drawLives(canvas);
    }

    private void drawLives(Canvas c){
        for(int i = 0; i < player.getLives(); i++){
            c.drawBitmap(lifeImage, x+dx2+i*dx, y+dy2, null);
        }
    }
}

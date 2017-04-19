package com.me.eamiller.mouseswipe.game.drawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.me.eamiller.mouseswipe.R;
import com.me.eamiller.mouseswipe.game.Game;
import com.me.eamiller.mouseswipe.game.MainThread;
import com.me.eamiller.mouseswipe.game.characters.balls.pointBalls.PointBall;
import com.me.eamiller.mouseswipe.game.characters.balls.superpowerballs.SuperBall;
import com.me.eamiller.mouseswipe.game.characters.enemies.Enemy;
import com.me.eamiller.mouseswipe.game.characters.walls.Wall;
import com.me.eamiller.mouseswipe.game.drawing.motionEvents.SwipeMemory;
import com.me.eamiller.mouseswipe.game.interfaces.drawable;
import com.me.eamiller.mouseswipe.game.interfaces.updateable;
import com.me.eamiller.mouseswipe.game.internalMemory.HighScoreManager;
import com.me.eamiller.mouseswipe.game.physics.Collider;

/**
 * Created by eamiller on 9.3.2017.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback, drawable, updateable {
    private Game game;
    private MainThread thread;
    private Background background;
    private Bitmap mouseIsDeadImage;
    private Bitmap gameOverImage;
    private Context contextToPass;
    private SwipeMemory swipeMemory = new SwipeMemory();
    private TimerImageHolder timerImageHolder;

    public static final int initialScreenHeight = 1740;
    public static final int initialScreenWidth = 1080;
    public static double yFactor;
    public static double xFactor;
    public static double scaleFactor;

    public GamePanel(Context context) {
        super(context);
        this.contextToPass = context;
        getHolder().addCallback(this);//add callBack to interpret events (finger tapping etc.)
        setFocusable(true);//make gamePanel focusable so it can handle events
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        yFactor = (1.0*getHeight()/(1.0*initialScreenHeight));
        xFactor = (1.0*getWidth()/(1.0*initialScreenWidth));
        scaleFactor = (yFactor + xFactor) / 2;


        this.game = new Game(this);
        background = new Background(contextToPass);
        this.timerImageHolder = new TimerImageHolder(contextToPass);
        setDeathImages();
        game.start();
    }

    private void setDeathImages(){
        int width = (int)(initialScreenWidth*xFactor);
        int height = (int)(initialScreenHeight*yFactor);

        this.mouseIsDeadImage = BitmapFactory.decodeResource(getResources(), R.drawable.dead_mouse );
        this.mouseIsDeadImage = Bitmap.createScaledBitmap(this.mouseIsDeadImage, width, height/2, true);

        this.gameOverImage = BitmapFactory.decodeResource(getResources(), R.drawable.gameover);
        this.gameOverImage = Bitmap.createScaledBitmap(this.gameOverImage, width, height, true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            swipeMemory.setInitialCoordinates((int)event.getX(), (int)event.getY());
            return true;
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            int x2 = (int)event.getX();
            int y2 = (int)event.getY();

            if(Collider.distanceBetweenCoordinates(swipeMemory.getX1(), swipeMemory.getY1(), x2, y2)>2){
                game.getMouse().reactToSwipe(swipeMemory.getX1(), swipeMemory.getY1(), x2, y2);
            }else{
                if(game.isPlayerDead()){
                    game.restart();
                }else{
                    if(game.isPaused()){
                        game.setPaused(false);
                    }else{
                        game.setPaused(true);
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        game.exitGame();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(game.isPlayerDead()){
            canvas.drawBitmap(mouseIsDeadImage, 0, 0, null);
            printGameOverText(canvas);
        }else{
            drawGame(canvas);
        }
    }

    private void printGameOverText(Canvas canvas){
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setTextSize((int)(55*scaleFactor));
        canvas.drawText("tap to restart",(int)(350*xFactor),(int)(1700*yFactor), p);

        if(HighScoreManager.isHighScore(game.getPlayer().getScore(), contextToPass)){
            p.setColor(Color.YELLOW);
            canvas.drawText("New High Score!",(int)(380*xFactor),(int)(1500*yFactor), p);
            p.setColor(Color.WHITE);
        }else{
            canvas.drawText("(best: " + HighScoreManager.getHighestScore(contextToPass)+")",(int)(400*xFactor),(int)(1500*yFactor), p);
        }

        p.setTextSize((int)(65*scaleFactor));
        p.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("Score: ",(int)(120*xFactor),(int)(950*yFactor), p);

        p.setTextSize((int)(300*scaleFactor));
        canvas.drawText(""+game.getPlayer().getScore(),(int)(430*xFactor),(int)(1250*yFactor), p);

    }

    private void drawGame(Canvas canvas){
        //background.draw(canvas); MAKE BETTER BG
        for(Wall w : game.getWalls()){
            w.draw(canvas);
        }
        for(PointBall pb : game.getPointBalls()){
            pb.draw(canvas);
        }

        for(SuperBall sb : game.getSuperBalls()){
            sb.draw(canvas);
        }
        game.getMouse().draw(canvas);
        for(Enemy e : game.getEnemies()){
            e.draw(canvas);
        }
        if(game.getMainEnemy()!=null){
            game.getMainEnemy().draw(canvas);
        }
        game.getPlayer().draw(canvas);
        if(game.isSuperStateActive()){
            drawClock(canvas);
        }
    }

    private void drawClock(Canvas canvas){
        int i = (int)((double)(16 * game.getSuperTimeElapsedRatio()));
        canvas.drawBitmap(timerImageHolder.getImage(i), (int)(920*GamePanel.xFactor), (int)(40*GamePanel.yFactor), null);
    }

    @Override
    public void update() {

    }

    public Context getContextToPass() {
        return contextToPass;
    }
}

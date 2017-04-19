package com.me.eamiller.mouseswipe.game.characters.mouse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.me.eamiller.mouseswipe.R;
import com.me.eamiller.mouseswipe.game.characters.GameCharacter;
import com.me.eamiller.mouseswipe.game.datastructures.Coordinate;
import com.me.eamiller.mouseswipe.game.drawing.Animator;
import com.me.eamiller.mouseswipe.game.drawing.GamePanel;
import com.me.eamiller.mouseswipe.game.superpowers.Ghost;
import com.me.eamiller.mouseswipe.game.superpowers.Superpower;

import java.util.ArrayList;

/**
 * Created by eamiller on 14.3.2017.
 */
public class Mouse extends GameCharacter {
    public static final int defaultWidth = 85, defaultHeight = 53;
    public static final int defaultVelocity = 16;
    private int velocity = defaultVelocity;
    private boolean stopped = false;
    private Animator animator;
    private Superpower superpower;




    public Mouse(Context c, int x, int y) {
        super(c, x, y);
        setToDefaultSize();
        //adjustSize(2);
        createAnimator();
        setMovingDirection();
    }



    private void createAnimator(){
        this.animator = new Animator();
        ArrayList<Bitmap> frames = new ArrayList<>();
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.mouse_1));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.mouse_2));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.mouse_3));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.mouse_2));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.mouse_1));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.mouse_4));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.mouse_5));
        frames.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.mouse_4));
        for(int i = 0; i < frames.size(); i++){
            Bitmap b = Bitmap.createScaledBitmap(frames.get(i), width, height, true);
            frames.set(i, b);
        }
        animator.setFrames(frames);
    }

    @Override
    public void update() {
        if(!stopped){
            super.update();//pelkästään tämä riippuu stopista!!
        }
        if(superpower!=null){
            superpower.getAnimator().update();
            this.image = superpower.getAnimator().getImage();
        }else{
            animator.update();
            this.image = animator.getImage();
        }
    }

    private void setMovingDirection(){
        double radians = ((2*Math.PI)/360)*angle;
        this.dx = Math.cos(radians)*velocity;
        this.dy = Math.sin(radians)*velocity;
    }

    private void setMovingDirection(double radians){
        this.dx = Math.cos(radians)*velocity;
        this.dy = Math.sin(radians)*velocity;

    }

    public void reactToSwipe(int x1, int y1, int x2, int y2){
        stopped = false;
        this.velocity = defaultVelocity;
        double x = x2 - x1;
        double y = y2 - y1;

        double radians = Math.atan(y/x);
        if(x1>x2)radians+=Math.PI;
        this.angle = (int)((360/(2*Math.PI))*radians);
        setMovingDirection(radians);
    }

    public void stop(){
        stopped = true;
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public ArrayList<Coordinate> getCollisionPointCoordinates(){
        ArrayList<Coordinate> returnThis = new ArrayList<>();
        double dx, dy;

        double distanceFromCMToNose = getWidth()/2;
        double radians = this.angle/(360/(2*Math.PI));

        dy = Math.sin(radians)*distanceFromCMToNose;
        dx = Math.cos(radians)*distanceFromCMToNose;
        returnThis.add(new Coordinate((int)(cmx+dx), (int)(cmy+dy)));//<- NOSE COORDINATE

        double radians2 = radians+0.21;//+15degrees
        double distanceToSecondPoints = distanceFromCMToNose*0.95;
        dy = Math.sin(radians2)*distanceToSecondPoints;
        dx = Math.cos(radians2)*distanceToSecondPoints;
        returnThis.add(new Coordinate((int)(cmx+dx), (int)(cmy+dy)));


        double radians3 = radians-0.21;//-15degrees
        dy = Math.sin(radians3)*distanceToSecondPoints;
        dx = Math.cos(radians3)*distanceToSecondPoints;
        returnThis.add(new Coordinate((int)(cmx+dx), (int)(cmy+dy)));

        double radians4 = radians+0.52;//+30degrees
        double distanceToThirdPoints = distanceFromCMToNose*0.9;
        dy = Math.sin(radians4)*distanceToThirdPoints;
        dx = Math.cos(radians4)*distanceToThirdPoints;
        returnThis.add(new Coordinate((int)(cmx+dx), (int)(cmy+dy)));


        double radians5 = radians-0.52;//-30degrees
        dy = Math.sin(radians5)*distanceToThirdPoints;
        dx = Math.cos(radians5)*distanceToThirdPoints;
        returnThis.add(new Coordinate((int)(cmx+dx), (int)(cmy+dy)));

        double radians6 = radians+0.7;//+40degrees
        double distanceToFourthPoints = distanceFromCMToNose*0.85;
        dy = Math.sin(radians6)*distanceToFourthPoints;
        dx = Math.cos(radians6)*distanceToFourthPoints;
        returnThis.add(new Coordinate((int)(cmx+dx), (int)(cmy+dy)));

        double radians7 = radians-0.7;//-40degrees
        dy = Math.sin(radians7)*distanceToFourthPoints;
        dx = Math.cos(radians7)*distanceToFourthPoints;
        returnThis.add(new Coordinate((int)(cmx+dx), (int)(cmy+dy)));



        return returnThis;
    }

    public double getAngleInRadians(){
        return this.angle/(360/(2*Math.PI));
    }

    public boolean isStopped() {
        return stopped;
    }

    public void adjustSize(double factor){
        this.width*=factor;
        this.height*=factor;
    }

    public void restoreFromSuperState(){
        setToDefaultSize();
        setSuperpower(null);
    }

    public void setToDefaultSize(){
        width = (int)(defaultWidth * GamePanel.xFactor);
        height = (int)(defaultHeight* GamePanel.yFactor);
    }

    public Superpower getSuperpower() {
        return superpower;
    }

    public void setSuperpower(Superpower superpower) {
        this.superpower = superpower;
        adjustSize(superpower.getSizeFactor());
    }
}

package com.me.eamiller.mouseswipe.game.characters;

import android.content.Context;
import android.graphics.Canvas;

import com.me.eamiller.mouseswipe.game.drawing.GamePanel;
import com.me.eamiller.mouseswipe.game.enums.Direction;


/**
 * Created by eamiller on 13.3.2017.
 */
public class GameCharacter extends GameRigidBody {
    protected Direction verticalDirection, horizontalDirection;
    protected double dx, dy;
    protected boolean inSuperPosition = false;
    protected int x2, y2; //when gc is in superposition
    //torque is specific for balls

    public GameCharacter(Context c, int x, int y){
        super(c, x, y);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(isPartlyOutOfSight()){
            drawRemainingPart(canvas);
        }
    }

    protected void drawRemainingPart(Canvas canvas){
        int memX = x;
        int memY = y;
        double screenWidth = GamePanel.initialScreenWidth*GamePanel.xFactor;
        double screenHeight = GamePanel.initialScreenHeight*GamePanel.yFactor;

        if(y < 0){
            this.y = (int)(screenHeight + y);
        }else if(y > screenHeight - height){
            y = (int)-(screenHeight - y);
        }
        if(x < 0){
            this.x = (int)(screenWidth + x);
        }else if(x > screenWidth - width){
            x = (int)-(screenWidth - x);
        }
        setCM();
        super.draw(canvas);
        x = memX;
        y= memY;
        setCM();
    }

    public boolean isPartlyOutOfSight(){
        double screenWidth = GamePanel.initialScreenWidth*GamePanel.xFactor;
        double screenHeight = GamePanel.initialScreenHeight*GamePanel.yFactor;
        if(x < 0 || y < 0 || x+width>screenWidth || y+height>screenHeight){
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        move();
        if(isPartlyOutOfSight()){
            setSecondaryCoordinates();//asetetaan joka iteraatiolla, joten muuttuu heti vääräksi. Fiksaa!
        }
        handlePossibleScreenExit();
        setCM();
    }

    protected void move(){
        this.x += dx;
        this.y += dy;
    }

    protected void handlePossibleScreenExit(){
        double screenWidth = GamePanel.initialScreenWidth*GamePanel.xFactor;
        double screenHeight = GamePanel.initialScreenHeight*GamePanel.yFactor;

        if((this.x > screenWidth - width) && getHorizontalDirection() == Direction.EAST){
            setX((int) -(screenWidth-x));
        }else if((this.x < 0) && getHorizontalDirection() == Direction.WEST){
            setX((int)(screenWidth + x));
        }
        if(this.y + height>screenHeight && getVerticalDirection()==Direction.SOUTH){
            setY(y=-height);
        }else if(this.y < 0 && getVerticalDirection()==Direction.NORTH){
            setY((int)(screenHeight + y));
        }
    }

    protected void setSecondaryCoordinates(){
        this.x2 = x;
        this.y2 = y;
    }

    public void stop(){
        this.dx=0;
        this.dy=0;
    }


    public void ddx(double ddx){
        this.dx+=ddx;
    }
    public void ddy(double ddy){
        this.dy+=ddy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public boolean isInSuperPosition() {
        return inSuperPosition;
    }

    public Direction getHorizontalDirection() {
        if(dx > 0){
            horizontalDirection = Direction.EAST;
        }else{
            horizontalDirection = Direction.WEST;
        }
        return horizontalDirection;
    }

    public Direction getVerticalDirection() {
        if(dy>0){
            verticalDirection = Direction.SOUTH;
        }else{
            verticalDirection = Direction.NORTH;
        }
        return verticalDirection;
    }
}

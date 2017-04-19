package com.me.eamiller.mouseswipe.game.physics;

import com.me.eamiller.mouseswipe.game.characters.GameCharacter;
import com.me.eamiller.mouseswipe.game.characters.GameRigidBody;
import com.me.eamiller.mouseswipe.game.characters.mouse.Mouse;
import com.me.eamiller.mouseswipe.game.datastructures.Coordinate;
import com.me.eamiller.mouseswipe.game.enums.Direction;

/**
 * Created by eamiller on 14.3.2017.
 */
public class Collider {
    public Collider(){

    }



    public Coordinate mouseCollide(Mouse m, GameRigidBody grb){//returns coordinate so mouse position can be fixed
        for(Coordinate c : m.getCollisionPointCoordinates()){
            if(isCoordinateInside(c, grb)){
                return c;
            }
        }
        return null;
    }

    public Direction getCollisionDirection(GameCharacter gc, GameRigidBody grb){
        Direction horizontal = null;
        if(gc.getHorizontalDirection()==Direction.EAST && gc.getCmx() <  grb.getCmx()){
            horizontal =  Direction.WEST;//tän takia
        }else if(gc.getHorizontalDirection()==Direction.WEST && gc.getCmx() > grb.getCmx()){
            horizontal = Direction.EAST;
        }

        Direction vertical = null;
        if(gc.getVerticalDirection()==Direction.NORTH && gc.getCmy() > grb.getCmy()){
            vertical = Direction.SOUTH;
        }else if(gc.getVerticalDirection()==Direction.SOUTH && gc.getCmy() < grb.getCmy()){
            vertical = Direction.NORTH;
        }

        if(horizontalDistanceFromGrb(gc.getCmx(), grb)>verticalDistanceFromGrb(gc.getCmy(), grb)){
            return horizontal;
        }else{
            return vertical;
        }
    }

    private int verticalDistanceFromGrb(int y, GameRigidBody grb){
        if(y < grb.getY()){
            return grb.getY()-y;
        }else if(y > grb.getY()+grb.getHeight()){
            return y-(grb.getX()+grb.getHeight());
        }else{
            return 0;
        }
    }

    private int horizontalDistanceFromGrb(int x, GameRigidBody grb){
        if(x < grb.getX()){
            return grb.getX()-x;
        }else if(x > grb.getX()+grb.getWidth()){
            return x-(grb.getX()+grb.getWidth());
        }else{
            return 0;
        }
    }

    public boolean isCoordinateInside(Coordinate c, GameRigidBody grb){
        if(c.getX() > grb.getX() && c.getX() < grb.getX()+grb.getWidth()){
            if(c.getY() > grb.getY() && c.getY() < grb.getY()+grb.getHeight()){
                return true;
            }
        }
        return false;
    }





    public boolean collide(GameCharacter gc, GameRigidBody grb){//NOT FINISHED
        if (gc.isPartlyOutOfSight()) {
            GameCharacter superPosGC = new GameCharacter(null, gc.getX2(), gc.getY2());
            return(collideTwoRectangles(superPosGC, grb) || collideTwoRectangles(gc, grb));
        }else{
            return collideTwoRectangles(gc, grb);
        }
    }

    public boolean collideTwoRectangles(GameRigidBody grb1, GameRigidBody grb2){ //works for non-rotating rectangles only
        return collideByWidth(grb1, grb2) & collideByHeight(grb1, grb2);
    }

    private boolean collideByWidth(GameRigidBody gc1, GameRigidBody gc2){
        GameRigidBody onLeft;
        GameRigidBody onRight;
        if(gc1.getX()<gc2.getX()){
            onLeft = gc1;
            onRight = gc2;
        }else{
            onLeft = gc2;
            onRight = gc1;
        }
        if(onRight.getX()<onLeft.getX()+onLeft.getWidth()){
            return true;
        }
        return false;
    }

    private boolean collideByHeight(GameRigidBody gc1, GameRigidBody gc2){
        GameRigidBody onTop;
        GameRigidBody onBottom;
        if(gc1.getY()<gc2.getY()){
            onTop = gc1;
            onBottom = gc2;
        }else{
            onTop = gc2;
            onBottom = gc1;
        }
        if(onBottom.getY()<onTop.getY()+onTop.getHeight()){
            return true;
        }
        return false;
    }

    public static int distanceBetweenCoordinates(int x1, int y1, int x2, int y2){
        return (int)(Math.sqrt(Math.pow(Math.abs(x1-x2), 2)+Math.pow(Math.abs(y1-y2), 2)));
    }
}

package com.me.eamiller.mouseswipe.game.Logic;

import com.me.eamiller.mouseswipe.game.Game;
import com.me.eamiller.mouseswipe.game.characters.balls.Ball;
import com.me.eamiller.mouseswipe.game.characters.balls.pointBalls.PointBall;
import com.me.eamiller.mouseswipe.game.characters.balls.pointBalls.PointBallGenerator;
import com.me.eamiller.mouseswipe.game.characters.balls.superpowerballs.SuperBall;
import com.me.eamiller.mouseswipe.game.characters.enemies.Enemy;
import com.me.eamiller.mouseswipe.game.characters.mouse.Mouse;
import com.me.eamiller.mouseswipe.game.characters.walls.Wall;
import com.me.eamiller.mouseswipe.game.datastructures.Coordinate;
import com.me.eamiller.mouseswipe.game.enums.Direction;
import com.me.eamiller.mouseswipe.game.enums.SuperpowerName;
import com.me.eamiller.mouseswipe.game.physics.Collider;
import com.me.eamiller.mouseswipe.game.player.Player;
import com.me.eamiller.mouseswipe.game.superpowers.Superpower;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by eamiller on 27.3.2017.
 */
public class GameCollisionManager {
    private Collider collider;
    private Game game;

    public GameCollisionManager(Game g){
        this.game = g;
        this.collider = new Collider();
    }

    public void manageCollisions(){
        if(game.getMouse().getSuperpower()==null){
            checkForAllCollisions();
        }else{
            Superpower superpower = game.getMouse().getSuperpower();
            checkForCollectedPoints();
            if(superpower.getName()!= SuperpowerName.GHOST && superpower.getName()!= SuperpowerName.INVINCIBLE){
                checkMainEnemyCollision();
            }else if(superpower.getName()!= SuperpowerName.GHOST){
                checkEnemyCollisions();
                checkForMouseWallCollisions();
            }
            checkForEnemyWallCollisions();
            checkForBallsColliding();
            checkForMouseSuperBallCollisions();
        }
    }

    private void checkForAllCollisions(){
        checkForCollectedPoints();
        checkForMouseEnemyCollisions();
        checkForMouseWallCollisions();
        checkForEnemyWallCollisions();
        checkForBallsColliding();
        checkForMouseSuperBallCollisions();
    }

    private void checkForEnemyWallCollisions(){//give each enemy NSEW-collision points and use them to fix position just like mouse
        for(Enemy e : game.getEnemies()){
            for(Wall w : game.getWalls()){
                if(collider.collideTwoRectangles(e, w)){
                    Direction collisionDirection = collider.getCollisionDirection(e, w);
                    //System.out.println(collisionDirection);
                    if(collisionDirection == Direction.EAST && e.getDx() < 0){
                        e.setDx(0);
                    } else if(collisionDirection == Direction.WEST && e.getDx() > 0 ){
                        e.setDx(0);
                    }else if(collisionDirection == Direction.NORTH && e.getDy() > 0){
                        e.setDy(0);
                    }else if(collisionDirection == Direction.SOUTH && e.getDy() < 0){
                        e.setDy(0);
                    }
                }
            }
        }
    }

    private void checkForMouseEnemyCollisions(){
        checkMainEnemyCollision();
        checkEnemyCollisions();
    }

    private void checkEnemyCollisions(){
        ArrayList<Integer> indexes = new ArrayList<>();
        int i = 0;

        int x1 = game.getMouse().getCmx();
        int y1 = game.getMouse().getCmy();
        for(Enemy e : game.getEnemies()){
            int x2 = e.getCmx();
            int y2 = e.getCmy();
            if(Collider.distanceBetweenCoordinates(x1, y1, x2, y2)<e.getWidth()/2){
                if(game.getMouse().getSuperpower()!=null){
                    if(game.getMouse().getSuperpower().getName()==SuperpowerName.INVINCIBLE){
                        indexes.add(i);
                    }else{
                        game.killPlayer();
                    }
                }else{
                    game.killPlayer();
                }
            }
            i++;
        }
        if(!indexes.isEmpty()){
            Collections.reverse(indexes);
            for(int j : indexes){
                game.getEnemies().remove(j);
            }
        }
    }

    private void checkMainEnemyCollision(){
        int x1 = game.getMouse().getCmx();
        int y1 = game.getMouse().getCmy();
        int x2 = game.getMainEnemy().getCmx();
        int y2 = game.getMainEnemy().getCmy();
        if(Collider.distanceBetweenCoordinates(x1, y1, x2, y2)<game.getMainEnemy().getWidth()/2){
            game.killPlayer();
        }
    }

    private void checkForBallsColliding(){//only for superballs but maybe expand later for pointballs too
        for(Ball ball : game.getSuperBalls()){
            for(Wall wall : game.getWalls()){
                if(collider.collideTwoRectangles(ball, wall)){
                    switch (collider.getCollisionDirection(ball, wall)){
                        case EAST:
                            ball.setDx(ball.getDx()*-1);
                            break;
                        case WEST:
                            ball.setDx(ball.getDx()*-1);
                            break;
                        case SOUTH:
                            ball.setDy(ball.getDy()*-1);
                            break;
                        case NORTH:
                            ball.setDy(ball.getDy()*-1);
                            break;
                    }
                }
            }
        }
    }

    private void checkForMouseWallCollisions(){
        Mouse mouse = game.getMouse();
        for(Wall wall : game.getWalls()){
            Coordinate collisionCoordinate = collider.mouseCollide(mouse, wall);
            if(collisionCoordinate != null && !mouse.isStopped()){

                double xFix = 0, yFix = 0;

                Direction collisionDirection = collider.getCollisionDirection(mouse, wall);
                System.out.println(collisionDirection);
                if(collisionDirection==Direction.EAST){
                    xFix = ((wall.getX()+wall.getWidth())-collisionCoordinate.getX());
                    yFix = (mouse.getDy()*xFix)/mouse.getDx();

                }else if(collisionDirection==Direction.WEST){
                    xFix = (wall.getX()-collisionCoordinate.getX());
                    yFix = (mouse.getDy()*xFix)/mouse.getDx();

                }else if(collisionDirection==Direction.SOUTH){
                    yFix = (wall.getY()+wall.getHeight())-collisionCoordinate.getY();
                    xFix = (mouse.getDx()*yFix)/mouse.getDy();

                }else if(collisionDirection==Direction.NORTH){
                    yFix = wall.getY()-collisionCoordinate.getY();
                    xFix = (mouse.getDx()*yFix)/mouse.getDy();
                }


                if(Math.abs(xFix)>20){//Tämä koodinpätkä on silkkaa paskaa mutta tekee tehtävänsä
                    xFix=(xFix/Math.abs(xFix))*16;
                    if(mouse.getHorizontalDirection()==Direction.WEST){
                        yFix/=Math.abs(yFix)*5;
                    }
                }


                mouse.setX(mouse.getX()+(int)xFix);
                mouse.setY(mouse.getY()+(int)yFix);

                System.out.println(xFix);
                mouse.stop();
                System.out.println("WTFFFFF");
                break;
            }
        }
    }

    private void checkForMouseSuperBallCollisions(){
        Mouse mouse = game.getMouse();
        ArrayList<Integer> indexes = new ArrayList<>();
        int i = 0;
        for(SuperBall superBall : game.getSuperBalls()){
            if(collider.collide(superBall, mouse)){
                indexes.add(i);
                mouse.setSuperpower(superBall.getSuperpower());//size adjusting happens here
                game.startSuperStateTimer();
            }
            i++;
        }
        Collections.reverse(indexes);
        for(int index : indexes){
            game.getSuperBalls().remove(index);
        }
    }

    private void checkForCollectedPoints(){
        Player player = game.getPlayer();
        Mouse mouse = game.getMouse();
        ArrayList<Integer> indexes = new ArrayList<>();
        int i = 0;
        for(PointBall pointBall : game.getPointBalls()){
            if(collider.collide(pointBall, mouse)){
                player.incScore(pointBall.getValue());
                indexes.add(i);
                game.checkIfLevelChanges();
            }
            i++;
        }
        Collections.reverse(indexes);
        for(int index : indexes){
            game.getPointBalls().remove(index);
            //Make new point balls appear
            game.getPointBalls().add(PointBallGenerator.randomPointBall(game.getContext()));

        }


    }




}

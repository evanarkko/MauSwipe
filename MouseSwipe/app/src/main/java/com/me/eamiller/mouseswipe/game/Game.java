package com.me.eamiller.mouseswipe.game;

import android.content.Context;
import android.graphics.Canvas;

import com.me.eamiller.mouseswipe.game.Logic.GameCollisionManager;
import com.me.eamiller.mouseswipe.game.Logic.LevelManager;
import com.me.eamiller.mouseswipe.game.characters.balls.superpowerballs.RedSuperBall;
import com.me.eamiller.mouseswipe.game.characters.balls.superpowerballs.WhiteSuperBall;
import com.me.eamiller.mouseswipe.game.characters.enemies.enamies.BlueEnamy;
import com.me.eamiller.mouseswipe.game.characters.enemies.enamies.Enamy;
import com.me.eamiller.mouseswipe.game.characters.enemies.Enemy;
import com.me.eamiller.mouseswipe.game.characters.enemies.PacManEnemy;
import com.me.eamiller.mouseswipe.game.characters.enemies.enamies.GreenEnamy;
import com.me.eamiller.mouseswipe.game.characters.enemies.enamies.PurpleEnamy;
import com.me.eamiller.mouseswipe.game.characters.enemies.enamies.RedEnamy;
import com.me.eamiller.mouseswipe.game.characters.enemies.enamies.YellowEnamy;
import com.me.eamiller.mouseswipe.game.enums.Alignment;
import com.me.eamiller.mouseswipe.game.interfaces.updateable;
import com.me.eamiller.mouseswipe.game.characters.balls.pointBalls.PointBall;
import com.me.eamiller.mouseswipe.game.characters.balls.pointBalls.PointBallGenerator;
import com.me.eamiller.mouseswipe.game.characters.balls.superpowerballs.SuperBall;
import com.me.eamiller.mouseswipe.game.characters.mouse.Mouse;
import com.me.eamiller.mouseswipe.game.characters.walls.Wall;
import com.me.eamiller.mouseswipe.game.drawing.GamePanel;
import com.me.eamiller.mouseswipe.game.internalMemory.HighScoreManager;
import com.me.eamiller.mouseswipe.game.player.Player;

import java.util.ArrayList;

/**
 * Created by eamiller on 9.3.2017.
 */
public class Game implements updateable {
    private GamePanel gamePanel;
    private Canvas gameCanvas;
    private MainThread thread;
    private boolean paused = false;
    private boolean playerDead = false;
    private boolean gameOver = false;
    private int cutSceneTimer = 0;
    private static final int cutSceneLimit = 50;
    private GameCollisionManager collisionManager;
    private LevelManager levelManager;
    private boolean superStateActive = false;//for gamepanel
    private int superStateTimer = 0;
    private int superStateTimeLimit = 300;

    private Player player;
    private Mouse mouse;
    private ArrayList<SuperBall> superBalls = new ArrayList<>();
    private ArrayList<PointBall> pointBalls = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();

    private PacManEnemy mainEnemy;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    //private ArrayList<Button> inGameButtons = new ArrayList<>();

    public Game(GamePanel gp){
        this.gamePanel = gp;
        this.thread = new MainThread(gp.getHolder(), this);
        player = new Player(gp.getContext());
        this.collisionManager  = new GameCollisionManager(this);
        this.levelManager = new LevelManager(this);

        setInitialCharacters();
    }

    private void setInitialCharacters(){
        mouse = new Mouse(getContext(), 10, 10);

        mainEnemy = new PacManEnemy(gamePanel.getContext(), 600, 1000, mouse);

        superBalls.add(new RedSuperBall(getContext(), 700, 1000));

        pointBalls.add(PointBallGenerator.randomPointBall(gamePanel.getContextToPass()));
        pointBalls.add(PointBallGenerator.randomPointBall(gamePanel.getContextToPass()));
        pointBalls.add(PointBallGenerator.randomPointBall(gamePanel.getContextToPass()));
        pointBalls.add(PointBallGenerator.randomPointBall(gamePanel.getContextToPass()));
        pointBalls.add(PointBallGenerator.randomPointBall(gamePanel.getContextToPass()));
        pointBalls.add(PointBallGenerator.randomPointBall(gamePanel.getContextToPass()));
        pointBalls.add(PointBallGenerator.randomPointBall(gamePanel.getContextToPass()));


        enemies.add(new YellowEnamy(getContext(), 700, 700, mouse));
        enemies.add(new GreenEnamy(getContext(), 700, 700, mouse));
        enemies.add(new BlueEnamy(getContext(), 700, 700, mouse));
        enemies.add(new PurpleEnamy(getContext(), 700, 700, mouse));
        enemies.add(new RedEnamy(getContext(), 700, 700, mouse));
    }


    public void start(){
        thread.setRunning(true);
        thread.start();
    }

    public void restart(){
        System.out.println("RESTART");
        clearAllLists();
        player.setLives(1);
        HighScoreManager.enterNewScore(player.getScore(), getContext());
        player.resetScore();
        setInitialCharacters();
        playerDead=false;
        gameOver=false;
    }

    private void clearAllLists(){
        superBalls = new ArrayList<>();
        pointBalls = new ArrayList<>();
        walls = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    public void advance(){
        if(!gameOver && !playerDead && !paused) {
            update();
            collisionManager.manageCollisions();
            if(superStateActive){
                advanceSuperStateTimer();
            }
        }

        gamePanel.draw(gameCanvas);

     }

    private void advanceSuperStateTimer(){
        superStateTimer++;
        if(superStateTimer>=superStateTimeLimit){
            superStateTimer = 0;
            superStateActive = false;
            restoreMouse();
        }
    }

    private void restoreMouse(){
        mouse.restoreFromSuperState();
    }



    @Override
    public void update(){
        mouse.update();
        if(mainEnemy!=null){
            mainEnemy.update();
        }
        for(Enemy e : enemies){
            e.update();
        }
        for(PointBall pb : this.pointBalls){
            pb.update();
        }
        for(SuperBall sb : this.superBalls){
            sb.update();
        }
    }

    public void checkIfLevelChanges(){
        levelManager.checkLevelChange();
    }

    public void killPlayer(){
        player.loseLife();
        if(player.getLives()==0){
            playerDead = true; //switchToCutImage
        }
    }

    private void relocateCharacters(){
        mouse.setX(0);
        mouse.setY(0);
        mainEnemy.setX(600);
        mainEnemy.setY(600);
    }

    public void exitGame(){
        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
            }catch (InterruptedException e){e.printStackTrace();}
            retry = false;
        }
    }

    public void startSuperStateTimer(){
        superStateActive = true;
    }

    public double getSuperTimeElapsedRatio(){
        return (1-superStateTimer/(double)superStateTimeLimit);
    }


    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setCanvas(Canvas canvas){
        this.gameCanvas = canvas;
    }

    public Mouse getMouse() {
        return mouse;
    }
    public ArrayList<SuperBall> getSuperBalls() {
        return superBalls;
    }

    public ArrayList<PointBall> getPointBalls() {
        return pointBalls;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public PacManEnemy getMainEnemy() {
        return mainEnemy;
    }

    public Context getContext(){
        return  gamePanel.getContext();
    }

    public boolean isPlayerDead() {
        return playerDead;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isSuperStateActive() {
        return superStateActive;
    }

    public void setSuperStateActive(boolean superStateActive) {
        this.superStateActive = superStateActive;
    }
}

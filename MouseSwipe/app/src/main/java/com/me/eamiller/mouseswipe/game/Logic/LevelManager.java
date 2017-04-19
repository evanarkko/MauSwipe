package com.me.eamiller.mouseswipe.game.Logic;

import com.me.eamiller.mouseswipe.game.Game;
import com.me.eamiller.mouseswipe.game.characters.walls.Wall;
import com.me.eamiller.mouseswipe.game.enums.Alignment;

/**
 * Created by eamiller on 28.3.2017.
 */
public class LevelManager {
    private Game game;

    public LevelManager(Game g){
        this.game = g;
    }

    public void checkLevelChange(){
        int score = game.getPlayer().getScore();
        switch (score){
            case 10:
                game.getWalls().clear();
                game.getWalls().add(new Wall(game.getContext(), 460, 500, 200, Alignment.HORIZONTAL));
                game.getWalls().add(new Wall(game.getContext(), 560, 1000, 200, Alignment.VERTICAL));
                break;
            case 20:
                game.getWalls().clear();
                game.getWalls().add(new Wall(game.getContext(), 300, 270, 500, Alignment.HORIZONTAL));
                game.getWalls().add(new Wall(game.getContext(), 300, 270, 500, Alignment.VERTICAL));
                game.getWalls().add(new Wall(game.getContext(), 550, 1000, 500, Alignment.HORIZONTAL));
                game.getWalls().add(new Wall(game.getContext(), 550, 1000, 500, Alignment.VERTICAL));
                break;
            case 30:
                game.getWalls().clear();
                game.getWalls().add(new Wall(game.getContext(), 250, 350, 600, Alignment.VERTICAL));
                game.getWalls().add(new Wall(game.getContext(), 550, 450, 600, Alignment.VERTICAL));
                game.getWalls().add(new Wall(game.getContext(), 850, 550, 600, Alignment.VERTICAL));
                break;
            case 40:
                game.getWalls().clear();
                game.getWalls().add(new Wall(game.getContext(), 200, 820, 700, Alignment.HORIZONTAL));
                game.getWalls().add(new Wall(game.getContext(), 550, 400, 900, Alignment.VERTICAL));
                break;
            case 50:
                game.getWalls().clear();
                game.getWalls().add(new Wall(game.getContext(), 30, 30, 400, Alignment.HORIZONTAL));
                game.getWalls().add(new Wall(game.getContext(), 30, 30, 400, Alignment.VERTICAL));

                game.getWalls().add(new Wall(game.getContext(), 60, 1680, 400, Alignment.HORIZONTAL));
                game.getWalls().add(new Wall(game.getContext(), 60, 1280, 400, Alignment.VERTICAL));

                game.getWalls().add(new Wall(game.getContext(), 560, 400, 400, Alignment.HORIZONTAL));
                game.getWalls().add(new Wall(game.getContext(), 560, 800, 400, Alignment.HORIZONTAL));
                game.getWalls().add(new Wall(game.getContext(), 560, 1200, 400, Alignment.HORIZONTAL));
                break;
            case 60:
                break;


        }
    }
}

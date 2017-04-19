package com.me.eamiller.mouseswipe.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.me.eamiller.mouseswipe.game.drawing.GamePanel;

public class GameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GamePanel gp = new GamePanel(this);
        setContentView(gp);
    }
}

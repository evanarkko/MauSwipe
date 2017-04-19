package com.me.eamiller.mouseswipe.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.me.eamiller.mouseswipe.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playGame(View view){
        Intent intent = new Intent(this, GameActivity.class);
        System.out.println("gameplaybutton pressed");
        startActivity(intent);
    }

    public void settings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void highScores(View view){
        Intent intent = new Intent(this, HighScoreActivity.class);
        startActivity(intent);
    }
}

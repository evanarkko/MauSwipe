package com.me.eamiller.mouseswipe.game.internalMemory;

import android.content.Context;
import android.content.SharedPreferences;

import com.me.eamiller.mouseswipe.R;

/**
 * Created by eamiller on 14.4.2017.
 */
public class HighScoreManager {
    public static int getHighestScore(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("preference_file", Context.MODE_PRIVATE);
        int defaultValue = 0;
        int highScore = sharedPref.getInt("highest_score", defaultValue);
        return highScore;
    }

    private static void setHighestScore(int score, Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("preference_file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("highest_score", score);
        editor.commit();
    }

    public static void enterNewScore(int score, Context context){
        if(score > getHighestScore(context)){
            setHighestScore(score, context);
        }
    }

    public static boolean isHighScore(int score, Context context){
        return score > getHighestScore(context);
    }

    public static void resetHighScore(Context context){
        setHighestScore(0,context);
    }
}

package com.ut3.bbt.viewholder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;

import com.ut3.bbt.game.GameView;
import com.ut3.bbt.R;
import com.ut3.bbt.game.Pause;

public class Opening extends Activity {

    private Button btGame;
    private Button btScore;

    private SharedPreferences sharedPreferences;
    public boolean gameStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.opening);

        gameStarted = false;

        sharedPreferences = this.getSharedPreferences("gameStarted", Context.MODE_PRIVATE);
        gameStarted = sharedPreferences.getBoolean("gameStarted",gameStarted);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("gameStarted", gameStarted);
        editor.apply();

        btGame = (Button)findViewById(R.id.btGame);
        btGame.setOnClickListener(view -> {
            setContentView(new GameView(this));
        });
        btScore = (Button)findViewById(R.id.btScore);
        btScore.setOnClickListener(view -> {
             Intent scoreIntent = new Intent(view.getContext(), Score.class);
             startActivityForResult(scoreIntent, 0);
        });
    }

    @Override
    public void onBackPressed() {
        if(gameStarted=true) {
            Intent pauseIntent = new Intent(this, Pause.class);
            startActivityForResult(pauseIntent, 1);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                if(!Boolean.parseBoolean(data.getStringExtra("result"))){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("gameStarted", false);
                    editor.apply();
                    setContentView(R.layout.opening);
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
    }
}
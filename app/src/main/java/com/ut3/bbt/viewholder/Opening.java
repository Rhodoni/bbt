package com.ut3.bbt.viewholder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import com.ut3.bbt.game.GameView;
import com.ut3.bbt.R;
import com.ut3.bbt.game.Pause;

public class Opening extends Activity {

    private Button btGame;
    private Button btScore;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private GameView gameView;
    public boolean gameRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.opening);

        SharedPreferences sharedp = this.getSharedPreferences("gameEnd",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedp.edit();
        //editor2.putInt("score",777);

        sharedPreferences = this.getSharedPreferences("game", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gameRunning = sharedPreferences.getBoolean("gameRunning", false);

        btGame = (Button)findViewById(R.id.btGame);
        btGame.setOnClickListener(view -> {
            gameRunning = true;
            editor.putBoolean("gameRunning", true);
            setContentView(gameView = new GameView(this));
        });
        btScore = (Button)findViewById(R.id.btScore);
        btScore.setOnClickListener(view -> {
             Intent scoreIntent = new Intent(view.getContext(), Score.class);
             startActivityForResult(scoreIntent, 0);
        });
    }

    @Override
    public void onBackPressed() {
        if(gameRunning = true) {
            gameView.pause();
            Intent pauseIntent = new Intent(this, Pause.class);
            startActivityForResult(pauseIntent, 1);
        }
    }

    public void endingGame(){
        Intent scoreIntent = new Intent(this, Score.class);
        finish();
        startActivity(scoreIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                if(!(data.getBooleanExtra("result",false))){
                    finish();
                    startActivity(this.getIntent());
                }else{
                    gameView.unpause();
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
    }
}
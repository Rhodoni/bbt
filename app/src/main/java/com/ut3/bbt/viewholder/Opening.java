package com.ut3.bbt.viewholder;

import android.app.Activity;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.opening);

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
        Intent pauseIntent = new Intent(this, Pause.class);
        startActivityForResult(pauseIntent, 0);

    }
}
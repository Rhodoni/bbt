package com.ut3.bbt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

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
            new Intent(view.getContext(), GameView.class);
        });
        btScore = (Button)findViewById(R.id.btScore);
        btScore.setOnClickListener(view -> {
            new Intent(view.getContext(), Score.class);
        });
    }
}
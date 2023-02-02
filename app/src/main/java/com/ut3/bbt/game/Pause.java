package com.ut3.bbt.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import com.ut3.bbt.R;
import com.ut3.bbt.viewholder.Score;

public class Pause extends Activity {
    private Button btMainMenu;
    private Button btResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.pause);

        btMainMenu = (Button)findViewById(R.id.btMainMenu);
        btMainMenu.setOnClickListener(view -> {
            setContentView(new GameView(this));
        });
        btResume = (Button)findViewById(R.id.btResume);
        btResume.setOnClickListener(view -> {
            finish();
        });
    }
}

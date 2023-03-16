package com.ut3.bbt.viewholder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import com.ut3.bbt.R;

public class Score extends Activity {
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = this.getSharedPreferences("gameEnd", Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int score = sharedPref.getInt("score",0);
        setContentView(R.layout.score);

    }
    @Override
    public void onBackPressed(){
        finish();
    }
}

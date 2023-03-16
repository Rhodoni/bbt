package com.ut3.bbt.viewholder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ut3.bbt.R;
import com.ut3.bbt.game.Pause;

public class Score extends Activity {
    SharedPreferences sharedPref;

    int scored;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPref = this.getSharedPreferences("gameEnd", Context.MODE_PRIVATE);
        //sharedPref.edit().putInt("score",77).apply();

        RecyclerView scores = (RecyclerView) findViewById(R.id.scores);
        scores.setAdapter(new ScoreAdapter(this));
        scores.setLayoutManager(new LinearLayoutManager(this));


    }
    @Override
    public void onBackPressed(){
        Intent mainAct = new Intent(this, Opening.class);
        finish();
        startActivity(mainAct);
    }

    class ScoreAdapter extends RecyclerView.Adapter{
        Context c;

        ScoreAdapter(Context c){
            this.c=c;
        }
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(new TextView(c)){

            };
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            scored = sharedPref.getInt("score",0);
            ((TextView) holder.itemView).setText("score " + scored);
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }
}

package com.ut3.bbt.game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.activity.OnBackPressedCallback;

import com.ut3.bbt.game.GameThread;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
        private Context context;
        private GameThread thread;
        private int width,height;
        private boolean gameStarted;

        public GameView(Context context) {
                super(context);
                thread = new GameThread(getHolder(), this);
                this.context = context;

                SharedPreferences sh = context.getSharedPreferences("gameStarted",Context.MODE_PRIVATE);
                gameStarted = sh.getBoolean("gameStarted",gameStarted);

                getHolder().addCallback(this);
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                width = displayMetrics.widthPixels;
                height = displayMetrics.heightPixels;
        }


        @Override
        public void surfaceCreated(SurfaceHolder holder) {
                thread.setRunning(true);
                thread.start();
        }
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
                thread.setRunning(false);
        }

        @Override
        public void draw(Canvas canvas) {
                super.draw(canvas);
                if (canvas != null) {
                        canvas.drawColor(Color.WHITE);
                }
        }
        public void update() {
                //x = (x + 1) % width;
        }
}

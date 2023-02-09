package com.ut3.bbt.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
        private Context context;
        private GameThread thread;
        private int width,height;
        private boolean gameRunning;
        private SharedPreferences sharedPreferences;
        private SharedPreferences.Editor editor;

        public GameView(Context context) {
                super(context);
                thread = new GameThread(getHolder(), this);
                this.context = context;

                sharedPreferences = context.getSharedPreferences("game", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putBoolean("gameRunning", gameRunning = true);

                getHolder().addCallback(this);
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                width = displayMetrics.widthPixels;
                height = displayMetrics.heightPixels;
        }


        @Override
        public void surfaceCreated(SurfaceHolder holder) {
                thread.setRunning(gameRunning);
                thread.start();
        }
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
                thread.setRunning(false);
        }

        public void pause(){thread.setRunning(false);
        gameRunning = false;}

        public void unpause(){thread.setRunning(true);
                thread.notify();
        gameRunning = true;}

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

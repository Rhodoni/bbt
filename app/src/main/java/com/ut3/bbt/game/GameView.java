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

import com.ut3.bbt.entities.Obstacle;
import com.ut3.bbt.entities.Player;
import com.ut3.bbt.entities.Rock;
import com.ut3.bbt.entities.Skier;
import com.ut3.bbt.entities.Tree;
import com.ut3.bbt.game.GameThread;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
        private Context context;
        private GameThread thread;
        private int width,height;
        private boolean gameStarted;

        private int scrollSpeed;
        private List<Obstacle> obstacles = new ArrayList<Obstacle>();
        private List<Skier> skiers = new ArrayList<Skier>();
        private Player player;

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

                initialiseGame();
        }

        public void initialiseGame() {
                scrollSpeed = 5;
                player = new Player(width/2, 50, 10, 1);
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

                        // Draw Game
                        obstacles.forEach(obstacle -> obstacle.draw(canvas));
                        skiers.forEach(skier -> skier.draw(canvas));
                        player.draw(canvas);
                }
        }

        public void pause (){
                thread.setRunning(false);
        }
        public void unpause(){
                thread.setRunning(true);
        }
        public void update() {
                if (Math.random() * 100 < 1) {
                        createEntity();
                }

                // Check collisions
                //skiers.forEach(skier -> obstacles.forEach(skier::checkCollision));
                skiers.forEach(skier -> skier.checkCollision(player));
                skiers.forEach(player::checkCollision);

                // Update
                skiers.forEach(Skier::update);
                player.update();

                // Scroll
                obstacles.forEach(obstacle -> obstacle.scroll(scrollSpeed));
                skiers.forEach(skier -> skier.scroll(scrollSpeed));

                // Clean entities
                cleanEntities();
        }

        private void createEntity() {
                double random = Math.random() * (10);
                if (random < 4) {
                        obstacles.add(new Rock(Math.random() * width, height));
                } else if (random < 8) {
                        obstacles.add(new Tree(Math.random() * width, height));
                } else if (random < 10) {
                        skiers.add(new Skier(Math.random() * width, height, Math.random() * 5 + 5));
                }
        }

        private void cleanEntities() {
                obstacles.removeIf(obstacle -> !Utils.isInScreen(obstacle, width, height  + obstacle.getHeight()));
                skiers.removeIf(skier -> !Utils.isInScreen(skier, width, height + skier.getHeight()));
        }
}

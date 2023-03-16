package com.ut3.bbt.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;

import com.ut3.bbt.entities.Obstacle;
import com.ut3.bbt.entities.Player;
import com.ut3.bbt.entities.Rock;
import com.ut3.bbt.entities.Skier;
import com.ut3.bbt.entities.Tree;
import com.ut3.bbt.entities.Wall;
import com.ut3.bbt.game.GameThread;
import com.ut3.bbt.viewholder.Opening;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
        private Context context;
        private GameThread thread;
        private int width,height;
        private CaptorActivity captorActivity;

        private boolean gameStarted;
        private boolean gameEnd;
        SharedPreferences sharedp;

        private int scrollSpeed;
        private List<Obstacle> obstacles = new ArrayList<Obstacle>();
        private List<Skier> skiers = new ArrayList<Skier>();
        private List<Wall> walls = new ArrayList<>();
        private Player player;
        private int score = 0;
        private TextView scoreview;

        //SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        public GameView(Context context) {
                super(context);
                thread = new GameThread(getHolder(), this);
                this.context = context;

                SharedPreferences sh = context.getSharedPreferences("gameStarted",Context.MODE_PRIVATE);
                gameStarted = sh.getBoolean("gameStarted",gameStarted);

                sharedp = context.getSharedPreferences("gameEnd",Context.MODE_PRIVATE);

                getHolder().addCallback(this);
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                width = displayMetrics.widthPixels;
                height = displayMetrics.heightPixels;
                this.captorActivity = new CaptorActivity();
                this.captorActivity.setUpSensors(this.context);
                initialiseGame();
        }

        public void initialiseGame() {
                int margin = 50;
                scrollSpeed = 5;
                player = new Player(width/2, 50, 10, 1,context);
                scoreview = new TextView(context);
                scoreview.setText(("score"));
                walls.add(new Wall(margin, 0, 50, height));
                walls.add(new Wall(width - margin * 2, 0, margin, height));

                skiers.add(new Skier(Math.random() * width, height, Math.random() * 2 + 2,context));
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
                        walls.get(0).draw2(canvas);
                        walls.get(1).draw2(canvas);
                }
        }

        public void pause (){
                thread.setRunning(false);
        }
        public void unpause(){
                thread.setRunning(true);
        }

        public void endGame(){

                SharedPreferences sharedp = context.getSharedPreferences("gameEnd",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedp.edit();
                editor.putInt("score",score).apply();
                ((Opening)context).endingGame();
        }

        public void update() {
                // Update level
                score += 1;
                updateDifficulty();

                if (player.isDead){
                        endGame();
                }

                if (Math.random() * 100 < 1) {
                        createEntity();
                }

                // Check collisions
                skiers.forEach(skier -> obstacles.forEach(skier::checkCollision));
                skiers.forEach(skier -> skier.checkCollision(player));
                skiers.forEach(player::checkCollision);
                skiers.forEach(skier -> {
                        skier.checkCollision(walls.get(0));
                        skier.checkCollision(walls.get(1));
                });

                // Update entities
                skiers.forEach(Skier::update);
                if (captorActivity.isJumping) {
                        player.jump();
                }
                player.setAcceleration(captorActivity.playerAcceleration);
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
                        obstacles.add(new Rock(Math.random() * width, height, context));
                } else if (random < 8) {
                        obstacles.add(new Tree(Math.random() * width, height,context));
                } else if (random < 10) {
                        skiers.add(new Skier(Math.random() * width, height / 2, Math.random() * 2 + 2, context));
                }
        }

        private void updateDifficulty() {
                this.scrollSpeed = (int) Math.round(10 + Math.sqrt(this.score) / 100);
        }

        private void cleanEntities() {
                obstacles.removeIf(obstacle -> !Utils.isInScreen(obstacle, width, height  + obstacle.getHeight()));
                skiers.removeIf(skier -> !Utils.isInGame(skier, width, height + skier.getHeight()));
        }
}

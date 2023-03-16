package com.ut3.bbt.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.ut3.bbt.R;
import com.ut3.bbt.entities.Entity;
import com.ut3.bbt.entities.Obstacle;
import com.ut3.bbt.entities.Player;
import com.ut3.bbt.entities.Rock;
import com.ut3.bbt.entities.Skier;
import com.ut3.bbt.entities.Tree;
import com.ut3.bbt.entities.Wall;
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

        private final int baseScrollSpeed = 15;
        private int scrollSpeed = baseScrollSpeed;
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
                // Initialise texte
                scoreview = new TextView(context);
                scoreview.setText(("score"));

                // Initiate walls
                int wallWidth = BitmapFactory.decodeResource(context.getResources(), R.drawable.snow_left).getWidth();
                int wallHeight = BitmapFactory.decodeResource(context.getResources(), R.drawable.snow_left).getHeight();
                Wall wallLeft = new Wall(0, 0, wallWidth, height);
                Wall wallRight = new Wall(width - wallWidth, 0, wallWidth, height);
                wallLeft.setBmp(BitmapFactory.decodeResource(context.getResources(), R.drawable.snow_left));
                wallRight.setBmp(BitmapFactory.decodeResource(context.getResources(), R.drawable.snow_right));

                walls.add(wallLeft);
                walls.add(wallRight);

                // Initialiser player
                player = new Player(width/2, 50, 10, 1, wallWidth/2, width - wallWidth/2,  context);

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
                        walls.get(0).draw(canvas);
                        walls.get(1).draw(canvas);

                        // Dessiner entités dans la bonne profondeur
                        List<Entity> entities = new ArrayList<Entity>();
                        entities.addAll(obstacles);
                        entities.addAll(skiers);
                        entities.add(player);
                        entities.sort((e1, e2) -> (int) (e1.getY() + e1.getHitBox().x + e1.getHitBox().height - (e2.getY() + e2.getHitBox().x + e2.getHitBox().height)));
                        entities.forEach(entity -> entity.draw(canvas));

                        // Fog filter
                        Paint opacityPaint = new Paint();
                        opacityPaint.setColor(Color.WHITE);
                        opacityPaint.setAlpha((int) Math.max(0, 200 - 200 * captorActivity.lightFactor));
                        canvas.drawRect(0, 0, width, height, opacityPaint);

                        // Draw score
                        Paint paint = new Paint();
                        paint.setTextSize(50);
                        canvas.drawText(String.valueOf(score),width/2,100, paint);
                }
        }

        public void pause (){
                thread.setRunning(false);
        }
        public void unpause(){
                thread.setRunning(true);
        }

        public void endGame() {
                thread.setRunning(false);
                SharedPreferences sharedp = context.getSharedPreferences("gameEnd",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedp.edit();
                editor.putInt("score",score).apply();
                ((Opening)context).endingGame();
        }

        public void update() {
                // Update level
                score += Math.max(1, scrollSpeed / 10);
                updateDifficulty();

                if (player.isDead) {
                        endGame();
                }

                if (Math.random() * 100 < 1 || obstacles.size() + skiers.size() < 3) {
                        createEntity();
                }

                // Check collisions
                skiers.forEach(skier -> obstacles.forEach(skier::checkCollision));
                //skiers.forEach(skier -> skier.checkCollision(player));
                skiers.forEach(player::checkCollision);
                obstacles.forEach(player::checkCollision);
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
                walls.forEach(wall -> wall.scroll(scrollSpeed));
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
                        skiers.add(new Skier(Math.random() * width, height, Math.random() * 2 + 2, context));
                }
        }

        private void updateDifficulty() {
                // Vitesse de scroll dépendante du score
                double scoreSpeed = (double) this.score / (this.score + 1000) * 20;

                // Vitesse de scroll dépendante de la lumière
                this.scrollSpeed = baseScrollSpeed / 2 + (int) Math.round((baseScrollSpeed/2 + scoreSpeed) * captorActivity.lightFactor);
        }

        private void cleanEntities() {
                obstacles.removeIf(obstacle -> !Utils.isInScreen(obstacle, width, height  + obstacle.getHeight()));
                skiers.removeIf(skier -> !Utils.isInGame(skier, width, height + skier.getHeight()));
        }
}

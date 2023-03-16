package com.ut3.bbt.entities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;

import com.ut3.bbt.R;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Player extends Movable {

    boolean jumping = false;
    public boolean isDead = false;
    private final Context context;

    public Player(double x, double y, double maxSpeed, double speed, Context context) {
        super(
            x,
            y,
            BitmapFactory.decodeResource(context.getResources(), R.drawable.ops).getWidth(),
            BitmapFactory.decodeResource(context.getResources(), R.drawable.ops).getHeight(),
            maxSpeed
        );

        this.context = context;
        this.bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.skier);
        paint.setColor(Color.BLUE);

        this.hitBox = new CollideBox(width / 4, 0, width / 2, height);
        this.hurtBox = this.hitBox;
    }

    public void jump() {
        jumping = true;
        this.bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.jumping);
        Executors.newSingleThreadScheduledExecutor()
                .schedule(landAfterJumpRunnable,1500, TimeUnit.MILLISECONDS);
    }

    public Runnable landAfterJumpRunnable = new Runnable() {
        @Override
        public void run() {
            jumping = false;
            bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.skier);
        }
    };

    public void scream() {

    }

    public void die(){
        isDead = true;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void collision(Entity entity) {
        System.out.println("PLAYER COLLITION");
        if (entity instanceof Obstacle) {
            Obstacle obstacle = (Obstacle) entity;
            if (!obstacle.isJumpable() || obstacle.isJumpable() && !jumping) {
                System.out.println("DEAD");
                die();
            }
        } else if (entity instanceof Skier) {
            die();
        }
    }
}

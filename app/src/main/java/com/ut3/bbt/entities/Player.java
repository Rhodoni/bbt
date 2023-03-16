package com.ut3.bbt.entities;

import android.graphics.Color;
import android.os.Handler;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Player extends Movable {

    boolean jumping = false;
    public boolean isDead = false;

    public Player(double x, double y, double maxSpeed, double speed) {
        super(x, y, maxSpeed);

        paint.setColor(Color.BLUE);
    }

    public void jump() {
        jumping = true;
        paint.setColor(Color.CYAN);
        Executors.newSingleThreadScheduledExecutor()
                .schedule(landAfterJumpRunnable,500, TimeUnit.MILLISECONDS);
    }

    public Runnable landAfterJumpRunnable = new Runnable() {
        @Override
        public void run() {
            jumping = false;
            paint.setColor(Color.BLUE);
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
            if (!obstacle.isJumpable() && jumping) {
                System.out.println("DEAD");
                die();
            }
        } else if (entity instanceof Skier) {
            die();
        }
    }
}

package com.ut3.bbt.entities;

import android.graphics.Color;
import android.os.Handler;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Player extends Movable {

    boolean jumping = false;

    public Player(double x, double y, double maxSpeed, double speed) {
        super(x, y, maxSpeed);
        hitBox = new CollideBox(0, 0, 5, 10);

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

    @Override
    public void update() {
    }

    @Override
    public void collision(Entity entity) {
        if (entity instanceof Obstacle) {
            if( ! ((Obstacle) entity).isJumpable() && jumping){
                die();
            }
        }
    }
}

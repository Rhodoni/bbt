package com.ut3.bbt.entities;

import android.graphics.Color;
import android.os.Handler;

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
        Handler handler = new Handler();
        handler.postDelayed(landAfterJumpRunnable, 5000);
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
        super.update();

        if (Math.random() * 20 < 1) {
            turn();
        }
    }

    @Override
    public void collision(Entity entity) {
        if (entity instanceof Obstacle) {
            if (!jumping) {
                die();
            }
        } else {
            die();
        }
    }
}

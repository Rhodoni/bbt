package com.ut3.bbt.entities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.DisplayMetrics;

import com.ut3.bbt.R;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Player extends Movable {

    boolean jumping = false;
    public boolean isDead = false;
    private final Context context;
    private final double borderLeft, borderRight;

    public Player(double x, double y, double maxSpeed, double speed, double borderLeft, double borderRight, Context context) {
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

        this.borderRight = borderRight;
        this.borderLeft = borderLeft;

        this.hitBox = new CollideBox(width / 4, height * 3 / 4, width / 2, height / 4);
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

    public void updateSpeed() {
        double newSpeed = speed + acceleration;
        speed = Math.max(Math.min(maxSpeed, newSpeed), -maxSpeed);
    }

    @Override
    public void updatePosition() {
        if (borderLeft < x + speed && x + width + speed < borderRight) {
            super.updatePosition();
        }
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

package com.ut3.bbt.entities;

import android.graphics.Color;

public class Player extends Movable {


    public Player(double x, double y, double maxSpeed, double speed) {
        super(x, y, maxSpeed);
        hitBox = new CollideBox(0, 0, 5, 10);

        paint.setColor(Color.BLUE);
    }

    public void jump() {

    }

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
        die();
    }
}

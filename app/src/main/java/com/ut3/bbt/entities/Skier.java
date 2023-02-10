package com.ut3.bbt.entities;

import android.graphics.Color;
import android.graphics.Paint;

public class Skier extends Movable implements Scrollable {


    public Skier(double x, double y, double maxSpeed) {
        super(x, y, maxSpeed);
        hitBox = new CollideBox(0, 0, 5, 10);

        paint.setColor(Color.RED);
    }

    public void goAway(Entity entity) {
        if (x < entity.x) {

        }
    }

    @Override
    public void update() {
        super.update();

        if (maxSpeed / 2 < Math.abs(speed) && Math.random() * maxSpeed * 2 < Math.abs(speed)) {
            turn();
        }
    }

    @Override
    public void scroll(int speed) {
        y -= speed * 0.5;
    }

    @Override
    public void collision(Entity entity) {
        if (entity instanceof Obstacle) {
            turn();
        }
    }
}

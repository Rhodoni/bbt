package com.ut3.bbt.entities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;

import com.ut3.bbt.R;

public class Skier extends Movable implements Scrollable {


    public Skier(double x, double y, double maxSpeed, Context context) {
        super(x, y, maxSpeed);
        paint.setColor(Color.RED);
        this.bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ops);
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
        if (entity instanceof Wall) {
            turn();
        } else if (entity instanceof Obstacle) {
            turn();
        }
    }
}

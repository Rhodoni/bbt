package com.ut3.bbt.entities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;

import com.ut3.bbt.R;

public class Skier extends Movable implements Scrollable {


    public Skier(double x, double y, double maxSpeed, Context context) {
        super(
            x,
            y,
            BitmapFactory.decodeResource(context.getResources(), R.drawable.ops).getWidth(),
            BitmapFactory.decodeResource(context.getResources(), R.drawable.ops).getHeight(),
            maxSpeed
        );

        this.bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ops);

        paint.setColor(Color.RED);

        this.hitBox = new CollideBox(width / 4, height * 3 / 4, width / 2, height / 4);
        this.hurtBox = new CollideBox( -width / 2, 0, width * 2, height * 2);
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
            if (entity.x <= 0 && acceleration < 0 || entity.x > 0 && acceleration > 0) {
                turn();
            }
        } else if (entity instanceof Obstacle) {
            if (x < entity.x && acceleration > 0 || x > entity.x && acceleration < 0) {
                turn();
            }

        }
    }
}

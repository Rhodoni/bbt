package com.ut3.bbt.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public abstract class Entity {
    public double x;
    public double y;

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public CollideBox hitBox;

    public abstract void update();

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawRect((float) x, (float) y, (float) x + 50, (float) y + 50, paint);
    }
}

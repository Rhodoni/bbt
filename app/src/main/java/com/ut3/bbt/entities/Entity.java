package com.ut3.bbt.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public abstract class Entity {
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected CollideBox hitBox;
    protected Paint paint;

    public Entity(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        paint = new Paint();
    }

    public abstract void update();

    public void draw(Canvas canvas) {
        canvas.drawRect((float) x, (float) y, (float) (x + width), (float) (y + height), paint);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}

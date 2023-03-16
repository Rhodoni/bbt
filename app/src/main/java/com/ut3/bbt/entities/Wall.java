package com.ut3.bbt.entities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.ut3.bbt.R;

public class Wall extends Obstacle {
    double offset = 0;

    public Wall(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void scroll(int speed) {
        offset = (offset - speed) % height;
    }

    @Override
    public void draw(Canvas canvas) {
        double y = offset;
        while (y < canvas.getHeight()) {
            canvas.drawBitmap(bmp, (float) x, (float) y, paint);

            y += this.bmp.getHeight();
        }

    }

    @Override
    public boolean isJumpable() {
        return false;
    }
}

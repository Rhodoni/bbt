package com.ut3.bbt.entities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.ut3.bbt.R;

public class Rock extends Obstacle {
    public Rock(double x, double y, Context context) {
        super(
            x,
            y,
            BitmapFactory.decodeResource(context.getResources(), R.drawable.ops).getWidth(),
            BitmapFactory.decodeResource(context.getResources(), R.drawable.ops).getHeight()
        );

        paint.setColor(Color.GRAY);

        this.bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.rocks);

        this.hitBox = new CollideBox(width * 3 / 8, height * 3 / 8, width / 4, height / 4);
    }

    @Override
    public boolean isJumpable() {
        return true;
    }
}

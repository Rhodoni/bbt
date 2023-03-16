package com.ut3.bbt.entities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.ut3.bbt.R;

public class Rock extends Obstacle {
    public Rock(double x, double y, Context context) {
        super(x, y, 50, 50);
        paint.setColor(Color.GRAY);

        this.bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.rocks);
    }

    @Override
    public boolean isJumpable() {
        return true;
    }
}

package com.ut3.bbt.entities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.ut3.bbt.R;

public class Tree extends Obstacle {
    public Tree(double x, double y, Context context) {
        super(x, y, 50, 150);
        this.bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.tree);

        paint.setColor(Color.GREEN);
    }

    @Override
    public boolean isJumpable() {
        return false;
    }
}

package com.ut3.bbt.entities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.ut3.bbt.R;

public class Tree extends Obstacle {
    public Tree(double x, double y, Context context) {
        super(
            x,
            y,
            BitmapFactory.decodeResource(context.getResources(), R.drawable.ops).getWidth(),
            BitmapFactory.decodeResource(context.getResources(), R.drawable.ops).getHeight()
        );

        this.bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.tree);

        paint.setColor(Color.GREEN);

        this.hitBox = new CollideBox(width * 3 / 8, height / 2, width / 4, height / 4);
    }

    @Override
    public boolean isJumpable() {
        return false;
    }
}

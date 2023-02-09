package com.ut3.bbt.entities;

public abstract class Entity {
    public int x;
    public int y;

    public CollideBox hitBox;

    public abstract void update();
}

package com.ut3.bbt.entities;

public class Skier extends Movable implements Scrollable {

    public Skier(float speed) {
        super(10, 0);
    }

    public void goAway(Entity entity) {
        if (x < entity.x) {

        }
    }



    @Override
    public void scroll(int speed) {
        y += speed * 0.8;
    }

    @Override
    public void collision(Entity entity) {
        if (entity instanceof Obstacle) {
            turn();
        }
    }
}

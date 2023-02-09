package com.ut3.bbt.entities;

public class Skier extends Movable implements Scrollable {


    public Skier(double x, double y, double maxSpeed) {
        super(x, y, maxSpeed);
    }

    public void goAway(Entity entity) {
        if (x < entity.x) {

        }
    }



    @Override
    public void scroll(int speed) {
        y -= speed * 0.8;
    }

    @Override
    public void collision(Entity entity) {
        if (entity instanceof Obstacle) {
            turn();
        }
    }
}

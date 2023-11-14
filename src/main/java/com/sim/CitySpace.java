package com.sim;

public class CitySpace extends Space {
    int x, y;

    CitySpace(String name, int x, int y) {
        super(name);
        this.x = x;
        this.y = y;
    }

    @Override
    public void welcome() {
        System.out.println("You are in " + name);
    }
}

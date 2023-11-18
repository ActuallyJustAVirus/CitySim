package com.sim;

public class CitySpace extends Space {
    int x, y;

    CitySpace(String name, int x, int y) {
        super(name);
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public void welcome() {
        System.out.println("You are in " + name);
    }
}

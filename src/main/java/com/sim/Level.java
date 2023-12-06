package com.sim;

public class Level {
    int levelNumber;
    String description;

    public Level(int levelNumber, String description){
        this.levelNumber = levelNumber;
        this.description = description;
    }

    public int getLevelNumber(){
        return levelNumber;
    }

    public String getDescription(){
        return description;
    }
}

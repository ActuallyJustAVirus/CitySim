package com.sim;

public class Level {
    int levelNumber;
    Item item;
    String description;
    Space space;
    String itemDescription;

    public Level(Level level){
        this.levelNumber = level.getLevelNumber();
        this.item = level.getLevelItem();
        this.description = level.getDescription();
        this.space = level.getSpace();
        this.itemDescription = level.getItemDescription();
    }

    public Level(int levelNumber, Item item, String description, Space space, String itemDescription){
        this.levelNumber = levelNumber;
        this.item = item;
        this.description = description;
        this.space = space;
        this.itemDescription = itemDescription;
    }

    public int getLevelNumber(){
        return levelNumber;
    }

    public Item getLevelItem(){
        return item;
    }

    public String getDescription(){
        return description;
    }

    public Space getSpace(){
        return space;
    }

    public String getItemDescription(){
        return itemDescription;
    }
}

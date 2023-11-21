package com.sim;
import java.util.ArrayList;

public class Inventory{
    public ArrayList<Item> objects = new ArrayList<>();
    private boolean open;
    private String openMessage;

    public void addItem(Item item) {
        objects.add(item);
        System.out.println(item.getName() + " added to the inventory.");
    }

    public Item getItem(String name){
        for(Item currentItem : objects){
            if(currentItem.getName().equals(name)){
                return currentItem;
            }
        }
        return null;
    }
}
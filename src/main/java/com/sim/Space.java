package com.sim;
/* Space class for modeling spaces (rooms, caves, ...)
 */

import java.util.Set;
import java.util.ArrayList;

public class Space extends Node {
    private ArrayList<Item> worldItems = new ArrayList<>();
    Space(String name) {
        super(name);
    }


    public void welcome() {
        System.out.println("You are now at " + name);
        Set<String> exits = edges.keySet();
        System.out.println("Current exits are:");
        for (String exit : exits) {
            System.out.println(" - " + exit);
        }
    }
    public void addItem(Item item) {
        worldItems.add(item);
    }

    public void removeItem(Item item) {
        worldItems.remove(item);
    }

    public Item getItem(String itemName) {
        for (Item item : worldItems) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
    public void goodbye() {
    }

    @Override
    public Space followEdge(String direction) {
        return (Space) (super.followEdge(direction));
    }

}

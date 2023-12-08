package com.sim;

import java.util.ArrayList;

public class Tutorial {
    public ArrayList<Level> levels = new ArrayList<>();

    World world = new World();

    Level level1 = new Level(0,world.item1,"To begin helping the people of Zamoridia, " +
            "\nyou need to collect some tools. Start by going " +
            "\nto the capital city, Aurelia.",world.spaces.get(0),
            "You now have a filing cabinet, and can access relevant " +
                    "\ninformation about each city. Each city's file " +
                    "\ncan be accessed by clicking the \"Info\" button, " +
                    "\nthat appears when you select a new city.");
    Level level2 = new Level(1, world.item2,"In order to start building roads, you're gonna " +
            "\nneed a steamroller. A brand new one just \narrived in Verdania, one of Zamoridia's " +
            "\nsmaller cities. ",world.spaces.get(3),
            "You now have a steamroller, which enables you to pave " +
                    "\nnew roads between the cities of Zamoridia. " +
                    "\nTo begin building a road, click the \"Build\" button, " +
                    "\nthat appears in the top left, when you select a new city.");

    Level level3 = new Level(2, world.item3,"Lastly, you're gonna need a crane. This will " +
            "\nenable you to build bridges over bodies " +
            "\n of water. Go to Zephyria to collect one.",world.spaces.get(7),
            "Now that you have a crane, you have the ability to build " +
                    "\nbridges over bodies of water. While this is more " +
                    "\nexpensive, it can be advantageous in certain situations.");

    public Tutorial(){
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
    }


}

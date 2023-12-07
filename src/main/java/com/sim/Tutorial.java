package com.sim;

import java.util.ArrayList;

public class Tutorial {
    public ArrayList<Level> levels = new ArrayList<>();

    World world = new World();

    Level level1 = new Level(0,world.item1,"To begin building roads for the people of " +
            "\nZamoridia, you need to collect some tools. " +
            "\nStart by going to the capital city, Aurelia.",world.spaces.get(0));
    Level level2 = new Level(1, world.item2,"You now have access to a filing cabinet, " +
            "\nand have access to relevant information about " +
            "\neach city. In order to start building roads, " +
            "\nyou're gonna need a steamroller. A brand new " +
            "\none just arrived in Verdania, one of Zamoridia's " +
            "\nsmaller cities. ",world.spaces.get(3));

    Level level3 = new Level(2, world.item3,"tutorial nr 3",world.spaces.get(2));

    public Tutorial(){
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
    }


}

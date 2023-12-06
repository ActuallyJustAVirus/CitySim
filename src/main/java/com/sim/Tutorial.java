package com.sim;

import java.util.ArrayList;

public class Tutorial {
    public ArrayList<Level> levels = new ArrayList<>();

    Level level1 = new Level(0,"To begin building roads for the people of " +
            "\nZamoridia, you need to collect some tools. " +
            "\nStart by going to the capital city, Aurelia.");
    Level level2 = new Level(1, "You now have access to a filing cabinet, " +
            "\nand have access to relevant information about " +
            "\neach city. In order to start building roads, " +
            "\nyou're gonna need a steamroller. A brand new " +
            "\none just arrived in Verdania, one of Zamoridia's " +
            "\nsmaller cities. ");

    Level level3 = new Level(2, "tutorial nr 3");

    public Tutorial(){
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
    }


}

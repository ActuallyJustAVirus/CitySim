package game;

import java.util.ArrayList;

public class SpaceCity extends Space {
    public ArrayList<SpaceCity> neighbors = new ArrayList<SpaceCity>();

    SpaceCity(String name) {
        super(name);
    }

    @Override
    public void welcome() {
        System.out.println("You are in " + name);
    }
}

package game;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class SpaceCity extends Space {
    public ArrayList<SpaceCity> neighbors = new ArrayList<SpaceCity>();
    int x, y;

    SpaceCity(String name, int x, int y) {
        super(name);
        this.x = x;
        this.y = y;
    }

    @Override
    public void welcome() {
        System.out.println("You are in " + name);
    }
}

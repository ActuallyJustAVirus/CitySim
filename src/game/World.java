package game;
/* World class for modeling the entire in-game world
 */

import java.util.ArrayList;

class World {
    Space entry;
    ArrayList<Space> spaces = new ArrayList<Space>();

    World() {
        Space map = new SpaceMap(this);
        spaces.add(new SpaceCity("Capital", 3, 4));
        spaces.add(new SpaceCity("City", 2, 3));
        spaces.add(new SpaceCity("Town", 1, 2));
        spaces.add(new SpaceCity("Village", 0, 1));
        spaces.add(new SpaceCity("Hamlet", 0, 0));
        spaces.add(new SpaceCity("Locality", 1, 1));

        for (Space space : spaces) {
            map.addEdge(space.getName(), space);
        }

        for (Space space : spaces) {
            space.addEdge("Map", map);
        }

        this.entry = map;
    }

    Space getEntry() {
        return entry;
    }

    void printMap() {
        System.out.println("Map:");
        System.out.println("Capital");
        System.out.println("City");
        System.out.println("Town");
    }
}

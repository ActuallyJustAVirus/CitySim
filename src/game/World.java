package game;
/* World class for modeling the entire in-game world
 */

import java.util.ArrayList;

class World {
    Space entry;
    ArrayList<SpaceCity> spaces = new ArrayList<SpaceCity>();
    ArrayList<Road> roads = new ArrayList<Road>();

    World() {
        Space map = new SpaceMap(this);
        spaces.add(new SpaceCity("Capital", 45, 7));
        spaces.add(new SpaceCity("City", 10, 12));
        spaces.add(new SpaceCity("Town", 85, 3));
        spaces.add(new SpaceCity("Village", 5, 4));
        spaces.add(new SpaceCity("Hamlet", 30, 2));
        spaces.add(new SpaceCity("Locality", 55, 14));

        roads.add(new Road(spaces.get(0), spaces.get(2)));
        roads.add(new Road(spaces.get(0), spaces.get(1)));
        roads.add(new Road(spaces.get(0), spaces.get(3)));
        roads.add(new Road(spaces.get(0), spaces.get(4)));
        roads.add(new Road(spaces.get(0), spaces.get(5)));
        roads.add(new Road(spaces.get(1), spaces.get(3)));
        roads.add(new Road(spaces.get(1), spaces.get(5)));
        roads.add(new Road(spaces.get(2), spaces.get(4)));
        roads.add(new Road(spaces.get(2), spaces.get(5)));
        roads.add(new Road(spaces.get(3), spaces.get(4)));

        for (Space space : spaces) {
            map.addEdge(space.getName(), space);
        }

        for (Space space : spaces) {
            space.addEdge("Map", map);
        }

        for (Space space : spaces) {
            for (Space space2 : spaces) {
                if (space == space2){
                    continue;
                }
                space.addEdge(space2.getName(),space2);
            }
        }

        this.entry = map;
    }

    Space getEntry() {
        return entry;
    }

    void printMap() {
        char[][] map = new char[15][100];
        for (int i = 0; i < map.length; i++) {
            for (int j=0; j < map[i].length; j++) {
                map[i][j] = ' ';
            }
        }
        for (Road road : roads) {
            SpaceCity from = road.connectsTo[0];
            SpaceCity to = road.connectsTo[1];
            int max = (from.x > to.x) ? from.x : to.x;
            int min = (from.x < to.x) ? from.x : to.x;
            for (int i = min; i <= max; i++) {
                int y = from.y + (to.y - from.y) * (i - from.x) / (to.x - from.x);
                map[y][i] = '`';
            }
        }
        for (SpaceCity space : spaces) {
            char[] name = space.getName().toCharArray();
            for (int i = 0; i < name.length; i++) {
                map[space.y][space.x + i] = name[i];
            }
        }

        // print map
        for (int i = 0; i < map[0].length + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            System.out.print("|");
            for (int j=0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println("|");
        }
        for (int i = 0; i < map[0].length + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}

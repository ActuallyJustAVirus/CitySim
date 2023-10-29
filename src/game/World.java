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
        for (SpaceCity space : spaces) {
            map[space.y][space.x] = space.getName().charAt(0);
        }
        for (Road road : roads) {
            SpaceCity from = road.connectsTo[0];
            SpaceCity to = road.connectsTo[1];
            int x = (from.x + to.x) / 2;
            int y = (from.y + to.y) / 2;
            map[y][x] = 'X';
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

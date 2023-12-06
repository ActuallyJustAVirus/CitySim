package com.sim;
/* World class for modeling the entire in-game world
 */

import java.io.File;
import java.util.ArrayList;

public class World {
    Space entry;
    public ArrayList<CitySpace> spaces = new ArrayList<CitySpace>();
    public ArrayList<Road> roads = new ArrayList<Road>();

    public World() {
        Space map = new MapSpace(this);
        spaces.add(new CitySpace("Capital", 45, 7, true, true, true, true, true, 10000));
        spaces.add(new CitySpace("City", 10, 12, true, false, false, true, false, 5000));
        spaces.add(new CitySpace("Town", 85, 3, false, true, true, false, true, 4000));
        spaces.add(new CitySpace("Village", 5, 4, false, false,false, true, false, 3000));
        spaces.add(new CitySpace("Hamlet", 30, 2, false,true,false,false,false, 2000));
        spaces.add(new CitySpace("Locality", 55, 14,false, false, false, false, false, 1000));

        //TODO: Figure out what cities has access to what institutions.

        Item item1 = new Item("Test","This is a Test Item",new File("src/main/resources/com/sim/gui/Lake.png"));
        spaces.get(0).addItem(item1);
        Item item2 = new Item("Test","This is a Test Item",new File("src/main/resources/com/sim/gui/City.jpg"));
        spaces.get(1).addItem(item2);
        Item item3 = new Item("Test","This is a Test Item",new File("src/main/resources/com/sim/gui/Mountain.png"));
        spaces.get(2).addItem(item3);

        for (int i = 1; i < 6; i++) {
            spaces.get(0).addBothEdges(spaces.get(i).getName(),spaces.get(i),spaces.get(0).getName());
        }

        spaces.get(1).addBothEdges(spaces.get(3).getName(),spaces.get(3),spaces.get(1).getName());
        spaces.get(1).addBothEdges(spaces.get(5).getName(),spaces.get(5),spaces.get(1).getName());
        spaces.get(2).addBothEdges(spaces.get(4).getName(),spaces.get(4),spaces.get(2).getName());
        spaces.get(2).addBothEdges(spaces.get(5).getName(),spaces.get(5),spaces.get(2).getName());
        spaces.get(3).addBothEdges(spaces.get(4).getName(),spaces.get(4),spaces.get(3).getName());


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
        char[][] map = new char[15][100];
        for (int i = 0; i < map.length; i++) {
            for (int j=0; j < map[i].length; j++) {
                map[i][j] = ' ';
            }
        }

        for (Road road : roads) {
            CitySpace from = road.connectsTo[0];
            CitySpace to = road.connectsTo[1];
            int max = (from.x > to.x) ? from.x : to.x;
            int min = (from.x < to.x) ? from.x : to.x;
            for (int i = min; i <= max; i++) {
                int y = from.y + (to.y - from.y) * (i - from.x) / (to.x - from.x);
                map[y][i] = '`';
            }
        }

        for (CitySpace space : spaces) {
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

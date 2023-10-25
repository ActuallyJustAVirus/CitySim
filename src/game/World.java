package game;
/* World class for modeling the entire in-game world
 */

class World {
    Space entry;

    World() {
        Space map = new SpaceMap();
        Space capital = new Space("Capital");
        Space city = new Space("City");
        Space town = new Space("Town");

        map.addEdge("Capital", capital);
        map.addEdge("City", city);
        map.addEdge("Town", town);

        capital.addEdge("Map", map);
        city.addEdge("Map", map);
        town.addEdge("Map", map);

        this.entry = map;
    }

    Space getEntry() {
        return entry;
    }
}

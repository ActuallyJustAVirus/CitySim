package game;
/* World class for modeling the entire in-game world
 */

class World {
    Space entry;

    World() {
        Space map = new SpaceMap();
        SpaceCity capital = new SpaceCity("Capital");
        SpaceCity city = new SpaceCity("City");
        SpaceCity town = new SpaceCity("Town");

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

package game;
/* World class for modeling the entire in-game world
 */

class World {
    Space entry;

    World() {
        Space map = new SpaceMap();
        Space capital = new SpaceCity("Capital");
        Space city = new SpaceCity("City");
        Space town = new SpaceCity("Town");
        Space village = new SpaceCity("Village");

        map.addEdge("Capital", capital);
        map.addEdge("City", city);
        map.addEdge("Town", town);
        map.addEdge("Village",village);

        capital.addEdge("Map", map);
        city.addEdge("Map", map);
        town.addEdge("Map", map);
        village.addEdge("Map",map);

        this.entry = map;
    }

    Space getEntry() {
        return entry;
    }
}

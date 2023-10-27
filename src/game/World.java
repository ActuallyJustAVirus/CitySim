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
        Space Hamlet= new SpaceCity("Hamlet");
        Space Locality=new Space("Locality");

        map.addEdge("Capital", capital);
        map.addEdge("City", city);
        map.addEdge("Town", town);
        map.addEdge("Village",village);
        map.addEdge("Hamlet", Hamlet);
        map.addEdge("Locality", Locality);

        capital.addEdge("Map", map);
        city.addEdge("Map", map);
        town.addEdge("Map", map);
        village.addEdge("Map",map);
        Locality.addEdge("map", map);
        Hamlet.addEdge("map", map);

        this.entry = map;
    }

    Space getEntry() {
        return entry;
    }
}

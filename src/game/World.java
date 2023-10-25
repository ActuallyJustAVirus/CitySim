package game;
/* World class for modeling the entire in-game world
 */

/**
 * klassens eneste formål er at lave alle rummene og deres forbindelser
 * klassen bliver instantieret i Game klassen
 */
class World {
    /**
     * for at kunne komme ind i verdnen gemmes entry rummet i en variabel
     */
    Space entry;

    /**
     * laver alle rummene og deres forbindelser når klassen instantieres
     */
    World() {
        Space entry = new Space("Entry");
        Space corridor = new Space("Corridor");
        Space cave = new Space("Cave");
        Space pit = new Space("Darkest Pit");
        Space outside = new Space("Outside");

        entry.addEdge("door", corridor);
        corridor.addEdge("door", cave);
        cave.addEdge("north", pit);
        cave.addEdge("south", outside);
        pit.addEdge("door", cave);
        outside.addEdge("door", cave);

        this.entry = entry;
    }

    /**
     * @return entry rummet
     */
    Space getEntry() {
        return entry;
    }
}

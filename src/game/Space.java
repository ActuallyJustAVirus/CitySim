package game;
/* Space class for modeling spaces (rooms, caves, ...)
 */

import java.util.Set;

class Space extends Node {
    Space(String name) {
        super(name);
    }

    /**
     * printer velkomst til spilleren
     * @param name er navnet på rummet
     * @param edges er en liste af alle exits
     */
    public void welcome() {
        System.out.println("You are now at " + name);
        // funktionen edges.keySet() returnere alle keys i edges
        Set<String> exits = edges.keySet();
        System.out.println("Current exits are:");
        for (String exit : exits) {
            System.out.println(" - " + exit);
        }
    }

    /**
     * funktionen kaldes når spilleren forlader et rum
     * !ikke brugt i dette spil!
     */
    public void goodbye() {
    }

    /*
     * returnere det rum der er i den vaglte retning
     */
    @Override
    public Space followEdge(String direction) {
        return (Space) (super.followEdge(direction));
    }
}

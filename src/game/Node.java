package game;
/* Node class for modeling graphs
 */

import java.util.HashMap;
import java.util.Map;

class Node {
    String name;
    /**
     * edges er en HashMap, til alle de forskellige exits, som spilleren kan bruge.
     */
    Map<String, Node> edges = new HashMap<String, Node>();

    Node(String name) {
        this.name = name;
    }

    /**
     * returnere navnet på rummet
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * tilføjer en exit til rummet
     * @param name er navnet på exiten
     * @param node er det rum exiten fører til
     */
    public void addEdge(String name, Node node) {
        edges.put(name, node);
    }

    /*
     * returnere det rum der er i den vaglte retning
     */
    public Node followEdge(String direction) {
        return edges.get(direction);
    }
}

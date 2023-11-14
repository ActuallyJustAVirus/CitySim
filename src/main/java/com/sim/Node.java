package com.sim;
/* Node class for modeling graphs
 */

import com.sim.commands.CommandUnknown;

import java.util.HashMap;
import java.util.Map;

public class Node {
    String name;
    public Map<String, Node> edges = new HashMap<String, Node>();

    Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addEdge(String name, Node node) {
        edges.put(name, node);
    }

    public void addBothEdges(String name, Node node, String thisname){
        this.addEdge(name,node);
        node.addEdge(thisname, this);
    }

    public Node followEdge(String direction) {
        for(String compares: edges.keySet()){    //Compares lower case input with list of nodes.
            if (compares.toLowerCase().equals(direction)){
                return edges.get(compares);
            }
        }
        return null;
    }
}

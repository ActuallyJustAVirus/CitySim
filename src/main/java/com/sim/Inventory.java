package com.sim;
import java.util.ArrayList;

public class Inventory{
    private ArrayList<Item> objects;
    private boolean open;
    private String openMessage;

    public Item getItem(String name){
        for(Item test : objects){
            if(test.getName().equals(name)){
                return test;
            }
        }
        return null;
    }
}


/* TODO VI SKAL FÆRDIGGØRE INVENTAR.
    VI MANGLER AT SØRGE FOR AT INVENTAREN ÅBNES (FOR LOOP SKAL GENNEMGÅ OBJECTS OG PRINT HVERT ITEN.
    DERUOVER MANGLER VI AT TESTE INSPECT COMMAND
 */
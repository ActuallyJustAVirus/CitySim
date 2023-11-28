package com.sim.commands;

import com.sim.Context;
import com.sim.Inventory;
import com.sim.Item;
import java.util.Scanner;
public class CommandInspect extends BaseCommand{
    public CommandInspect() {
        description = "Inspect an item to get more information about it.";
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        if (parameters.length < 2) {
            System.out.println("Please provide the name of the item you want to inspect.");

            //Player inputs name of item.
            Scanner scanner = new Scanner(System.in);
            String itemName = scanner.nextLine().trim();

            if (itemName.isEmpty()) {
                System.out.println("No item name provided. Inspection canceled.");
                return;
            }
            inspectItem(context, itemName);
        } else {
            // Concatenate array elements into a single string
            String itemName = parameters[1];
            for (int i = 2; i < parameters.length; i++) {
                itemName += " " + parameters[i];
            }
            inspectItem(context, itemName);
        }
    }
    private void inspectItem(Context context, String itemName) {
        Item itemToInspect = context.inv.getItem(itemName);

        if (itemToInspect == null) {
            System.out.println("Item not found in your inventory.");
        } else {
            // Viser information om den valgte item.
            itemToInspect.inspect();
        }
    }
}

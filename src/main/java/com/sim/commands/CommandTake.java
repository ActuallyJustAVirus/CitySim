package com.sim.commands;
import com.sim.Inventory;
import com.sim.Context;
import com.sim.Item;

public class CommandTake extends BaseCommand {

    public CommandTake() {
        description = "Pick up the specified item.";
    }
    @Override
    public void execute(Context context, String command, String[] parameters) {
        if (parameters.length < 1) {
            System.out.println("Please provide the name of the item you want to take.");
            return;
        }
        String itemName = parameters[0];
        Item itemToTake = context.getCurrentSpace().getItem(itemName);

        if (itemToTake == null) {
            System.out.println("Item not found in the current space.");
        } else {
            context.inv.addItem(itemToTake);  // Add the item to the inventory
            context.getCurrentSpace().removeItem(itemToTake);  // Optionally remove the item from the space
            System.out.println(itemName + " taken and added to the inventory.");
        }
    }
}



// Indtil videre kan denne command ikke meget, da items endnu ikke eksisterer i verdenen.
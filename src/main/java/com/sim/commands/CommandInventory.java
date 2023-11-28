package com.sim.commands;

import com.sim.Context;
import com.sim.Item;

public class CommandInventory extends BaseCommand {
    public CommandInventory() {
        description = "See what items you have access to.";
    }
    @Override
    public void execute(Context context, String command, String[] parameters) {
        System.out.println(context.inv.getInventoryList());
    }
}
package com.sim.commands;

import com.sim.Context;
import com.sim.Item;
public class CommandInspect extends BaseCommand{
    public CommandInspect() {
        description = "Inspect an item";
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        context.item1.Inspect();
    }
}

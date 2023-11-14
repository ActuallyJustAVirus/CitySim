package com.sim.commands;

import com.sim.Context;

public class CommandMap extends BaseCommand {
    public CommandMap() {
        description = "Open the map";
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        context.transition("Map");
    }
    
}

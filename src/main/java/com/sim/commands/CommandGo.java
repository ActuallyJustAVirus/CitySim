package com.sim.commands;
/* Command for transitioning between spaces
 */

import com.sim.Context;

public class CommandGo extends BaseCommand {
    public CommandGo() {
        description = "Follow an exit";
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        if (guardEq(parameters, 1)) {
            System.out.println("I don't seem to know where that is 🤔");
            return;
        }
        context.transition(parameters[0]);
    }
}

package com.sim.commands;
/* Command for exiting program
 */

import com.sim.Context;

public class CommandExit extends BaseCommand {

    public CommandExit() {
        description = "Quit the game";
    }
    @Override
    public void execute(Context context, String command, String parameters[]) {
        context.makeDone();
    }
}

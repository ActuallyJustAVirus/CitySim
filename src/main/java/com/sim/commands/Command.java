package com.sim.commands;
/* Command interface
 */

import com.sim.Context;

public interface Command {
    void execute(Context context, String command, String parameters[]);

    String getDescription();
}

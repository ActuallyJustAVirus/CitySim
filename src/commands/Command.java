package commands;
/* Command interface
 */

import game.Context;

/**
 * Command interface 
 */
public interface Command {
    void execute(Context context, String command, String parameters[]);

    String getDescription();
}

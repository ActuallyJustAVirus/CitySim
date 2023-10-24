package commands;
/* Command interface
 */

import game.Context;

public interface Command {
    void execute(Context context, String command, String parameters[]);

    String getDescription();
}

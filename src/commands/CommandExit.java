package commands;
/* Command for exiting program
 */

import game.Context;

public class CommandExit extends BaseCommand implements Command {
    @Override
    public void execute(Context context, String command, String parameters[]) {
        context.makeDone();
    }
}

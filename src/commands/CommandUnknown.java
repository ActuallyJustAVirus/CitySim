package commands;
/* Fallback for when a command is not implemented
 */

import game.Context;

public class CommandUnknown extends BaseCommand {
    @Override
    public void execute(Context context, String command, String parameters[]) {
        System.out.println("Woopsie, I don't understand '" + command + "' 😕");
    }
}

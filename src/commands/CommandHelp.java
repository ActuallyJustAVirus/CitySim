package commands;
/* Help command
 */

import java.util.Arrays;

import game.Context;
import game.Registry;

/**
 * CommandHelp er til at vise en hjælpe besked
 */
public class CommandHelp extends BaseCommand {
    Registry registry;

    public CommandHelp(Registry registry) {
        this.registry = registry;
        this.description = "Display a help message";
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        String[] commandNames = registry.getCommandNames();
        Arrays.sort(commandNames);

        // find max length of command name
        int max = 0;
        for (String commandName : commandNames) {
            int length = commandName.length();
            if (length > max) {
                max = length;
            }
        }

        // present list of commands
        System.out.println("Commands:");
        for (String commandName : commandNames) {
            String description = registry.getCommand(commandName).getDescription();
            System.out.printf(" - %-" + max + "s %s%n", commandName, description);
        }
    }
}

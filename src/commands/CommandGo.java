package commands;
/* Command for transitioning between spaces
 */

import game.Context;

/**
 * Command til at gå til et andet rum
 */
public class CommandGo extends BaseCommand {
    public CommandGo() {
        description = "Follow an exit";
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        // tjekker om der er et parameter
        if (guardEq(parameters, 1)) {
            System.out.println("I don't seem to know where that is 🤔");
            return;
        }
        context.transition(parameters[0]);
    }
}

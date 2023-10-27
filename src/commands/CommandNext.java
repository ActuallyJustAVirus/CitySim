package commands;

import game.Context;

public class CommandNext extends BaseCommand {

    public CommandNext() {description = "Go to next turn";}

    @Override
    public void execute(Context context, String command, String[] parameters) {
        context.NextTurn();
    }
/* Right now, when you do type the command 'next',
This will call the method CommandNext,
which then prints "1 month later".

This command has to add money to the players inventory,
and maybe in the future, call a information method about the players actions.*/

}

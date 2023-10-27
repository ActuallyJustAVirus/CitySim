package commands;

import game.Context;

public class CommandBalance extends BaseCommand {
    @Override
    public void execute(Context context, String command, String[] parameters) {
    // metode der fremvise din nuværende kampital    
    context.GetBalance();


    }
}

package commands;

import game.Context;
import game.Space;
import game.SpaceCity;

public class CommandBuild extends BaseCommand {

    @Override
    public void execute(Context context, String command, String[] parameters) {
        Space currentSpace = context.getCurrentSpace();
        if (currentSpace instanceof SpaceCity) {
            SpaceCity city = (SpaceCity) currentSpace;
            
        } else {
            System.out.println("You can't build a city here");
        }
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}

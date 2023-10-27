package commands;

import game.Context;
import game.Registry;
import game.Space;
import game.SpaceCity;
import game.Road;
import java.util.Scanner;
import game.Game;

public class CommandBuild extends BaseCommand {

    static Scanner scanner = new Scanner(System.in);

    String city2;
    public CommandBuild() {
        description = "Build a road";
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        Space currentSpace = context.getCurrentSpace();
        if (currentSpace instanceof SpaceCity) {
            SpaceCity city = (SpaceCity) currentSpace;
            System.out.println("Build a road to: ");
            System.out.print("> ");
            city2 = scanner.next();

            System.out.println("You have built a road from "+ city.getName()+" to "+city2);


            
        } else {
            System.out.println("You can't build a road here 😭");
        }
    }
    
}

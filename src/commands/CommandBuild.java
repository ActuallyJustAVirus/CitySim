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

    String cityInput;
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
            cityInput = scanner.next();
            SpaceCity city2 = (SpaceCity) city.followEdge(cityInput);

            if (city2 == null){
                System.out.println("You can't build a road from "+city.getName()+" to "+cityInput);
                return;
            }

            Road road = new Road(city,city2);
            System.out.println("You have built a road from "+ city.getName()+" to "+cityInput);


            
        } else {
            System.out.println("You can't build a road here 😭");
        }
    }
    
}

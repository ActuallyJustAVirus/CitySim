package com.sim.commands;

import java.util.Scanner;
import java.util.Set;

import com.sim.CitySpace;
import com.sim.Context;
import com.sim.Game;
import com.sim.Node;
import com.sim.Registry;
import com.sim.Road;
import com.sim.Space;

public class CommandBuild extends BaseCommand {

    static Scanner scanner = new Scanner(System.in);

    String cityInput;
    public CommandBuild() {
        description = "Build a road";
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        Space currentSpace = context.getCurrentSpace();
        if (currentSpace instanceof CitySpace) {

            CitySpace city = (CitySpace) currentSpace;

            Set<String> cities = city.edges.keySet();
            System.out.println("You can build roads to: ");
            label:
            for (String cityName : cities) {

                if (cityName.equals("Map")) {
                    continue;
                }

                CitySpace node = (CitySpace)city.edges.get(cityName);
                for (Road road : context.world.roads) {
                    if ((road.connectsTo[0] == city && road.connectsTo[1] == city)||(road.connectsTo[1] == node && road.connectsTo[0] == city)) {
                        continue label; //jumps to 'label'
                    }
                }
                System.out.println("-"+cityName+" "+"("+context.getPrice(node,city)+'$'+")");

            }
            System.out.println();

            System.out.println("Build a road to: ");
            System.out.print("> ");
            cityInput = scanner.next();
            CitySpace city2 = (CitySpace) city.followEdge(cityInput);

            if (city2 == null){
                System.out.println("You can't build a road from "+city.getName()+" to "+cityInput);
                return;
            }

            for (Road road : context.world.roads) {
                if ((road.connectsTo[0] == city2 && road.connectsTo[1] == city)||(road.connectsTo[1] == city2 && road.connectsTo[0] == city)) {
                    //Checker om der er en road fra city2 til city, og omvendt.
                    System.out.println("There is already a sufficient road between " + city.getName() + " and " + cityInput);
                    return;
                }
            }

            int price = context.getPrice(city2,city);

            if (context.balance < price){
                System.out.println("Insufficient funds 😭");
                return;
            }
            Road road = new Road(city, city2);
            context.world.roads.add(road);
            System.out.println("You have built a road from " + city.getName() + " to " + cityInput);

            context.balance -= context.getPrice(city2,city);

            
        } else {
            System.out.println("You can't build a road here 😭");
        }
    }
    
}

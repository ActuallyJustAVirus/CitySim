package com.sim;
/* Main class for launching the game
 */

import java.util.Scanner;

import com.sim.commands.Command;
import com.sim.commands.CommandBalance;
import com.sim.commands.CommandExit;
import com.sim.commands.CommandGo;
import com.sim.commands.CommandHelp;
import com.sim.commands.CommandUnknown;
import com.sim.commands.CommandNext;
import com.sim.commands.CommandMap;
import com.sim.commands.CommandBuild;

public class Game {
    static World world = new World();
    static Context context = new Context(world);
    static Command fallback = new CommandUnknown();
    static Registry registry = new Registry(context, fallback);
    static Scanner scanner = new Scanner(System.in);

    private static void initRegistry() {
        Command cmdExit = new CommandExit();
        registry.register("exit", cmdExit);
        registry.register("quit", cmdExit);
        registry.register("bye", cmdExit);
        registry.register("go", new CommandGo());
        registry.register("help", new CommandHelp(registry));
        registry.register("next", new CommandNext());
        registry.register("map", new CommandMap());
        registry.register("build", new CommandBuild());
        registry.register("bal", new CommandBalance());

        // example of anonymous class
        registry.register("duck", new Command() {
            @Override
            public void execute(Context context, String command, String[] parameters) {
                System.out.println("🦆");
            }

            @Override
            public String getDescription() {
                return "Prints a duck";
            }
        });
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the World of Zuul!");

        initRegistry();
        context.getCurrentSpace().welcome();

        while (context.isDone() == false) {
            System.out.print("> ");
            String line = scanner.nextLine();
            registry.dispatch(line);
        }

        System.out.println("Game Over 😥");
    }
}

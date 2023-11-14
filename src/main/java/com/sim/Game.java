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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class Game extends javafx.application.Application{
    static World world = new World();
    static Context context = new Context(world);
    static Command fallback = new CommandUnknown();
    static Registry registry = new Registry(context, fallback);
    static Scanner scanner = new Scanner(System.in);

    @Override
    public void start (Stage stage) {
        Label intro = new Label("Welcome to Burkina Faso\nChoose a city to visit");
        Button button1 = new Button("Capital");
        Button button2 = new Button("City");
        Button button3 = new Button("Village");
        Button button4 = new Button("Hamlet");
        Button button5 = new Button("Locality");
        Button button6 = new Button("Town");

        HBox introBox = new HBox();
        HBox buttons1 = new HBox();
        HBox buttons2 = new HBox();
        VBox vbox = new VBox();
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                registry.dispatch("go Capital");
                Platform.exit();
            }
        });
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                registry.dispatch("go Capital");
                Platform.exit();
            }
        });
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                registry.dispatch("go Capital");
                Platform.exit();
            }
        });

        introBox.getChildren().addAll(intro,vbox);
        buttons1.getChildren().addAll(button1, button2, button3);
        buttons2.getChildren().addAll(button4, button5, button6);
        vbox.getChildren().addAll(intro,buttons1,buttons2);
        stage.setScene(new Scene(introBox));
        stage.setWidth(500);
        stage.setHeight(300);
        stage.show();
    }

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
        launch(args);

        while (context.isDone() == false) {
            System.out.print("> ");
            String line = scanner.nextLine();
            registry.dispatch(line);
        }

        System.out.println("Game Over 😥");
    }
}

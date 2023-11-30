package com.sim.gui;

import java.io.IOException;
import java.util.ArrayList;

import com.sim.CitySpace;
import com.sim.Context;
import com.sim.World;
import com.sim.commands.CommandInventory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Frame extends Application {
    
    private static Scene scene;
    private static Canvas canvas;
    private static AnchorPane overlay;

    private static Button infoButton, buildButton, closeInfo, inventoryButton, closeInventory, nextturnButton;

    private static Label infoText, inventoryLabel ;

    private static Pane infoPane,inventoryPane;

    public static World world;

    public static Context context;



    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("test"), 640, 480);
        stage.setScene(scene);
        stage.show();
        canvas = (Canvas) scene.lookup("#canvas");
        GameCanvas gameCanvas = new GameCanvas(canvas, world, context);
        overlay = (AnchorPane) scene.lookup("#overlay");
        context = new Context(world);
        infoButton = (Button) scene.lookup("#infoButton");
        buildButton = (Button) scene.lookup("#buildButton");
        infoPane = (Pane) scene.lookup("#infoPane");
        closeInfo = (Button) scene.lookup("#closeInfo");
        infoText = (Label) scene.lookup("#infoText");
        inventoryButton = (Button) scene.lookup("#inventoryButton");
        inventoryPane = (Pane) scene.lookup("#inventoryPane");
        closeInventory = (Button) scene.lookup("#closeInventory");
        inventoryLabel = (Label) scene.lookup("#inventoryText");
        nextturnButton = (Button) scene.lookup("#nextturn");

        nextturnButton.setOnMouseClicked(e->{
            context.NextTurn();

        });
        closeInfo.setOnMouseClicked(e -> {
            infoPane.setVisible(false);
        });

        buildButton.setOnMouseClicked(e -> {
            gameCanvas.checkBuildClicked();
        });

        inventoryButton.setOnMouseClicked(e -> {
            inventoryPane.setVisible(true);
            inventoryLabel.setText(context.inv.getInventoryList());

        });

        closeInventory.setOnMouseClicked(e -> {
            inventoryPane.setVisible(false);
        });

        overlay.setOnMouseClicked(e -> {
            CitySpace selectedCity = gameCanvas.checkClick(e.getX(),e.getY());

            context.transition("map"); //TODO: Fix "You are confused, and walk in a circle looking for 'map'."

            if (selectedCity != null) {
                context.transition(selectedCity.getName());
                gameCanvas.build = false;
                gameCanvas.highlightedCities = new ArrayList<>();
                infoButton.setVisible(true);
                buildButton.setVisible(true);

                infoButton.setOnMouseClicked(f -> {
                    infoPane.setVisible(true);
                    infoText.setText(selectedCity.getInfo());
                });
            }

            else {
                infoButton.setVisible(false);
                buildButton.setVisible(false);
            }

            if (gameCanvas.build){
                gameCanvas.build = false;
            }

        });
        gameCanvas.redraw();

    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Frame.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        world = new World();
        launch(args);
    }
}

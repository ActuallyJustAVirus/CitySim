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

    private static Button infoButton, buildButton, closeInfo, inventoryButton, closeInventory, nextTurnButton, yesBuild, noBuild;

    private static Label infoText, inventoryLabel, buildText;

    private static Pane infoPane,inventoryPane, buildPane;

    public static World world;

    public static Context context;



    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("test"), 640, 480);
        stage.setScene(scene);
        stage.show();
        canvas = (Canvas) scene.lookup("#canvas");
        context = new Context(world);
        GameCanvas gameCanvas = new GameCanvas(canvas, world, context);
        overlay = (AnchorPane) scene.lookup("#overlay");

        infoButton = (Button) scene.lookup("#infoButton");
        buildButton = (Button) scene.lookup("#buildButton");
        infoPane = (Pane) scene.lookup("#infoPane");
        closeInfo = (Button) scene.lookup("#closeInfo");
        infoText = (Label) scene.lookup("#infoText");
        inventoryButton = (Button) scene.lookup("#inventoryButton");
        inventoryPane = (Pane) scene.lookup("#inventoryPane");
        closeInventory = (Button) scene.lookup("#closeInventory");
        inventoryLabel = (Label) scene.lookup("#inventoryText");
        nextTurnButton = (Button) scene.lookup("#nextturn");
        buildPane = (Pane) scene.lookup("#buildPane");
        buildText = (Label) scene.lookup("#buildText");
        yesBuild = (Button) scene.lookup("#yesBuild");
        noBuild = (Button) scene.lookup("#noBuild");

        nextTurnButton.setOnMouseClicked(e->{
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

        yesBuild.setOnMouseClicked(e -> {
            buildPane.setVisible(false);
            gameCanvas.build();
            gameCanvas.selectedCity = null;
            buildButton.setVisible(false);
            infoButton.setVisible(false);
            gameCanvas.redraw();
        });

        noBuild.setOnMouseClicked(e -> {
            buildPane.setVisible(false);
            gameCanvas.build = false;
            gameCanvas.redraw();
        });

        overlay.setOnMouseClicked(e -> {
            if (gameCanvas.build){
                gameCanvas.selectedBuildCity = gameCanvas.checkClick(e.getX(), e.getY());
                buildPane.setVisible(true);
                buildText.setText("Build a road from "+gameCanvas.selectedCity.getName()+" to "+gameCanvas.selectedBuildCity.getName()+"?");
            }

            else{
                gameCanvas.selectedCity = gameCanvas.checkClick(e.getX(),e.getY());
                context.transition("map"); //TODO: Fix "You are confused, and walk in a circle looking for 'map'."

            if (gameCanvas.selectedCity != null) {
                context.transition(gameCanvas.selectedCity.getName());
                gameCanvas.build = false;
                gameCanvas.highlightedCities = new ArrayList<>();
                infoButton.setVisible(true);
                buildButton.setVisible(true);

                infoButton.setOnMouseClicked(f -> {
                    infoPane.setVisible(true);
                    infoText.setText(gameCanvas.selectedCity.getInfo());
                });
            }

            else {
                infoButton.setVisible(false);
                buildButton.setVisible(false);
            }

            if (gameCanvas.build){
                gameCanvas.build = false;
            }
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

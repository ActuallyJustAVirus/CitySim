package com.sim.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.sim.CitySpace;
import com.sim.Context;
import com.sim.Item;
import com.sim.World;
import com.sim.commands.CommandInventory;
import com.sim.commands.CommandTake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Frame extends Application {
    
    private static Scene scene;
    private static Canvas canvas;
    private static AnchorPane overlay;

    private static Button infoButton, buildButton, closeInfo, inventoryButton, closeInventory, nextTurnButton, yesBuild, noBuild;

    private static Label infoText, inventoryLabel, buildText, moneyLabel, dayLabel;

    private static Pane infoPane,inventoryPane, buildPane, itemPane;

    public static World world;

    public static Context context;



    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("test"), 800, 500);
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
        itemPane = (Pane) scene.lookup("#itemPane");
        buildText = (Label) scene.lookup("#buildText");
        yesBuild = (Button) scene.lookup("#yesBuild");
        noBuild = (Button) scene.lookup("#noBuild");
        moneyLabel = (Label) scene.lookup("#moneyLabel");
        dayLabel = (Label) scene.lookup("#dayLabel");

        updateLabel();

        nextTurnButton.setOnMouseClicked(e->{
            context.NextTurn();
            updateLabel();
        });

        closeInfo.setOnMouseClicked(e -> {
            infoPane.setVisible(false);
        });

        buildButton.setOnMouseClicked(e -> {
            if (gameCanvas.build){
                gameCanvas.build = false;
                buildButton.setStyle("-fx-background-color: white; -fx-font-weight: bold");
                gameCanvas.redraw();
            }
            else {
                gameCanvas.checkBuildClicked();
                buildButton.setStyle("-fx-background-color: yellow; -fx-font-weight: bold");
            }
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
            gameCanvas.redraw();
        });

        overlay.setOnMouseClicked(e -> {
            if (gameCanvas.build){
                gameCanvas.selectedBuildCity = gameCanvas.checkClick(e.getX(), e.getY());
                for (CitySpace ccas : gameCanvas.highlightedCities) {
                    if (ccas == gameCanvas.selectedBuildCity) {
                        buildPane.setVisible(true);
                        buildText.setText("Build a road from "+gameCanvas.selectedCity.getName()+" to "+gameCanvas.selectedBuildCity.getName()+"?");
                        return;
                    }
                }
                if (gameCanvas.selectedCity != null && gameCanvas.selectedBuildCity == gameCanvas.selectedCity) return;
                gameCanvas.build = false;
                buildButton.setVisible(false);
                infoButton.setVisible(false);
                buildPane.setVisible(false);
            } else {
                gameCanvas.selectedCity = gameCanvas.checkClick(e.getX(),e.getY());
                context.transition("map"); //TODO: Fix "You are confused, and walk in a circle looking for 'map'."

                if (gameCanvas.selectedCity != null) {
                    context.transition(gameCanvas.selectedCity.getName());
                    gameCanvas.build = false;
                    gameCanvas.highlightedCities = new ArrayList<>();
                    infoButton.setVisible(true);
                    buildButton.setVisible(true);
                    buildButton.setStyle("-fx-background-color: white; -fx-font-weight: bold");

                    infoButton.setOnMouseClicked(f -> {
                        if (gameCanvas.build){
                            gameCanvas.build = false;
                            gameCanvas.redraw();
                        }
                        infoPane.setVisible(true);
                            infoText.setText(gameCanvas.selectedCity.getInfo());
                    });

                    String items = gameCanvas.selectedCity.getItems();
                    if (!items.equals("")) {
                        itemPane.setVisible(true);
                        ImageView imageView = (ImageView)itemPane.lookup("#imageView");
                        Label itemNameLabel = (Label)itemPane.lookup("#itemName");
                        Label itemDescriptionLabel = (Label)itemPane.lookup("#itemDescription");
                        Button takeButton = (Button)itemPane.lookup("#collectItemBtn");

                        String itemname = items.split("\n")[0];
                        Item item = gameCanvas.selectedCity.getItem(itemname);
                        itemNameLabel.setText("You found a "+itemname);
                        itemDescriptionLabel.setText(item.getDesc());
                        Image image = new Image(item.image.toURI().toString());
                        imageView.setImage(image);

                        takeButton.setOnMouseClicked(g -> {
                            CommandTake take = new CommandTake();
                            take.execute(context, "take", new String[]{itemname}); 
                            itemPane.setVisible(false);
                        });
                    }

                } else {
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

    private void updateLabel(){
        moneyLabel.setText("Balance: " + String.valueOf((context.balance)));

        dayLabel.setText(context.getGameTime());
    }

    public static void main(String[] args) {
        world = new World();
        launch(args);
    }
}

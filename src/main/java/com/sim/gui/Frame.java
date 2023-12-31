package com.sim.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.sim.*;
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
import  javafx.scene.text.*;


public class Frame extends Application {

    private static Scene scene;
    private static Canvas canvas;
    private static AnchorPane overlay;

    private static Button infoButton, buildButton, closeInfo, inventoryButton, closeInventory, nextTurnButton, yesBuild, noBuild, okBuild, continueButton, itemContinueButton;

    private static Label infoText, tutLabel, inventoryLabel, buildText, dayLabel, introLabel, itemCollectedLabel, welcome, buildPrice, brokeText, Win, Lose;

    private static Pane infoPane,inventoryPane, buildPane, itemPane, tutPane, introPane, itemCollectedPane;

    private static Text moneyText;

    public static World world;

    public static Context context;

    GameCanvas gameCanvas;

    Tutorial tutorial;

    Level currentLevel;



    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("test"), 800, 500);
        stage.setScene(scene);
        stage.show();
        canvas = (Canvas) scene.lookup("#canvas");
        context = new Context(world);
        gameCanvas = new GameCanvas(canvas, world, context);
        overlay = (AnchorPane) scene.lookup("#overlay");

        tutorial = new Tutorial();
        currentLevel = new Level(tutorial.levels.get(0));

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
        moneyText = (Text) scene.lookup("#moneyText");
        dayLabel = (Label) scene.lookup("#dayLabel");
        introLabel = (Label) scene.lookup("#introLabel");
        continueButton = (Button) scene.lookup("#continueButton");
        introPane = (Pane) scene.lookup("#introPane");
        tutPane = (Pane) scene.lookup("#tutPane");
        tutLabel = (Label) scene.lookup("#tutLabel");
        itemCollectedPane = (Pane) scene.lookup("#itemCollectedPane");
        itemContinueButton = (Button) scene.lookup("#itemContinueButton");
        itemCollectedLabel = (Label) scene.lookup("#itemCollectedLabel");
        welcome = (Label) scene.lookup("#welcome");

        buildPrice = (Label) scene.lookup("#buildPrice");
        brokeText = (Label) scene.lookup("#brokeText");
        okBuild = (Button) scene.lookup("#okBuild");

        updateLabel();

        introLabel.setText(
                "a picturesque country craving progress amid its stunning " +
                        "\nlandscapes and cultural richness. Experience the role of a " +
                        "\nvisionary leader tasked with rejuvenating Zamoridia's worn " +
                        "\nroads. Strategize, allocate resources, and navigate " +
                        "\nchallenges to weave a network that connects communities, " +
                        "\nunlocking the nation's potential and fostering prosperity " +
                        "\nacross its enchanting yet neglected terrains.");

        continueButton.setOnMouseClicked(e -> {
            introPane.setVisible(false);
            if (currentLevel.getLevelNumber() == 0){
                tutPane.setVisible(true);
                tutLabel.setText(currentLevel.getDescription());
            }
        });

        nextTurnButton.setOnMouseClicked(e -> {

            Win = (Label) scene.lookup("#Win");
            ArrayList<CitySpace> spaces = new ArrayList<CitySpace>();
            ArrayList<CitySpace> CitySpace = world.spaces;
            for (int i = 0; i < CitySpace.size(); i++) {
                CitySpace city = CitySpace.get(i);
                if (!city.hasAccessToallinsisutions()) {
                    break;
                }
                if(i==CitySpace.size()-1){
                    Win.setVisible(true);
                }
            }

            Lose = (Label) scene.lookup("#Lose");
            if (context.tid > context.max) {
                Lose.setVisible(true);

            }
            context.NextTurn();
            updateLabel();
        });



        infoButton.setOnMouseClicked(f -> {
            if (gameCanvas.build) {
                gameCanvas.build = false;
                gameCanvas.redraw();
            }
            infoPane.setVisible(true);
            infoText.setText(gameCanvas.selectedCity.getInfo());
        });

        closeInfo.setOnMouseClicked(e -> {
            infoPane.setVisible(false);
        });

        buildButton.setOnMouseClicked(e -> {
            if (gameCanvas.build){
                gameCanvas.build = false;
                buildButton.setStyle("-fx-font-weight: bold");
                gameCanvas.redraw();
            } else {
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
            updateLabel();
            gameCanvas.redraw();
        });

        noBuild.setOnMouseClicked(e -> {
            buildPane.setVisible(false);
            gameCanvas.redraw();
        });

        okBuild.setOnMouseClicked(e -> {
            buildPane.setVisible(false);
            gameCanvas.redraw();
        });

        overlay.setOnMouseClicked(e -> {
            if (gameCanvas.build){
                gameCanvas.selectedBuildCity = gameCanvas.checkClick(e.getX(), e.getY());
                for (CitySpace ccas : gameCanvas.highlightedCities) {
                    if (ccas == gameCanvas.selectedBuildCity) {
                        buildPrice.setText("Price: $" + context.getPrice(gameCanvas.selectedCity, gameCanvas.selectedBuildCity) + " million");
                        buildText.setText("Build a road from " + gameCanvas.selectedCity.getName() + " to " + gameCanvas.selectedBuildCity.getName() + "?");
                        if(context.balance < context.getPrice(gameCanvas.selectedCity, gameCanvas.selectedBuildCity)){
                            buildPane.setVisible(true);
                            brokeText.setVisible(true);
                            yesBuild.setVisible(false);
                            noBuild.setVisible(false);
                            okBuild.setVisible(true);
                            return;
                        } else {
                            brokeText.setVisible(false);
                            yesBuild.setVisible(true);
                            noBuild.setVisible(true);
                            okBuild.setVisible(false);
                            buildPane.setVisible(true);
                            return;
                        }
                    }
                }
                if (gameCanvas.selectedCity != null && gameCanvas.selectedBuildCity == gameCanvas.selectedCity) return;
                gameCanvas.build = false;
                buildButton.setVisible(false);
                infoButton.setVisible(false);
                buildPane.setVisible(false);
            } else {
                gameCanvas.selectedCity = gameCanvas.checkClick(e.getX(),e.getY());
                context.transition("map");
                if (gameCanvas.selectedCity != null){
                    citySelect(gameCanvas.selectedCity);
                }
            }
            if (gameCanvas.selectedCity == null){
                infoButton.setVisible(false);
                buildButton.setVisible(false);
            }
        });
        gameCanvas.redraw();
    }

    void citySelect(CitySpace city){
        gameCanvas.selectedCity = city;
        if (gameCanvas.selectedCity != null) {
            context.transition(gameCanvas.selectedCity.getName());
            gameCanvas.build = false;
            gameCanvas.highlightedCities = new ArrayList<>();

            if (context.hasTools("Steamroller")) {
                buildButton.setVisible(true);
                buildButton.setStyle("-fx-font-weight: bold");
            } else {
                buildButton.setVisible(false);
            }

            if (context.hasTools("Filing cabinet")){
                infoButton.setVisible(true);
            } else {
                infoButton.setVisible(false);
            }

            if (gameCanvas.build) {
                gameCanvas.build = false;
            }
            displayItems(city);

        };

        gameCanvas.redraw();

    }

    void nextTutorial() {
        if (currentLevel.getLevelNumber() < tutorial.levels.size()-1){
            tutPane.setVisible(true);
            currentLevel = tutorial.levels.get(currentLevel.getLevelNumber() + 1);
            tutLabel.setText(currentLevel.getDescription());
            currentLevel.getSpace().addItem(currentLevel.getLevelItem());
         }
        else {
            tutPane.setVisible(false);
            introPane.setVisible(true);
            welcome.setText("Ready to build");
            introLabel.setText("Now that you have all the tools necessary, you are " +
                    "\nready to build. Your goal is to make sure that all the " +
                    "\npeople of Zamoridia have access to Schools, Fire and " +
                    "\nPolice Stations, Hospitals and Workplaces, before the " +
                    "\nyear 2030. Keep track of your balance and the time in " +
                    "\nthe toolbar at the bottom, and use the \"Next\" button " +
                    "\nto progress one month, collecting an increasing amount " +
                    "\nof money, based on your progress");
            introLabel.setStyle("-fx-font-size: 16");
        }
    }

    void displayItems(CitySpace city){
        if (city.getItems() != null) {
            if (currentLevel.getLevelItem().getName().equals(city.getItems().getName())) {
                Item items = city.getItems();
                if (items != null) {
                    itemPane.setVisible(true);
                    ImageView imageView = (ImageView) itemPane.lookup("#imageView");
                    Label itemNameLabel = (Label) itemPane.lookup("#itemName");
                    Label itemDescriptionLabel = (Label) itemPane.lookup("#itemDescription");
                    Button takeButton = (Button) itemPane.lookup("#collectItemBtn");

                    itemNameLabel.setText("You found a " + items.getName());
                    itemDescriptionLabel.setText(items.getDesc());
                    Image image = new Image(items.image.toURI().toString());
                    imageView.setImage(image);

                    takeButton.setOnMouseClicked(g -> {
                        CommandTake take = new CommandTake();
                        take.execute(context, "take", new String[]{items.getName()});
                        itemPane.setVisible(false);
                        tutPane.setVisible(false);
                        itemCollectedPane.setVisible(true);
                        itemCollectedLabel.setText(currentLevel.getItemDescription());
                        itemContinueButton.setOnMouseClicked(e -> {
                            itemCollectedPane.setVisible(false);
                            nextTutorial();
                        });
                    });
                }
            }
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Frame.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private void updateLabel() {
        moneyText.setText("$" + String.valueOf((context.balance)) + " million");
        moneyText.setFill(Color.GREEN);

        dayLabel.setText(context.getGameTime());
    }

    public static void main(String[] args) {
        world = new World();
        launch(args);
    }
}

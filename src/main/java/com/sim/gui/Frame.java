package com.sim.gui;

import java.io.IOException;

import com.sim.CitySpace;
import com.sim.World;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Frame extends Application {
    
    private static Scene scene;
    private static Canvas canvas;
    private static AnchorPane overlay;

    public static World world;



    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("test"), 640, 480);
        stage.setScene(scene);
        stage.show();
        canvas = (Canvas) scene.lookup("#canvas");
        GameCanvas gameCanvas = new GameCanvas(canvas);
        overlay = (AnchorPane) scene.lookup("#overlay");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.FORESTGREEN);
        gc.fillRect(0, 0, 10000, 10000);
        gc.fillText("Hello", 10, 10);
        overlay.setOnMouseClicked(e -> {
            // gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.setFill(javafx.scene.paint.Color.BLACK);
            gc.fillText("Hello", e.getX(), e.getY());
        });

        for (CitySpace cities:world.spaces) {
            gameCanvas.drawCity(cities.getX(),cities.getY(),cities.getName());
        }
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

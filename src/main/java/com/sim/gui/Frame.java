package com.sim.gui;

import java.io.IOException;

import com.sim.CitySpace;
import com.sim.Context;
import com.sim.World;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Frame extends Application {
    
    private static Scene scene;
    private static Canvas canvas;
    private static AnchorPane overlay;

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



        overlay.setOnMouseClicked(e -> {
            CitySpace selectedCity = gameCanvas.checkClick(e.getX(),e.getY());

            context.transition("map");

            if (selectedCity != null) {
                context.transition(selectedCity.getName());
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

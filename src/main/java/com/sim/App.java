package com.sim;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {
    
    private static Scene scene;
    private static Canvas canvas;
    private static AnchorPane overlay;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("test"), 640, 480);
        stage.setScene(scene);
        stage.show();
        canvas = (Canvas) scene.lookup("#canvas");
        overlay = (AnchorPane) scene.lookup("#overlay");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(javafx.scene.paint.Color.RED);
        gc.fillRect(0, 0, 10000, 10000);
        gc.fillText("Hello", 10, 10);
        overlay.setOnMouseClicked(e -> {
            // gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.setFill(javafx.scene.paint.Color.BLACK);
            gc.fillText("Hello", e.getX(), e.getY());
        });
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}

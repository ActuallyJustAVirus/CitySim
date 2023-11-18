package com.sim.gui;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

public class GameCanvas {

    private Canvas canvas;

    Image city = new Image("file:/Users/mikkelnielsen/Desktop/Skole/OOP/data/CitySim/src/main/resources/com/sim/gui/Village.png");
    Image pond = new Image("file:/Users/mikkelnielsen/Desktop/Skole/OOP/data/CitySim/src/main/resources/com/sim/gui/Lake.png");
    Image mountain = new Image("file:/Users/mikkelnielsen/Desktop/Skole/OOP/data/CitySim/src/main/resources/com/sim/gui/Mountain.png");
    public GameCanvas (Canvas canvas){
        this.canvas = canvas;
    }

    public void drawCity (int x, int y, String name){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.setFill(javafx.scene.paint.Color.BLACK);
        gc.fillText(name,x*5+30,y*20+100);
        gc.drawImage(city,x*5,y*20,125,125);
        gc.drawImage(pond,375,200,150,150);
        gc.drawImage(pond,80,150,150,150);
        gc.drawImage(mountain,300,50,100,100);
    }
}

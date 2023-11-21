package com.sim.gui;


import com.sim.CitySpace;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class GameCanvas {

    private Canvas canvas;

    Image city = new Image("file:src/main/resources/com/sim/gui/village.png");
    Image pond = new Image("file:src/main/resources/com/sim/gui/Lake.png");
    Image mountain = new Image("file:src/main/resources/com/sim/gui/Mountain.png");
    Image capitalCity = new Image("file:src/main/resources/com/sim/gui/CapitalCity.png");

    public GameCanvas (Canvas canvas){
        this.canvas = canvas;
    }

    public void drawCity(ArrayList<CitySpace> cities){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.setFill((Color.BLACK));
        gc.drawImage(pond,375,200,150,150);
        gc.drawImage(pond,80,150,150,150);
        gc.drawImage(mountain,300,40,100,100);

        for (CitySpace cityspaces: cities) {
            if (cityspaces.getName() == "Capital"){
                gc.drawImage(capitalCity, cityspaces.getX()*5+25,cityspaces.getY()*20+10,75,75);
            }
            else {
                gc.drawImage(city, cityspaces.getX() * 5, cityspaces.getY() * 20, 125, 125);
            }
            gc.fillText(cityspaces.getName(), cityspaces.getX()*5+30, cityspaces.getY()*20+100);
        }

    }
}

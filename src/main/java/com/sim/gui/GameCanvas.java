package com.sim.gui;


import com.sim.CitySpace;
import com.sim.Context;
import com.sim.Node;
import com.sim.World;
import com.sim.commands.CommandBuild;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import com.sim.Road;

import java.util.ArrayList;


public class GameCanvas {

    private Canvas canvas;
    private World world;
    CitySpace selectedCity;

    CitySpace selectedBuildCity;

    ArrayList<CitySpace> highlightedCities = new ArrayList<>();

    CommandBuild builder;



    boolean build;

    Context context;

    Image city = new Image("file:src/main/resources/com/sim/gui/village.png");
    Image pond = new Image("file:src/main/resources/com/sim/gui/Lake.png");
    Image mountain = new Image("file:src/main/resources/com/sim/gui/Mountain.png");
    Image capitalCity = new Image("file:src/main/resources/com/sim/gui/CapitalCity.png");

    public GameCanvas (Canvas canvas, World world, Context context){
        this.canvas = canvas;
        this.world = world;
        this.context = context;
    }


    public void redraw(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.DARKOLIVEGREEN);
        gc.fillRect(0, 0, 10000, 10000);
        gc.setFill(Color.GRAY);
        gc.setFill((Color.BLACK));

        for (Road road : world.roads) { //TODO: Draw roads: Work in progress
            CitySpace from = road.connectsTo[0];
            CitySpace to = road.connectsTo[1];

            gc.setLineWidth(50);
            gc.setStroke(Color.DIMGRAY);

            gc.strokeLine(from.getX() * 5 + 60, from.getY() * 20 + 70, to.getX() * 5 + 60, to.getY() * 20 + 70);
        }

        for (CitySpace cityspaces: world.spaces) {
            if (cityspaces.getName() == "Capital"){
                gc.setFill(Color.SEAGREEN);
                gc.fillOval(cityspaces.getX()*5,cityspaces.getY()*22,125,75);
                gc.drawImage(capitalCity, cityspaces.getX()*5+25,cityspaces.getY()*20+10,75,75);
            }
            else {
                gc.setFill(Color.SEAGREEN);
                gc.fillOval(cityspaces.getX()*5,cityspaces.getY()*20,125,75);
                gc.drawImage(city, cityspaces.getX()*5, cityspaces.getY()*20, 125,125);
            }
            gc.fillText(cityspaces.getName(), cityspaces.getX()*5+30, cityspaces.getY()*20+100);

        }

        if (selectedCity != null){
            if (selectedCity.getName() == "Capital"){
                gc.fillRect(selectedCity.getX()*5+17.5, selectedCity.getY()*20+2.5, 95,105);
                gc.setFill(Color.OLIVEDRAB);
                gc.fillRect(selectedCity.getX()*5+20, selectedCity.getY()*20+5, 90,100);
                gc.drawImage(capitalCity,selectedCity.getX()*5+25, selectedCity.getY()*20+10,75,75);
                gc.setFill(Color.BLACK);
                gc.fillText(selectedCity.getName(),selectedCity.getX()*5+30, selectedCity.getY()*20+100 );
            }
            else{
                gc.fillRect(selectedCity.getX()*5+17.5, selectedCity.getY()*20+17.5, 95,95);
                gc.setFill(Color.OLIVEDRAB);
                gc.fillRect(selectedCity.getX()*5+20, selectedCity.getY()*20+20, 90,90);
                gc.drawImage(city,selectedCity.getX()*5, selectedCity.getY()*20,125,125);
                gc.setFill(Color.BLACK);
                gc.fillText(selectedCity.getName(),selectedCity.getX()*5+30, selectedCity.getY()*20+100 );

            }
        }

        if (build){
            for (CitySpace buildableCity : highlightedCities) {
                if (buildableCity.getName() == "Capital"){
                    gc.fillRect(buildableCity.getX()*5+17.5, buildableCity.getY()*20+2.5, 95,105);
                    gc.setFill(Color.ORANGE);
                    gc.fillRect(buildableCity.getX()*5+20, buildableCity.getY()*20+5, 90,100);
                    gc.drawImage(capitalCity,buildableCity.getX()*5+25, buildableCity.getY()*20+10,75,75);
                    gc.setFill(Color.BLACK);
                    gc.fillText(buildableCity.getName(),buildableCity.getX()*5+30, buildableCity.getY()*20+100 );
                }

                else {
                    gc.fillRect(buildableCity.getX() * 5 + 17.5, buildableCity.getY() * 20 + 17.5, 95, 95);
                    gc.setFill(Color.ORANGE);
                    gc.fillRect(buildableCity.getX() * 5 + 20, buildableCity.getY() * 20 + 20, 90, 90);
                    gc.drawImage(city, buildableCity.getX() * 5, buildableCity.getY() * 20, 125, 125);
                    gc.setFill(Color.BLACK);
                    gc.fillText(buildableCity.getName(), buildableCity.getX() * 5 + 30, buildableCity.getY() * 20 + 100);
                }
            }
        }

        gc.drawImage(pond,375,200,150,150);
        gc.drawImage(pond,80,150,150,150);
        gc.drawImage(mountain,300,40,100,100);

    }

    public void checkBuildClicked(){
        build = true;
        System.out.println(build);
        for (String city : selectedCity.edges.keySet()) {
            Node node = selectedCity.edges.get(city);
            if (node instanceof CitySpace){
                highlightedCities.add((CitySpace) node);
            }
        }
        redraw();
    }

    public CitySpace checkClick(double x, double y) {
        if (build){
            for (CitySpace city : world.spaces) {
                if (city.getName() == "Capital") {
                    if (x >= city.getX() * 5+20 && x <= city.getX() * 5 + 100 && y >= city.getY() * 20+15 && y <= city.getY() * 20 + 100) {
                        selectedBuildCity = city;
                        redraw();
                        return selectedBuildCity;
                    }
                }
                if (x >= city.getX() * 5 + 20 && x <= city.getX() * 5 + 100 && y >= city.getY() * 20+20 && y <= city.getY()*20+100) {
                    selectedBuildCity = city;
                    redraw();
                    return selectedBuildCity;
                }
            }
        } else{
            for (CitySpace city : world.spaces) {
                if (city.getName() == "Capital") {
                    if (x >= city.getX() * 5+20 && x <= city.getX() * 5 + 100 && y >= city.getY() * 20+15 && y <= city.getY() * 20 + 100) {
                        selectedCity = city;
                        redraw();
                        return selectedCity;
                    }
                }
                if (x >= city.getX() * 5 + 20 && x <= city.getX() * 5 + 100 && y >= city.getY() * 20+20 && y <= city.getY()*20+100) {
                    selectedCity = city;
                    redraw();
                    return selectedCity;
                }
            }

        }

        selectedCity = null;
        highlightedCities = new ArrayList<>();
        redraw();
        return null;
    }

    void build (){
        builder = new CommandBuild();
        builder.execute(context, "build", new String[]{selectedBuildCity.getName()});
        build = false;
    }

}

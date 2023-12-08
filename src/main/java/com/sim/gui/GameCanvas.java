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

        gc.drawImage(pond,375,125,150,150);
        gc.drawImage(pond,80,150,150,150);

        gc.drawImage(mountain,350,10,100,100);
        gc.drawImage(mountain,400,20,100,100);
        gc.drawImage(mountain,320,30,100,100);

        gc.drawImage(mountain,430,260,100,100);
        gc.drawImage(mountain,460,270,100,100);
        gc.drawImage(mountain,400,280,100,100);

        for (Road road : world.roads) {
            CitySpace from = road.connectsTo[0];
            CitySpace to = road.connectsTo[1];

            gc.setLineWidth(30);
            gc.setStroke(Color.BLACK);

            gc.strokeLine(from.getX() * 5 + 60, from.getY() * 20 + 70, to.getX() * 5 + 60, to.getY() * 20 + 70);

            gc.setLineWidth(3);
            gc.setLineDashes(30);
            gc.setStroke(Color.WHITE);

            gc.strokeLine(from.getX() * 5 + 60, from.getY() * 20 + 70, to.getX() * 5 + 60, to.getY() * 20 + 70);
        }


        for (CitySpace cityspaces: world.spaces) {
                if (selectedCity == cityspaces) {
                    gc.setFill(Color.GRAY);
                } else {
                    gc.setFill(Color.DARKKHAKI);
                }
                gc.fillOval(cityspaces.getX() * 5, cityspaces.getY() * 20 + 35, 125, 75);
                if (cityspaces.getName() == "Aurelia") {
                    gc.drawImage(capitalCity, cityspaces.getX() * 5 + 25, cityspaces.getY() * 20 + 10, 75, 75);
                } else {
                    gc.drawImage(city, cityspaces.getX() * 5, cityspaces.getY() * 20, 125, 125);
                }

                gc.setFill(Color.BLACK);
                gc.fillText(cityspaces.getName(), cityspaces.getX() * 5 + 30, cityspaces.getY() * 20 + 100);
            }

        if (build){
            for (CitySpace buildableCity : highlightedCities) {
                if (buildableCity.getName() == "Aurelia"){
                    gc.setFill(Color.ORANGE);
                    gc.fillOval(buildableCity.getX()*5,buildableCity.getY()*20+35,125,75);
                    gc.drawImage(capitalCity,buildableCity.getX()*5+25, buildableCity.getY()*20+10,75,75);
                    gc.setFill(Color.BLACK);
                    gc.fillText(buildableCity.getName(),buildableCity.getX()*5+30, buildableCity.getY()*20+100 );
                }

                else {
                    gc.setFill(Color.ORANGE);
                    gc.fillOval(buildableCity.getX()*5,buildableCity.getY()*20+35,125,75);
                    gc.drawImage(city, buildableCity.getX() * 5, buildableCity.getY() * 20, 125, 125);
                    gc.setFill(Color.BLACK);
                    gc.fillText(buildableCity.getName(), buildableCity.getX() * 5 + 30, buildableCity.getY() * 20 + 100);
                }
            }
        }


    }

    public void checkBuildClicked(){
        build = true;
        for (String city : selectedCity.edges.keySet()) {
            Node node = selectedCity.edges.get(city);
            if (node instanceof CitySpace){
                CitySpace city2 = (CitySpace) node;
                if (selectedCity.hasAccessTo(city2)) {
                    continue;
                }
                highlightedCities.add((CitySpace) node);
            }
        }
        redraw();
    }

    public CitySpace checkClick(double x, double y) {
        if (build){
            for (CitySpace city : world.spaces) {
                if (x >= city.getX() * 5 + 20 && x <= city.getX() * 5 + 100 && y >= city.getY() * 20+35 && y <= city.getY()*20+110) {
                    selectedBuildCity = city;
                    redraw();
                    return selectedBuildCity;
                }
            }
        } else{
            for (CitySpace city : world.spaces) {
                if (x >= city.getX() * 5+10 && x <= city.getX() * 5 + 120 && y >= city.getY() * 20+35 && y <= city.getY()*20+110) {
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


    void nextTutorial(){

    }

    void build (){
        builder = new CommandBuild();
        builder.execute(context, "build", new String[]{selectedBuildCity.getName()});
        build = false;
    }

}

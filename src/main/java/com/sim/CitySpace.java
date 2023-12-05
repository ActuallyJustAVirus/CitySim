package com.sim;
import java.util.*;

public class CitySpace extends Space {
    int x, y;
    int population;
    private List<Road> roads = new ArrayList<>();
    private HashMap<String, Integer> hasAccess = new HashMap<>();


    CitySpace(String name, int x, int y, boolean hasHospital, boolean hasPoliceStation, boolean hasFireStation, boolean hasSchool, boolean hasWorkplace, int population) {
        super(name);
        this.x = x;
        this.y = y;
        this.population = population;
        if (hasHospital) {
            hasAccess.put("Hospital", 3);
        } else {
            hasAccess.put("Hospital", 0);
        }
        if (hasPoliceStation) {
            hasAccess.put("Police Station", 3);
        } else {
            hasAccess.put("Police Station", 0);
        }
        if (hasFireStation) {
            hasAccess.put("Fire Station", 3);
        } else {
            hasAccess.put("Fire Station", 0);
        }
        if (hasSchool) {
            hasAccess.put("School", 3);
        } else {
            hasAccess.put("School", 0);
        }
        if (hasWorkplace) {
            hasAccess.put("Workplace", 3);
        } else {
            hasAccess.put("Workplace", 0);
        }
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public String getName(){
        return this.name;
    }

    public String getInfo(){
        String info = "";
        info += "Name: " + name + "\n";
        info += "Population: " + population + "\n";
        info += "Has access to: \n";
        for (Map.Entry<String, Integer> entry : hasAccess.entrySet()) {
            info += entry.getKey() + ": ";
            switch (entry.getValue()) {
                case 0:
                    info += "No access\n";
                    break;
                case 1:
                    info += "Limited access\n";
                    break;
                case 2:
                    info += "Good access\n";
                    break;
                case 3:
                    info += "Excellent access\n";
                    break;
                default:
                    break;
            }
        }
        info += "Points: " + getPoints() + "\n";
        return info;
    }

    public int getPoints(){
        int points = 0;
        for (Map.Entry<String, Integer> entry : hasAccess.entrySet()) {
            if (entry.getValue() > 0) {
                int pointsToAdd = (population/1000) * entry.getValue(); // TODO: Make this better
                points += pointsToAdd; 
            }
        }
        return points;
    }

    public void updateAccess(String type, int amount){
        if (hasAccess.containsKey(type) && hasAccess.get(type) < amount) {
            hasAccess.put(type, amount);
        }
        if (amount > 1) {
            for (Road road : roads) {
                CitySpace otherCity = roadConnectsTo(road);
                otherCity.updateAccess(type, amount - 1);
            }
        }
    }

    public void addRoad(Road road) {
        if (roads == null) {
            roads = new ArrayList<>();
        }
        roads.add(road);
        for (Map.Entry<String, Integer> entry : hasAccess.entrySet()) {
            if (entry.getValue() > 1) {
                updateAccess(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public void welcome() {
        System.out.println("You are in " + name);
        
    }

    private CitySpace roadConnectsTo(Road road) {
        if (road.connectsTo[0] == this) {
            return road.connectsTo[1];
        } else {
            return road.connectsTo[0];
        }
    }

    public boolean hasAccessTo(CitySpace city) {
        for (Road road : roads) {
            if (roadConnectsTo(road) == city) {
                return true;
            }
        }
        return false;
    }
}

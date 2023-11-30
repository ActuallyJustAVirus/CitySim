package com.sim;
import java.util.*;

public class CitySpace extends Space {
    int x, y;
    public List<Road> roads;
    HashMap<String, Boolean> institutions = new HashMap<>();
    HashMap<String, Boolean> hasAccess = new HashMap<>();


    CitySpace(String name, int x, int y, boolean hasHospital, boolean hasPoliceStation, boolean hasFireStation, boolean hasSchool, boolean hasWorkplace) {
        super(name);
        this.x = x;
        this.y = y;
        institutions.put("Hospital", hasHospital);
        institutions.put("Police Station", hasPoliceStation);
        institutions.put("Fire Station", hasFireStation);
        institutions.put("School", hasSchool);
        institutions.put("Workplace", hasWorkplace);
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

    public int getPoints(){
        int points = 0;
        for (Map.Entry<String, Boolean> entry : institutions.entrySet()) {
            if (entry.getValue()) {
                points++; // TODO: Figure out how to add points based on population.
                hasAccess.put(entry.getKey(), true);
                continue;
            }
            for (Road road : roads) {
                CitySpace city = roadConnectsTo(road);
                if (city.institutions.get(entry.getKey())) {
                    points++; // TODO: Figure out how to add points based on population.
                    hasAccess.put(entry.getKey(), true);
                }
            }
        }
        return points;
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
}

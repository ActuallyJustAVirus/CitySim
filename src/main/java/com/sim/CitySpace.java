package com.sim;
import java.util.*;

public class CitySpace extends Space {
    int x, y;
    List<Road> roads;
    boolean hasHospital, hasPoliceStation, hasFireStation, hasSchool, hasWorkplace;
    private List<String> accesspoints;

    CitySpace(String name, int x, int y, boolean hasHospital, boolean hasPoliceStation, boolean hasFireStation, boolean hasSchool, boolean hasWorkplace) {
        super(name);
        this.x = x;
        this.y = y;
        this.hasHospital = hasHospital;
        this.hasPoliceStation = hasPoliceStation;
        this.hasFireStation = hasFireStation;
        this.hasSchool = hasSchool;
        this.hasWorkplace = hasWorkplace;
        this.accesspoints=new ArrayList<>();
        // her opretter jeg en tom list kaldet accespoints.

    }
        public void addAccesspoints(String accesspoint){
            accesspoints.add(accesspoint);
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

        public List<String> getAccesspoints(){
            return accesspoints;
        }


    // Added booleans for institutions. TODO: Figure out what cities has access to what institutions.
    @Override
    public void welcome() {
        System.out.println("You are in " + name);
        if(!accesspoints.isEmpty()){
            System.out.println("Access points in this city");
         for(String accespoint:accesspoints){
             System.out.println("-"+ accesspoints);
         }

        }

    }
}

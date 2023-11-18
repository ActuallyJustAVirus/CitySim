package com.sim;
import java.util.*;

public class CitySpace extends Space {
    int x, y;
    List<Road> roads;
    boolean hasHospital, hasPoliceStation, hasFireStation, hasSchool, hasWorkplace;

    CitySpace(String name, int x, int y, boolean hasHospital, boolean hasPoliceStation, boolean hasFireStation, boolean hasSchool, boolean hasWorkplace) {
        super(name);
        this.x = x;
        this.y = y;
        this.hasHospital = hasHospital;
        this.hasPoliceStation = hasPoliceStation;
        this.hasFireStation = hasFireStation;
        this.hasSchool = hasSchool;
        this.hasWorkplace = hasWorkplace;
    }

    // Added booleans for institutions. TODO: Figure out what cities has access to what institutions.
    @Override
    public void welcome() {
        System.out.println("You are in " + name);
    }
}

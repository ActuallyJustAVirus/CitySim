package com.sim;

public class Road {
    public CitySpace[] connectsTo = new CitySpace[2];

    public Road (CitySpace city1, CitySpace city2){
        connectsTo[0] = city1;
        connectsTo[1] = city2;
    }

}

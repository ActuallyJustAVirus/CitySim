package game;

public class Road {
    public SpaceCity[] connectsTo = new SpaceCity[2];

    public Road (SpaceCity city1, SpaceCity city2){
        connectsTo[0] = city1;
        connectsTo[1] = city2;
    }

}

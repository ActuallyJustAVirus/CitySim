package game;

public class SpaceMap extends Space{
    World world;

    SpaceMap(World world) {
        super("Map");
        this.world = world;
    }

    @Override
    public void welcome() {
        world.printMap();
    }
    
}

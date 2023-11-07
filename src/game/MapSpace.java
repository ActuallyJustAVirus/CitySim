package game;

public class MapSpace extends Space{
    World world;

    MapSpace(World world) {
        super("Map");
        this.world = world;
    }

    @Override
    public void welcome() {
        world.printMap();
    }
    
}

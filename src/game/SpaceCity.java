package game;

public class SpaceCity extends Space {

    SpaceCity(String name) {
        super(name);
    }

    @Override
    public void welcome() {
        System.out.println("You are in " + name);
    }
}

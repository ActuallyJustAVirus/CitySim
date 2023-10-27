package game;
/* Context class to hold all context relevant to a session.
 */

public class Context {
    Space currentSpace;
    boolean done = false;

    Context(Space node) {
        currentSpace = node;
    }

    public Space getCurrentSpace() {
        return currentSpace;
    }

    public void transition(String direction) {
        Space nextSpace = currentSpace.followEdge(direction);
        if (nextSpace == null) {
            System.out.println("You are confused, and walk in a circle looking for '" + direction
                    + "'. In the end you give up 😩");
        } else {
            currentSpace.goodbye();
            currentSpace = nextSpace;
            currentSpace.welcome();
        }
    }

    public void makeDone() {
        done = true;
    }

    public boolean isDone() {
        return done;
    }

    public void NextTurn (){  /*Method to go to next turn. */
        System.out.println("´1 month later...");
    }
}

package game;
/* Context class to hold all context relevant to a session.
 */

public class Context {
    Space currentSpace;
    boolean done = false;

    int balance = 0;

    Context(Space node) {
        currentSpace = node;
    }

    public Space getCurrentSpace() {
        return currentSpace;
    }

    public void transition(String direction) {
        Space next = currentSpace.followEdge(direction);
        if (next == null) {
            System.out.println("You are confused, and walk in a circle looking for '" + direction
                    + "'. In the end you give up 😩");
        } else {
            currentSpace.goodbye();
            currentSpace = next;
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

        int i = 1000000;

        System.out.println(balance+i);
    }
}

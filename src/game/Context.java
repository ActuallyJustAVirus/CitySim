package game;
/* Context class to hold all context relevant to a session.
 */

/**
 * Context klasen holder styr på alle de forskellige rum og om spillet er færdigt.
 */
public class Context {
    /**
     * currentSpace er det rum spilleren er i.
     */
    Space currentSpace;
    /**
     * done er en boolean, som bliver brugt til at finde ud af om spillet er færdigt.
     */
    boolean done = false;

    Context(Space startSpace) {
        currentSpace = startSpace;
    }

    public Space getCurrentSpace() {
        return currentSpace;
    }

    /**
     * følger en exit i den vaglte retning. og printer en besked hvis der ikke er en exit i den retning.
     * @param direction er den vaglte retning man skriver i terminalen
     */
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

    /**
     * makeDone gør done til true, så spillet er færdigt.
     */
    public void makeDone() {
        done = true;
    }

    /**
     * isDone returnere done. aka om spillet er færdigt.
     * @return
     */
    public boolean isDone() {
        return done;
    }
}

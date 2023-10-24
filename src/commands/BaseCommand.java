package commands;
/* Baseclass for commands
 */

public abstract class BaseCommand implements Command {
    String description = "Undocumented";

    protected boolean guardEq(String[] parameters, int bound) {
        return parameters.length != bound;
    }

    public String getDescription() {
        return description;
    }
}

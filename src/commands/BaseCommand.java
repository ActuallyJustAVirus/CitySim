package commands;
/* Baseclass for commands
 */

public class BaseCommand {
    String description = "Undocumented";

    protected boolean guardEq(String[] parameters, int bound) {
        return parameters.length != bound;
    }

    public String getDescription() {
        return description;
    }
}

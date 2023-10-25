package commands;
/* Baseclass for commands
 */

 /**
  * Baseclass for commands
  */
public abstract class BaseCommand implements Command {
    String description = "Undocumented";

    /**
     * chekker om der er lige mange parametre som bound
     * @param parameters er det array af parametre der er blevet skrevet
     * @param bound er antallet af parametre der skal være for at kommandoen kan udføres
     * @return
     */
    protected boolean guardEq(String[] parameters, int bound) {
        return parameters.length != bound;
    }

    public String getDescription() {
        return description;
    }
}

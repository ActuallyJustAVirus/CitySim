package game;
/* Command registry
 */

import java.util.HashMap;
import java.util.Map;

import commands.Command;
/**
 * Command registry
 * Denne klasse bliver brugt til at registrere alle de forskellige kommandoer, som spilleren kan bruge.
 */
public class Registry {
    Context context;
    /**
     * fallback er en kommando, som bliver brugt, hvis spilleren skriver en kommando, som ikke er registreret.
     */
    Command fallback;
    /**
     * commands er en HashMap, som bliver brugt til at registrere alle de forskellige kommandoer, som spilleren kan bruge.
     * et hashmap er en liste, som består af en key(her String) og en value(her Command).
     * key er navnet på kommandoen, og value er kommandoen.
     */
    Map<String, Command> commands = new HashMap<String, Command>();

    Registry(Context context, Command fallback) {
        this.context = context;
        this.fallback = fallback;
    }

    /**
     * register er en metode, som bliver brugt til at registrere en kommando.
     * @param name er det man skriver for at bruge kommandoen.
     * @param command er kommandoen.
     */
    public void register(String name, Command command) {
        commands.put(name, command);
    }

    /**
     * dispatch er en metode, som bliver brugt til at finde ud af, hvilken kommando spilleren har skrevet.
     * @param line er det spilleren har skrevet.
     */
    public void dispatch(String line) {
        // funktionen split deler en sting op i et array af stings
        // ex: new String("ThisaIsaText").split("a") -> {"This", "Is", "Tesxt"}
        String[] elements = line.split(" ");
        String command = elements[0];
        String[] parameters = getParameters(elements);
        Command handler = getCommand(command);
        (handler == null ? fallback : handler).execute(context, command, parameters);
        /* simple version: af linjen over
        if (handler == null) {
            fallback.execute(context, command, parameters);
        } else {
            handler.execute(context, command, parameters);
        }
        */
    }

    /**
     * bliver brugt til at finde en kommando i commands hashmapet.
     * @param commandName er det man skriver for at bruge kommandoen.
     * @return
     */
    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

    /**
     * returnerer alle kommandoer i commands hashmapet.
     * @return
     */
    public String[] getCommandNames() {
        return commands.keySet().toArray(new String[0]);
    }

    // helpers

    /**
     * Laver et array der indeholder alle andre elmenterne end det første fra input arrayet <br>
     * ex: new String[]{"a", "b", "c"} -> new String[]{"b", "c"}
     * @param input
     * @return
     */
    private String[] getParameters(String[] input) {
        String[] output = new String[input.length - 1];
        for (int i = 0; i < output.length; i++) {
            output[i] = input[i + 1];
        }
        return output;
    }
}

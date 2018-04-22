package core;

import commands.Command;

import java.util.HashMap;

public class commandHandler {

    public static final commandParser parser = new commandParser();
    public static HashMap<String, Command> commands = new HashMap<>();

    public static void handleCommand(commandParser.commandContainer cmd) {
        String invoke = cmd.invoke;
        invoke=invoke.toLowerCase();
        if (commands.containsKey(invoke)) {

            boolean safe = commands.get(invoke).called(cmd.args, cmd.event);

            if (!safe) {
                commands.get(invoke).action(cmd.args, cmd.event);
                commands.get(invoke).executed(safe, cmd.event);
            } else {
                commands.get(invoke).executed(safe, cmd.event);
            }
        }
    }
}
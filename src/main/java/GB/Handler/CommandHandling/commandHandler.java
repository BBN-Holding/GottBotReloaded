package GB.Handler.CommandHandling;

import GB.GottBot;

import java.util.HashMap;

public class commandHandler {

    public static final commandParser parser = new commandParser();
    public static HashMap<String, Command> commands = new HashMap<>();

    public static void handleCommand(commandParser.commandContainer cmd, String userid, String serverid) {
        String invoke = cmd.invoke;
        invoke = invoke.toLowerCase();
        // TODO: Create serveruser table
        // TODO: register in message
        String permissions = "command.*";
        if (permissions.contains("command." + cmd.invoke) || permissions.contains("command.*")) {
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
}
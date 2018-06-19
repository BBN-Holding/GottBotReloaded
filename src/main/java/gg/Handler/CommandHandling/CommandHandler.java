package gb.Handler.CommandHandling;

import gb.GottBot;
import java.util.HashMap;

public class CommandHandler {

    public static final CommandParser parser = new CommandParser();
    public static HashMap<String, Command> commands = new HashMap<>();
    public static void handleCommand(CommandParser.commandContainer cmd) {
        String invoke = cmd.invoke;
        invoke = invoke.toLowerCase();
        if (commands.containsKey(invoke)) {
            if (commands.get(invoke).getClass().getPackageName().equals("gb.commands.owner")) {
                if (GottBot.getConfig().getOwnerID().contains(cmd.event.getAuthor().getId())) {
                    commands.get(invoke).action(cmd.args, cmd.event);
                }
            } else
            commands.get(invoke).action(cmd.args, cmd.event);
        }
    }
}
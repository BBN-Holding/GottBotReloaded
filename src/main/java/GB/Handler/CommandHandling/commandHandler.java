package GB.Handler.CommandHandling;

import GB.GottBot;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class commandHandler {

    public static final commandParser parser = new commandParser();
    public static HashMap<String, Command> commands = new HashMap<>();
    public static void handleCommand(commandParser.commandContainer cmd) {
        String invoke = cmd.invoke;
        invoke = invoke.toLowerCase();
        if (commands.containsKey(invoke)) {
            if (commands.get(invoke).getClass().getPackageName().equals("GB.commands.owner")) {
                if (GottBot.getConfig().getOwnerID().contains(cmd.event.getAuthor().getId())) {
                    commands.get(invoke).action(cmd.args, cmd.event);
                }
            } else
            commands.get(invoke).action(cmd.args, cmd.event);
        }
    }
}
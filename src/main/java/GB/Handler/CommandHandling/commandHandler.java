package GB.Handler.CommandHandling;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class commandHandler {

    public static final commandParser parser = new commandParser();
    public static HashMap<String, Command> commands = new HashMap<>();
    public static void handleCommand(commandParser.commandContainer cmd) {
        String invoke = cmd.invoke;
        invoke = invoke.toLowerCase();
        commands.get(invoke).action(cmd.args, cmd.event);
    }
}
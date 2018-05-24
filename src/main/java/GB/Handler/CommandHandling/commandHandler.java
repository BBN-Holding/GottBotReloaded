package GB.Handler.CommandHandling;

import GB.Pluginmanager.Plugin;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.HashMap;

public class commandHandler {

    public static final commandParser parser = new commandParser();
    public static HashMap<String, Plugin> commands = new HashMap<>();
    public static void handleCommand(commandParser.commandContainer cmd, MessageReceivedEvent event) {
        String invoke = cmd.invoke;
        invoke = invoke.toLowerCase();
        String permissions = "command.*";
        if (permissions.contains("command." + cmd.invoke) || permissions.contains("command.*") || event.getGuild().getOwner().getUser().getId().equals(event.getAuthor().getId())) {
            if (commands.containsKey(invoke)) {
                    commands.get(invoke).onCommand(cmd.args, cmd.event);
            }
        }
    }
}
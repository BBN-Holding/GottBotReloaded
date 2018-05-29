package GB.Handler.CommandHandling;

import GB.Pluginmanager.Plugin;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class commandHandler {

    public static final commandParser parser = new commandParser();
    public static ArrayList<Plugin> commands = new ArrayList<>();
    public static void handleCommand(commandParser.commandContainer cmd, MessageReceivedEvent event) {
        String invoke = cmd.invoke;
        invoke = invoke.toLowerCase();
        int i =0;
        while (commands.size()>i) {
            commands.get(i).onCommand(invoke, cmd.args, cmd.event);
            i++;
        }
    }
}
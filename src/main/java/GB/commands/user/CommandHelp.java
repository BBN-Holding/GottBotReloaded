package GB.commands.user;

import GB.Handler.CommandHandling.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandHelp implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        // TODO: Do it
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}

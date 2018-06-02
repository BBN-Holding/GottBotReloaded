package GB.Handler.CommandHandling;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface Command {
    String[] Aliases();
    void action(String[] args, MessageReceivedEvent event);

}

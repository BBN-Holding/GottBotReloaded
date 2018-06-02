package GB.Handler.CommandHandling;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface Command {

    void action(String[] args, MessageReceivedEvent event);

}

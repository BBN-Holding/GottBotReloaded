package GB.commands.owner;

import GB.Handler.CommandHandling.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandTest implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendMessage("Test angekommen").queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}

package commands.botowner;

import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandWebhook implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        // Hier soll der WebHook command entstehen... Danke ForYaSee für die Idee
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}

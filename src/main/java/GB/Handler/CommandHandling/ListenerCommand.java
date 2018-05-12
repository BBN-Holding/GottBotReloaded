package GB.Handler.CommandHandling;

import GB.GottBot;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ListenerCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        String prefix = GottBot.getInfo().getPrefix(event.getGuild().getId());
        if (!event.getAuthor().isBot()) {
            if (event.getMessage().getContentRaw().startsWith(event.getJDA().getSelfUser().getAsMention())) {
                handle(event, event.getJDA().getSelfUser().getAsMention());
            } else if (event.getMessage().getContentRaw().startsWith(prefix)) {
                handle(event, prefix);
            }
        }
    }

    private void handle(MessageReceivedEvent event, String prefix) {
        commandHandler.handleCommand(commandHandler.parser.parse(prefix,event.getMessage().getContentRaw(), event));
    }
}

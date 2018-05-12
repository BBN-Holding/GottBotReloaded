package GB.Handler.CommandHandling;

import GB.GottBot;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class commandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            String Prefix = GottBot.getInfo().getPrefix(event.getGuild().getId());
            if (event.getMessage().getContentRaw().startsWith(event.getJDA().getSelfUser().getAsMention())) {
                handle(event, event.getJDA().getSelfUser().getAsMention());
            } else if (event.getMessage().getContentRaw().startsWith(Prefix)) {
                handle(event, Prefix);
            }
        }
    }

    private void handle(MessageReceivedEvent event, String prefix) {
        commandHandler.handleCommand(commandHandler.parser.parse(prefix,event.getMessage().getContentRaw(), event));
    }

}

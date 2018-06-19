package gb.Handler.CommandHandling;

import gb.GottBot;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class commandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannelType().isGuild()) {
            if (event.getTextChannel().getGuild().getSelfMember().hasPermission(event.getTextChannel(), Permission.MESSAGE_WRITE) && event.getTextChannel().getGuild().getSelfMember().hasPermission(event.getTextChannel(), Permission.MESSAGE_HISTORY) && event.getTextChannel().getGuild().getSelfMember().hasPermission(event.getTextChannel(), Permission.MESSAGE_EMBED_LINKS)) {
                if (!event.getAuthor().isBot()) {
                    String prefix = GottBot.getMessage().getPrefix(event.getGuild().getId());
                    if (event.getMessage().getContentRaw().startsWith(event.getJDA().getSelfUser().getAsMention())) {
                        handle(event, event.getJDA().getSelfUser().getAsMention());
                    } else if (event.getMessage().getContentRaw().startsWith(prefix)) {
                        handle(event, prefix);
                    }
                }
            }
        }
    }

    private void handle(MessageReceivedEvent event, String prefix) {
        CommandHandler.handleCommand(CommandHandler.parser.parse(prefix, event.getMessage().getContentRaw(), event));
    }
}

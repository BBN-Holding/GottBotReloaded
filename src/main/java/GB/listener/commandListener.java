package GB.listener;

import GB.GottBot;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.MessageUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class commandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String Prefix = GottBot.getInfo().getPrefix(event.getGuild().getId());
        if (event.getMessage().getContentRaw().startsWith(Prefix)) {
            
        }
    }

    @Override
    public void onMessageUpdate(MessageUpdateEvent event) {

    }

}

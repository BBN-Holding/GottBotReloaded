package GB.listener;

import GB.GottBot;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Message extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // registerServer
        if (GottBot.getDB().get("server", "serverid", event.getGuild().getId(), "serverid")==null) {
            GottBot.getDB().insertServer(event.getGuild().getId());
            System.out.println("Ich hab 1 server eingetragen... "+ event.getGuild());
        }
    }
}

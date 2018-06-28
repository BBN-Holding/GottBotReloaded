package gb.listener;

import gb.GottBot;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            // registerServer
            if (GottBot.getDB().get("server", "serverid", event.getGuild().getId(), "serverid") == null) {
                GottBot.getDB().insertServer(event.getGuild().getId());
                GottBot.getLogger().log("ein neuer Server!: " + event.getGuild().getName() + " (" + event.getGuild().getId() + ")");
            }
            // registerUser
            if (GottBot.getDB().get("user", "userid", event.getAuthor().getId(), "userid") == null) {
                GottBot.getDB().insertUser(event.getAuthor().getId());
                GottBot.getLogger().log("ein neuer User!: " + event.getAuthor().getName() + " (" + event.getAuthor().getId() + ")");
            }
            // registerPermissions
            if (GottBot.getDB().get("serveruser", "serveruserid", event.getGuild().getId() + " " + event.getAuthor().getId(), "serveruserid") == null) {
                GottBot.getDB().insertServerUser(event.getAuthor().getId(), event.getGuild().getId());
                GottBot.getLogger().log("neue User permissions eingetragen! " + event.getAuthor().getName() + " (" + event.getAuthor().getId() + ") on " + event.getGuild().getName() + " (" + event.getGuild().getId() + ")");
            }
        }
    }
}

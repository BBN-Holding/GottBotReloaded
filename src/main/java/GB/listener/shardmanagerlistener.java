package GB.listener;

import GB.GottBot;
import net.dv8tion.jda.core.entities.Icon;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.Webhook;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;

public class shardmanagerlistener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            if (GottBot.getDB().get("portal", "serverid", event.getGuild().getId(), "connected").equals("true")) {
                if (GottBot.getDB().get("portal", "serverid", event.getGuild().getId(), "channel").equals(event.getTextChannel().getId())) {
                    String guildsconnected=GottBot.getDB().get("portal", "serverid", event.getGuild().getId(), "connectedservers");
                    String[] guilds = guildsconnected.split(":");
                    ArrayList<TextChannel> textChannels = new ArrayList<TextChannel>();
                    for (String guild:guilds) {
                        String channel = GottBot.getDB().get("portal", "serverid", guild, "channel");
                        textChannels.add(event.getJDA().getTextChannelById(channel));
                    }
                    for (TextChannel channel:textChannels) {
                        // TODO: Add Avatar
                        Webhook webhook = channel.createWebhook(event.getAuthor().getName()+" ("+event.getGuild().getName()+")").complete();
                        webhook.getChannel().sendMessage(event.getMessage().getContentRaw()).queue();
                        webhook.delete().queue();
                    }
                }
            }
        }
    }
}

package listener;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import stuff.DATA;

public class PrivateMessage extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            if (event.getChannelType().equals(ChannelType.PRIVATE)) {

                String Content = event.getMessage().getContentDisplay();
                String Author = event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator();

                PrivateChannel Hax = event.getJDA().getUserById(DATA.Hax).openPrivateChannel().complete();
                PrivateChannel Skidder = event.getJDA().getUserById(DATA.Skidder).openPrivateChannel().complete();

                Hax.sendMessage(new EmbedBuilder().setTitle("Neue Private Nachricht von " + Author + " .").setDescription(Content).build()).queue();
                Skidder.sendMessage(new EmbedBuilder().setTitle("Neue Private Nachricht von " + Author + " .").setDescription(Content).build()).queue();

            }
        }
    }

}

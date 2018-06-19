package gb.listener;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MentionListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        // TODO: translate
        if (event.getMessage().getMentionedMembers().size()!=0) {
            if (event.getMessage().getMentionedMembers().contains(event.getGuild().getSelfMember()))
            event.getMessage().getTextChannel().sendMessage("Hello i'm here ").queue();
        }
    }
}

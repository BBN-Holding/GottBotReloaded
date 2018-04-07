package commands.botowner;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import commands.Command;
import util.Embed;

public class CommandInvite implements Command {




    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
            String HALLO = event.getJDA().getGuildById(args[0]).getTextChannels().get(0).createInvite().setMaxAge(400).complete().getURL();

            event.getTextChannel().sendMessage(Embed.normal(":information_source: Invite", "Invite: " + HALLO).build()).queue();

        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }

}
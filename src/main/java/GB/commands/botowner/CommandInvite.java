package GB.commands.botowner;

import GB.Handler;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import GB.commands.Command;

public class CommandInvite implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
            String HALLO = event.getJDA().getGuildById(args[0]).getTextChannels().get(0).createInvite().setMaxAge(400).complete().getURL();

            event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("botowner.invite.title", "botowner.invite.text", HALLO, "normal", event)).queue();

        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }

}
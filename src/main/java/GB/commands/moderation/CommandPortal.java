package GB.commands.moderation;

import GB.GottBot;
import GB.Handler.CommandHandling.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandPortal implements Command {
    @Override
    public String[] Aliases() {
        return new String[0];
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<1) {
            event.getTextChannel().sendMessage(GottBot.getMessage().getCommandTemplate(Aliases(), "", "")).queue();
        } else {

        }
    }
}

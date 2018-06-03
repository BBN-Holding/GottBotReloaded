package GB.commands.usercommands;

import GB.GottBot;
import GB.Handler.CommandHandling.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandGuildInfo implements Command {
    @Override
    public String[] Aliases() {
        return new String[]{"guildinfo", "serverinfo", "gi", "si"};
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendMessage(
                new EmbedBuilder().setTitle(GottBot.getMessage().getString("commands.user.guildinfo.title", event)).build()
        ).queue();
    }
}

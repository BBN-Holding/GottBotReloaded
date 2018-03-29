package commands.botowner;

import commands.Command;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Embed;

public class CommandSetLevel implements Command {



    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
            MySQL.update("user", "level", args[1], "id", args[0]);
            new MessageBuilder().setEmbed(Embed.success(MessageHandler.get(event.getAuthor()).getString("setlvltitel"), MessageHandler.get(event.getAuthor()).getString("setlvldescription")).build()).build();
        }
    }
    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}

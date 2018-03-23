package commands.botowner;

import commands.Command;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandSetLevel implements Command {



    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Handler.get(event.getAuthor())) {
            MySQL.update("user", "level", args[1], "id", args[0]);
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("setlvltitel").replaceAll("gb.", MessageHandler.getprefix(event.getGuild())))
                    .setDescription(MessageHandler.get(event.getAuthor()).getString("setlvldescription")).build()).queue();
        }
    }
    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}

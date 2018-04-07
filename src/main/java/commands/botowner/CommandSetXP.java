package commands.botowner;

import commands.Command;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Embed;

public class CommandSetXP implements Command {



    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
            MySQL.update("user", "xp", args[1], "id", args[0]);
           event.getTextChannel().sendMessage(Embed.success(MessageHandler.get(event.getAuthor()).getString("setxptitel"), MessageHandler.get(event.getAuthor()).getString("setxpdescription")).build()).queue();
        }
    }
    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}

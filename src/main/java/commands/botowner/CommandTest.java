package commands.botowner;

import commands.Command;
import core.MessageHandler;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Embed;


public class CommandTest implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
           event.getTextChannel().sendMessage(Embed.normal(MessageHandler.get(event.getAuthor()).getString("testtitel"), MessageHandler.get(event.getAuthor()).getString("testtext")).build()).queue();





        } else {
            event.getTextChannel().sendMessage(Embed.error(MessageHandler.get(event.getAuthor()).getString("nopermstitel"), MessageHandler.get(event.getAuthor()).getString("nopermstext")).build()).queue();
        }


    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}

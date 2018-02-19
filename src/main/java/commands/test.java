package commands;

import core.MessageHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class test implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        MessageHandler.in(event.getAuthor(), true, "Test", event.getGuild());
        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.Titel).setDescription(MessageHandler.Message).build()).queue(); // print wenn user language is english Test english wen user language is german Test german
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}

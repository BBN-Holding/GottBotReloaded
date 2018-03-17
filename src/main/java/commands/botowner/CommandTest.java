package commands.botowner;

import commands.Command;
import core.MessageHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandTest implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Handler.get(event.getAuthor())) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("testtitel"))
                    .setDescription(MessageHandler.get(event.getAuthor()).getString("testtext")).build()).queue(); // print wenn user CommandLanguage is english Test english wen user CommandLanguage is german Test german

        } else event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("nopermstitel"))
                .setDescription(MessageHandler.get(event.getAuthor()).getString("nopermstext")).build()).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}

package commands.botowner;

import commands.Command;
import core.MenuHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;

public class CommandHelp3 implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        Message message =event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Loading...").setDescription("loading...").build()).complete();
        MySQL.insert("helpmenu", "id`,`message", event.getAuthor().getId()+"','"+message.getId());
        event.getTextChannel().editMessageById(message.getId(),MenuHandler.getMessage("\uD83D\uDD19", new EmbedBuilder().setTitle("HelpMenu - ").build())).queue();
        List<String> list = MenuHandler.getemote("\uD83D\uDD19", new EmbedBuilder().setTitle("HelpMenu").build());
        while (list.size() > 0) {
            message.addReaction(list.get(0)).queue();
            list.remove(0);
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}

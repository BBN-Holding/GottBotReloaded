package commands.usercommands;

import commands.Command;
import core.MenuHandler;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.List;

public class CommandHelp3 implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            Message message = event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Loading...").setDescription("loading...").build()).complete();
            MySQL.insert("helpmenu", "id`,`message", event.getAuthor().getId() + "','" + message.getId());
            event.getTextChannel().editMessageById(message.getId(), MenuHandler.getMessage("\uD83D\uDD19", new EmbedBuilder().setTitle("HelpMenu - ").build())).queue();
            List<String> list = MenuHandler.getemote("\uD83D\uDD19", new EmbedBuilder().setTitle("HelpMenu").build());
            while (list.size() > 0) {
                message.addReaction(list.get(0)).queue();
                list.remove(0);
            }
        } else {
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("helptitel"))
                    .setDescription(MessageHandler.get(event.getAuthor()).getString("helptext").replaceAll("gb.", MessageHandler.getprefix(event.getGuild()))).setColor(Color.CYAN).build()).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}
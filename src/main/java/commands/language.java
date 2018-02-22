package commands;

import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import stuff.SECRETS;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static stuff.DATA.url;

public class language implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<1) {
            try {
                MessageHandler.in(event.getMember().getUser(), true, "lang", event.getGuild());
                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.Titel).setDescription(MessageHandler.Message).setColor(Color.CYAN).build()).queue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (args[0].equalsIgnoreCase("german")||args[0].equalsIgnoreCase("english")) {
            try {
                MySQL.update("user", "language", args[0], "ID", event.getAuthor().getId());
                MessageHandler.in(event.getMember().getUser(), true, "langedit", event.getGuild());
                event.getTextChannel().sendMessage(new EmbedBuilder().setDescription(MessageHandler.Message).setTitle(MessageHandler.Titel).setColor(Color.green).build()).queue();
            } catch (Exception e) {
                e.printStackTrace();
            }
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

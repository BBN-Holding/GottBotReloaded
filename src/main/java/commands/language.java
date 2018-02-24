package commands;

import core.MessageHandler;
import core.MySQL;
import listener.Message;
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
                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("languagetitel"))
                        .setDescription(MessageHandler.get(event.getAuthor()).getString("languagetext").replaceAll("gb.", MessageHandler.getprefix(event.getGuild()))).setColor(Color.CYAN).build()).queue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (args[0].equalsIgnoreCase("list")) {
            try {

                String out=MySQL.getallstring("language", "name").replaceAll(" ", ", ");
                event.getTextChannel().sendMessage(new EmbedBuilder().setDescription(MessageHandler.get(event.getAuthor()).getString("languagelisttext")+":\n"+out)
                        .setTitle(MessageHandler.get(event.getAuthor()).getString("languagelisttitel")).build()).queue();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (args[0].length()==2) {
            try {

                if (MySQL.getallstring("language", "name").contains(args[0].toLowerCase())) {
                    MySQL.update("user", "language", args[0].toLowerCase(), "ID", event.getAuthor().getId());
                    event.getTextChannel().sendMessage(new EmbedBuilder().setDescription(MessageHandler.get(event.getAuthor()).getString("languageedittext"))
                            .setTitle(MessageHandler.get(event.getAuthor()).getString("languageedittitel")).setColor(Color.green).build()).queue();
                }
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

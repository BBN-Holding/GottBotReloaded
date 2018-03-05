package commands.usercommands;

import commands.Command;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class CommandLanguage implements Command {
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

                String out=MySQL.getallstring("CommandLanguage", "name").replaceAll(" ", ", ");
                event.getTextChannel().sendMessage(new EmbedBuilder().setDescription(MessageHandler.get(event.getAuthor()).getString("languagelisttext")+":\n"+out)
                        .setTitle(MessageHandler.get(event.getAuthor()).getString("languagelisttitel")).build()).queue();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (args[0].length()==2) {
            try {

                if (MySQL.getallstring("CommandLanguage", "name").contains(args[0].toLowerCase())) {
                    MySQL.update("user", "CommandLanguage", args[0].toLowerCase(), "ID", event.getAuthor().getId());
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

package commands;

import com.mysql.cj.mysqla.authentication.MysqlaAuthenticationProvider;
import core.MessageHandler;
import core.MySQL;
import listener.Message;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import stuff.SECRETS;

import java.awt.*;
import java.sql.*;

import static stuff.DATA.url;

public class prefix implements Command {
    boolean Role=false;
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        int i =0;
        while (event.getMember().getRoles().size()-1>=i) {
            if (event.getMember().getRoles().get(i).getName().equalsIgnoreCase("GBOwner")) {
                Role=true;
            }
            i++;
        }
        if (event.getAuthor().getId()==event.getGuild().getOwner().getUser().getId() || Role) {
            if (args.length < 1) {
                try {
                    MessageHandler.in(event.getMember().getUser(), true, "prefix", event.getGuild());
                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.Titel).setDescription(MessageHandler.Message).setColor(Color.CYAN).build()).queue();
                    Role=false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    MessageHandler.in(event.getMember().getUser(), true, "prefixchanged", event.getGuild());
                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.Titel).setDescription(MessageHandler.Message).setColor(Color.green).build()).queue();
                    MySQL.update("server", "Prefix", args[0], "ID", event.getGuild().getId());
                    Role=false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (Role=false) {
            MessageHandler.in(event.getMember().getUser(), true, "noperms", event.getGuild());
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.Titel).setDescription(MessageHandler.Message).setColor(Color.RED).build()).queue();
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

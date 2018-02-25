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
                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("prefixtitel"))
                            .setDescription(MessageHandler.get(event.getAuthor()).getString("prefixtext").replaceAll("gb.", MessageHandler.getprefix(event.getGuild()))).setColor(Color.CYAN).build()).queue();
                    Role=false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    MySQL.update("server", "Prefix", args[0], "ID", event.getGuild().getId());
                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("prefixchangedtitel"))
                            .setDescription(MessageHandler.get(event.getAuthor()).getString("prefixchangedtext").replaceAll("gb.", MessageHandler.getprefix(event.getGuild()))).setColor(Color.green).build()).queue();
                    Role=false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (Role=false) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("nopermstitel"))
                    .setDescription(MessageHandler.get(event.getAuthor()).getString("nopermstext")).setColor(Color.RED).build()).queue();
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

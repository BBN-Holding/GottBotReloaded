package commands;

import core.MessageHandler;
import core.lang;
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
                    lang.getlanguage(event.getMember().getUser(), true, "prefix", event.getGuild());
                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.Titel).setDescription(MessageHandler.Message).setColor(Color.CYAN).build()).queue();
                    Role=false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Connection con = DriverManager.getConnection(url + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SECRETS.user, SECRETS.password);
                    PreparedStatement pst = con.prepareStatement("Select * FROM `server` WHERE ID=" + event.getGuild().getId());
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        con = DriverManager.getConnection(url + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SECRETS.user, SECRETS.password);
                        pst = con.prepareStatement("UPDATE `server` SET `Prefix`='" + args[0] + "' WHERE ID=" + event.getGuild().getId());
                        pst.execute();
                        lang.getlanguage(event.getMember().getUser(), true, "prefixchanged", event.getGuild());
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.Titel).setDescription(MessageHandler.Message).setColor(Color.green).build()).queue();
                        pst.close();
                    }
                    rs.close();
                    Role=false;
                } catch (SQLSyntaxErrorException e) {
                    lang.getlanguage(event.getMember().getUser(), true, "prefixerror1", event.getGuild());
                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.Titel).setDescription(MessageHandler.Message).setColor(Color.RED).build()).queue();
                    Role=false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (Role=false) {
            lang.getlanguage(event.getMember().getUser(), true, "noperms", event.getGuild());
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

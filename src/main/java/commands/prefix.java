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
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

            if (args.length<1) {
                try {
                Connection con = DriverManager.getConnection(url + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SECRETS.user, SECRETS.password);
                PreparedStatement pst = con.prepareStatement("Select * FROM `server` WHERE ID=" + event.getGuild().getId());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Prefix").setDescription("Your Prefix is: " + rs.getString(2) + "\nTo set a new Prefix write: " + rs.getString(2) + "prefix <New Prefix>").setColor(Color.green).build()).queue();
                }
                rs.close();
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
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Prefix changed").setDescription("Your Prefix is now: " + args[0]).setColor(Color.green).build()).queue();
                        pst.close();
                    }
                    rs.close();
                } catch (SQLSyntaxErrorException e) {
                    lang.getlanguage(event.getMember().getUser(), true, "prefixerror1", event.getGuild());
                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.Titel).setDescription(MessageHandler.Message).setColor(Color.RED).build()).queue();
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

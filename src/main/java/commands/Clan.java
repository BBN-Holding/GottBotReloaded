package commands;

import core.MessageHandler;
import listener.Message;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import stuff.SECRETS;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static stuff.DATA.url;

public class Clan implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<1) {
            MessageHandler.in(event.getAuthor(), true, "clan", event.getGuild());
            event.getTextChannel().sendMessage(new EmbedBuilder().setDescription(MessageHandler.Message).setTitle(MessageHandler.Titel).setColor(Color.RED).build()).queue();
        } else {
            try {
                switch (args[0].toLowerCase()) {

                    case "list":
                        Connection con = DriverManager.getConnection(url + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SECRETS.user, SECRETS.password);
                        PreparedStatement pst = con.prepareStatement("SELECT * FROM `clan` WHERE ID='" + event.getGuild().getId() + "'");
                        ResultSet rs = pst.executeQuery();
                        if (rs.next()) {
                            MessageHandler.in(event.getAuthor(), true, "clanlist", event.getGuild());
                            String clans = MessageHandler.Message;
                            while (rs.next()) {
                                clans += rs.getString(1);
                                rs.next();
                            }
                            MessageHandler.in(event.getAuthor(), true, "clanlistout", event.getGuild());
                            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.Titel).setDescription(MessageHandler.Message).setColor(Color.GREEN).build()).queue();
                        }
                        rs.close();
                        break;

                    case "join":

                        break;


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

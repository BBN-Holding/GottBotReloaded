package commands;

import core.MessageHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import stuff.SECRETS;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static stuff.DATA.url;

public class joinmessage implements Command {
    boolean Role=false;
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            System.out.println("asd");
            int i = 0;
            while (event.getMember().getRoles().size() - 1 >= i) {
                if (event.getMember().getRoles().get(i).getName().equalsIgnoreCase("GBOwner")) {
                    Role = true;
                }
                i++;
            }
            int i2 = 1;
            String Message = "";
            if (event.getAuthor().getId() == event.getGuild().getOwner().getUser().getId() || Role) {
                System.out.println("adsdaf");
                Connection con = DriverManager.getConnection(url + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SECRETS.user, SECRETS.password);
                PreparedStatement pst = con.prepareStatement("Select * FROM `server` WHERE ID=" + event.getGuild().getId());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    if (args.length <= 2) {
                        MessageHandler.in(event.getAuthor(), true, "joinmessage", event.getGuild());
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.Titel).setDescription(MessageHandler.Message + rs.getString(3)).build()).queue();
                    } else {
                        System.out.println("sdflkjsfg");
                        String Channel = args[0].replace("<", "").replace(">", "").replace("#", "");
                        String id = event.getGuild().getTextChannelById(Channel).getId();
                        while (args.length - 1 >= i2) {
                            System.out.println("uii");
                            Message += " " + args[i2];
                            i2++;
                        }
                        System.out.println("asdfasdf");
                            pst = con.prepareStatement("UPDATE `server` SET `Welcome`='" + Message + "',`Welcomechannelid`='" + id + "' WHERE ID='" + event.getGuild().getId() + "'");
                            pst.execute();
                            MessageHandler.in(event.getAuthor(), true, "joinmessagesucess", event.getGuild());
                            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.Titel).setDescription(MessageHandler.Message + Message).setColor(Color.green).build()).queue();

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

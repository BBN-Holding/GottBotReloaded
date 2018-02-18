package commands;

import core.MessageHandler;
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
                lang.getlanguage(event.getMember().getUser(), true, "lang", event.getGuild());
                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.Titel).setDescription(MessageHandler.Message).setColor(Color.CYAN).build()).queue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (args[0].equalsIgnoreCase("german")||args[0].equalsIgnoreCase("english")) {
            try {
                Connection con = DriverManager.getConnection(url + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SECRETS.user, SECRETS.password);
                PreparedStatement pst = con.prepareStatement("UPDATE `user` SET `Lang`='" + args[0] + "' WHERE ID=" + event.getAuthor().getId());
                pst.execute();
                lang.getlanguage(event.getMember().getUser(), true, "langedit", event.getGuild());
                pst.close();
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

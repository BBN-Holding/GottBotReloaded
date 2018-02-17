package commands;

import core.lang;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import stuff.SECRETS;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
                lang.getlanguage(event.getMember().getUser());
                Connection con = DriverManager.getConnection(url + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SECRETS.user, SECRETS.password);
                PreparedStatement pst = con.prepareStatement("Select * FROM `server` WHERE ID=" + event.getGuild().getId());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    if (lang.language.equalsIgnoreCase("german")) {
                        lang.Titel = "Deine Sprache";
                        lang.Message = "Deine Sprache ist derzeit ``" + lang.language + "``\nUmstellen kannst du sie mit " + rs.getString(2) + "language <german|english>";
                    } else if (lang.language.equalsIgnoreCase("english")) {
                        lang.Titel = "Your language";
                        lang.Message = "Your language is ``" + lang.language + "``\nYou can edit your language with " + rs.getString(2) + "language <german|english>";
                    }
                }
                rs.close();
                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(lang.Titel).setDescription(lang.Message).setColor(Color.CYAN).build()).queue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (args[0].equalsIgnoreCase("german")||args[0].equalsIgnoreCase("english")) {
            try {
                Connection con = DriverManager.getConnection(url + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SECRETS.user, SECRETS.password);
                PreparedStatement pst = con.prepareStatement("UPDATE `user` SET `Lang`='" + args[0] + "' WHERE ID=" + event.getAuthor().getId());
                pst.execute();
                lang.getlanguage(event.getMember().getUser());
                if (lang.language.equalsIgnoreCase("english")) {
                    lang.Titel = "Your language was edited";
                    lang.Message = "Your language is now english!";
                } else if (lang.language.equalsIgnoreCase("german")) {
                    lang.Titel = "Deine Sprache wurde geändert";
                    lang.Message = "Deine Sprache ist nun Deutsch!";
                }
                pst.close();
                event.getTextChannel().sendMessage(new EmbedBuilder().setDescription(lang.Message).setTitle(lang.Titel).setColor(Color.green).build()).queue();
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

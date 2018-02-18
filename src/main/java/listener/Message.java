package listener;

import core.Main;
import core.MessageHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stuff.DATA;
import stuff.SECRETS;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

public class Message extends ListenerAdapter {
    private static Logger logger = LoggerFactory.getLogger(Message.class);
    int Punkte;
    int Level;
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        // Mention
        if (event.getMessage().getContentRaw().startsWith("<@407189087649398795>")) {
            MessageHandler.in(event.getAuthor(), true, "mention", event.getGuild());
            event.getChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.Titel).setDescription(MessageHandler.Message).setColor(Color.CYAN).build()).queue();
            logger.info(event.getAuthor().getName()+" mit ID "+ event.getAuthor().getId()+" auf "+event.getGuild().getName()+" hat mich erwähnt! ");
        }
        //lvl
        if (event.getAuthor().isBot()) {
        } else {
            try {
                Connection con = DriverManager.getConnection(DATA.url + "" + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SECRETS.user, SECRETS.password);
                PreparedStatement pst = con.prepareStatement("SELECT * FROM `user` WHERE ID='"+event.getAuthor().getId()+"'");
                ResultSet rs = pst.executeQuery();
                if (!rs.next()) {
                } else {
                    Level=rs.getInt(3);
                    Punkte=rs.getInt(4);
                    Punkte++;
                    pst = con.prepareStatement("UPDATE `user` SET `xp`='"+Punkte+"' WHERE ID='"+event.getAuthor().getId()+"'");
                    pst.execute();
                    System.out.println("HIII");

                    pst = con.prepareStatement("SELECT * FROM `lvl`");
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        if (Punkte>rs.getInt(2)-1) {
                            if (Level<rs.getInt(1)) {
                                Level++;
                                pst = con.prepareStatement("UPDATE `user` SET `xp`='0',`level`='"+Level+"' WHERE ID='"+event.getAuthor().getId()+"'");
                                pst.execute();
                                event.getChannel().sendMessage(new EmbedBuilder().setColor(Color.GREEN).setTitle("Level Aufgestiegen").setDescription("Du bist jetzt schon Lvl "+Level+"!").build()).complete().delete().queueAfter(10, TimeUnit.SECONDS);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

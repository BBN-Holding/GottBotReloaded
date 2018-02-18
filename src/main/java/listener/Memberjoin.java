package listener;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stuff.SECRETS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static stuff.DATA.url;

public class Memberjoin extends ListenerAdapter {
    private static Logger logger = LoggerFactory.getLogger(Memberjoin.class);
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        try {
            Connection con = DriverManager.getConnection(url + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SECRETS.user, SECRETS.password);
            PreparedStatement pst = con.prepareStatement("Select * FROM `server` WHERE ID=" + event.getGuild().getId());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                if (!rs.getString(3).isEmpty()) {
                    String Message = rs.getString(3)+"";
                    Message.replace("%m", "TEST");
                            Message.replace("%s", "LOL");
                    event.getGuild().getTextChannelById(rs.getLong(4)).sendMessage(Message).queue();
                }
            }
            rs.close();

            pst = con.prepareStatement("Select * FROM `user` WHERE ID=" + event.getUser().getId());
            rs = pst.executeQuery();
            if (!rs.next()) {
                pst = con.prepareStatement("INSERT INTO `user` (`ID`) VALUES ('"+event.getUser().getId()+"');");
                pst.execute();
                pst.close();
                logger.info("neuer User in database Name: "+event.getMember().getUser().getName()+" ID: "+event.getUser().getId()+" von "+event.getGuild().getName());
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

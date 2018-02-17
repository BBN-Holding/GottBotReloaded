package listener;

import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import stuff.SECRETS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static stuff.DATA.urlempty;

public class Guildjoin extends ListenerAdapter {

    public void onGuildJoin(GuildJoinEvent event) {
        try {
            System.out.println("Ich bin auf einem neuen Server: Name: "+event.getGuild().getName()+" ID: "+event.getGuild().getId()+" User: "+event.getGuild().getMembers().size());
            Connection con = DriverManager.getConnection(urlempty + "miner?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SECRETS.user, SECRETS.password);
            PreparedStatement pst = con.prepareStatement("Select * FROM `ID` WHERE ID=" + event.getGuild().getId());
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                con = DriverManager.getConnection(urlempty + "miner?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SECRETS.user, SECRETS.password);
                pst = con.prepareStatement("Select * FROM `ID` WHERE ID=" + event.getGuild().getId());
                pst.execute();
                rs.close();
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

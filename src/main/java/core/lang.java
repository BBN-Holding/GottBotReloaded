package core;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import stuff.SECRETS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static stuff.DATA.url;

public class lang {
    public static String language;
    public static void getlanguage(User user, boolean MessageHandler, String message, Guild guild) {
        try {
            Connection con = DriverManager.getConnection(url + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SECRETS.user, SECRETS.password);
            PreparedStatement pst = con.prepareStatement("Select * FROM `user` WHERE ID=" + user.getId());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                language = rs.getString(2);
            }
            rs.close();
            if (MessageHandler) {
                core.MessageHandler.get(language, message, guild);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

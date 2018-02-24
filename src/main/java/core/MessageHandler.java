package core;

import listener.Message;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import stuff.SECRETS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.ResourceBundle;

import static stuff.DATA.url;

public class MessageHandler {
    public static ResourceBundle messagebundle;
    public static ResourceBundle get(User user){
        try {
            String language = MySQL.get("user", "ID", user.getId()+"", "language");
            System.out.println(language);
            Locale locale = new Locale(language);
            messagebundle = ResourceBundle.getBundle("MessagesBundle", locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messagebundle;
    }
    public static String getprefix(Guild guild) {
        String Prefix = MySQL.get("server", "ID", guild.getId(), "prefix");
        return Prefix;
    }

}

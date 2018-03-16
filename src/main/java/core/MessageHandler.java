package core;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageHandler {
    public static ResourceBundle messagebundle;
    public static ResourceBundle get(User user){
        try {
            String language = MySQL.get("user", "ID", user.getId()+"", "language");
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

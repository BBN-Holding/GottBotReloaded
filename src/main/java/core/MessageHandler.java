package core;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageHandler {
    public static ResourceBundle messagebundle;
    public static MessageEmbed getEmbed(String title, String msg, String extra, String type, MessageReceivedEvent event) {
        MessageEmbed result = new EmbedBuilder().setTitle("This is a error :O").setDescription("Please use ``%prefix%bug`` Thanks <3".replace("%prefix%", getprefix(event.getGuild()))).setColor(Color.RED).build();
        String getTitle = get(event.getAuthor()).getString(title);
        String getMsg = get(event.getAuthor()).getString(msg);
        switch (type.toLowerCase()) {
            case "normal":
                result = new EmbedBuilder().setColor(Color.CYAN).setTitle(getTitle).setDescription(getMsg.replaceAll("%prefix%", getprefix(event.getGuild())).replace("%extra%", extra)).build();
                break;

            case "error":
                result = new EmbedBuilder().setColor(Color.RED).setTitle(getTitle.replaceAll("%prefix%", getprefix(event.getGuild())).replace("%extra%", extra)).setDescription(getMsg.replaceAll("%prefix%", getprefix(event.getGuild())).replace("%extra%", extra)).build();
                break;

            case "sucess":
                result = new EmbedBuilder().setColor(Color.GREEN).setTitle(getTitle).setDescription(getMsg.replaceAll("%prefix%", getprefix(event.getGuild())).replace("%extra%", extra)).build();
                break;
        }
        return result;

    }
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

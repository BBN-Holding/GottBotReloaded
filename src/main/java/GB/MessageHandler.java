package GB;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageHandler {
    public MessageEmbed getEmbed(String title, String msg, String extra, String type, MessageReceivedEvent event) {
        MessageEmbed result = new EmbedBuilder().setTitle("This is a error :O").setDescription("Please use ``%prefix%bug`` Thanks <3".replace("%prefix%", getprefix(event.getGuild()))).setColor(Color.RED).build();
        String getTitle = get(title, event.getAuthor(), event.getGuild());
        String getMsg = get(msg, event.getAuthor(), event.getGuild());
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
    public String get(String string, User user, Guild guild){
        try {
            String language = new Handler().getMySQL().get("user", "ID", user.getId()+"", "language");
            Locale locale = new Locale(language);
            return ResourceBundle.getBundle("MessagesBundle", locale).getString(string).replaceAll("%prefix%", getprefix(guild));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }
    public String getprefix(Guild guild) {
        String Prefix = new Handler().getMySQL().get("server", "ID", guild.getId(), "prefix");
        return Prefix;
    }

}

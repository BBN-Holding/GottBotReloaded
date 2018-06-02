package GB.Handler;

import GB.GottBot;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Message {

    public MessageEmbed getEmbed(String TitleKey, String DescriptionKey, Color color, User user, Guild guild) {
        String titleValue = getString(TitleKey, user, guild);
        String descriptionValue = getString(DescriptionKey, user, guild);
        MessageEmbed messageEmbed = new EmbedBuilder().setTitle(titleValue).setDescription(descriptionValue).setColor(color).build();
        return messageEmbed;
    }

    public String getString(String string, User user, Guild guild) {
        try {
            String language = GottBot.getDB().get("user", "userid", user.getId(), "language");
            Locale locale = new Locale(language);
            return ResourceBundle.getBundle("Messages", locale).getString(string).replaceAll("%prefix%", getPrefix(guild.getId()));
        } catch (NullPointerException ignored) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return string+" not found";
    }

    public String getPrefix(String id) {
        return GottBot.getDB().get("server", "serverid", id, "prefix");
    }

}

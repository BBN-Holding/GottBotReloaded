package GB.Handler;

import GB.GottBot;
import jdk.jfr.Event;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Message {

    public MessageEmbed getEmbed(String TitleKey, String DescriptionKey, Color color, MessageReceivedEvent event) {
        String titleValue = getString(TitleKey, event);
        String descriptionValue = getString(DescriptionKey, event);
        MessageEmbed messageEmbed = new EmbedBuilder().setTitle(titleValue).setDescription(descriptionValue).setColor(color).build();
        return messageEmbed;
    }

    public String getString(String string, MessageReceivedEvent event) {
        return getString(string, GottBot.getDB().get("user", "userid", event.getAuthor().getId(), "language"), event);
    }

    public String getString(String string, String language, MessageReceivedEvent event) {
        try {
            return ResourceBundle.getBundle("Messages_" + language).getString(string).replaceAll("%prefix%", getPrefix(event.getGuild().getId()));
        } catch (MissingResourceException ex) {

        } catch (NullPointerException ignored) {
            ignored.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string+" not found";
    }

    public String getPrefix(String id) {
        return GottBot.getDB().get("server", "serverid", id, "prefix");
    }

    public MessageEmbed getCommandTemplate(String[] aliases, String usage, String description) {
        String aliasess="";
        for (int i =0; (aliases.length-1)>i; i++) {
            aliasess+=aliases[i]+", ";
        }
        aliasess+=aliases[aliases.length-1];
        MessageEmbed messageEmbed = new EmbedBuilder().setTitle("Help")
                .addField("Aliases", aliasess , false)
                .addField("Usage", usage, false)
                .addField("Description", description, false)
                .build();
        return messageEmbed;
    }
}

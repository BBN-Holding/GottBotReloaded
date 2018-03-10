package listener;

import core.Main;
import core.MessageHandler;
import core.MySQL;
import javafx.beans.binding.IntegerBinding;
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
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        // Mention
        if (event.getMessage().getContentRaw().equals(event.getJDA().getSelfUser().getAsMention())) {
            event.getChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("mentiontitel"))
                    .setDescription(MessageHandler.get(event.getAuthor()).getString("mentiontext").replaceAll("gb.", MessageHandler.getprefix(event.getGuild()))).setColor(Color.CYAN).build()).queue();
            logger.info(event.getAuthor().getName()+" mit ID "+ event.getAuthor().getId()+" auf "+event.getGuild().getName()+" hat mich erwähnt! ");
        }
        //lvl
        if (!event.getAuthor().isBot()) {
            long xp = Long.parseLong(MySQL.get("user", "id", event.getAuthor().getId(), "xp"));
            xp++;
            MySQL.update("user","xp", String.valueOf(xp), "ID", event.getAuthor().getId());
            long level = Long.parseLong(MySQL.get("user", "ID", event.getAuthor().getId(), "level"));
            long xpmax = Long.parseLong(MySQL.get("lvl", "lvl", String.valueOf(level+1), "xp"));
            if (xp>=xpmax) {
                MySQL.update("user", "level", String.valueOf(level+1), "ID", event.getAuthor().getId());
                MySQL.update("user", "xp", "0", "ID", event.getAuthor().getId());
                if (MySQL.get("user", "id", event.getAuthor().getId(), "lvlmessage").equals("true")) {
                    event.getAuthor().openPrivateChannel().complete().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("leveluptitel"))
                            .setDescription(MessageHandler.get(event.getAuthor()).getString("leveluptext") + String.valueOf(level + 1)).build()).queue();
                }
            }

        }
    }
}

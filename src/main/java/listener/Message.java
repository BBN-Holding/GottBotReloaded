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
    int Punkte;
    int Level;
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        // Mention
        if (event.getMessage().getContentRaw().startsWith("<@407189087649398795>")) {
            MessageHandler.in(event.getAuthor(), true, "mention", event.getGuild());
            event.getChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.Titel).setDescription(MessageHandler.Message).setColor(Color.CYAN).build()).queue();
            logger.info(event.getAuthor().getName()+" mit ID "+ event.getAuthor().getId()+" auf "+event.getGuild().getName()+" hat mich erwähnt! ");
        }
        //lvl
        if (!event.getAuthor().isBot()) {
            long xp = Long.parseLong(MySQL.get("user", "ID", event.getAuthor().getId(), "xp"));
            xp++;
            MySQL.update("user","xp", String.valueOf(xp), "ID", event.getAuthor().getId());
            long level = Long.parseLong(MySQL.get("user", "ID", event.getAuthor().getId(), "level"));
            long xpmax = Long.parseLong(MySQL.get("lvl", "level", String.valueOf(level+1), "lvl"));
            if (xp>=xpmax) {
                MySQL.update("user", "level", String.valueOf(level+1), "ID", event.getAuthor().getId());
                event.getAuthor().openPrivateChannel().complete().sendMessage()
            }

        }
    }
}

package listener;

import core.Main;
import core.MySQL;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import core.commandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stuff.SECRETS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

import static stuff.DATA.url;

public class commandListener extends ListenerAdapter {
    public static String beheaded;
    private static Logger logger = LoggerFactory.getLogger(commandListener.class);
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            if (event.getChannelType().equals("TEXT")) {
                if (!event.getAuthor().isBot()) {
                    String PREFIX = MySQL.get("server", "ID", event.getGuild().getId(), "prefix");
                    if (event.getMessage().getContentRaw().startsWith(PREFIX)) {
                        beheaded = event.getMessage().getContentRaw().toLowerCase().replaceFirst(Pattern.quote(PREFIX), "");
                        commandHandler.handleCommand(commandHandler.parser.parse(event.getMessage().getContentRaw().toLowerCase(), event));
                        logger.info(event.getAuthor().getName() + " mit ID " + event.getAuthor().getId() + " auf " + event.getGuild().getName() + " hat den Command genutzt: " + event.getMessage().getContentRaw());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
package listener;

import core.Main;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.ShutdownEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import core.commandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stuff.DATA;

import java.util.EventListener;
import java.util.regex.Pattern;

public class commandListener extends ListenerAdapter {
    public static String beheaded;
    private static Logger logger = LoggerFactory.getLogger(commandListener.class);

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            if (!event.getAuthor().isBot()) {
                if (MySQL.get("blacklist", "id", event.getAuthor().getId(), "id")==null) {
                   if (event.getChannelType().equals(ChannelType.TEXT)) {
                       String PREFIX = MySQL.get("server", "ID", event.getGuild().getId(), "prefix");
                       if (event.getMessage().getContentRaw().replaceFirst("!", "").startsWith(event.getJDA().getSelfUser().getAsMention())) {
                           beheaded = event.getMessage().getContentRaw().replaceFirst("!", "").replace(event.getJDA().getSelfUser().getAsMention(), "");
                           commandHandler.handleCommand(commandHandler.parser.parse(event.getMessage().getContentRaw(), event));
                           logger.info(event.getAuthor().getName() + " mit ID " + event.getAuthor().getId() + " auf " + event.getGuild().getName() + " hat den Command genutzt: " + event.getMessage().getContentRaw());
                           String Command = MySQL.get1("stats", "1", "command");
                           Main.shardManager.getGuildById(DATA.BBNS).getTextChannelById(DATA.BBNLOG).sendMessage(new EmbedBuilder().setAuthor(event.getAuthor().getName()+event.getAuthor().getId(), event.getAuthor().getAvatarUrl(), event.getAuthor().getAvatarUrl()).setDescription(event.getMessage().getContentRaw()).build()).queue();
                           long jay = Long.parseLong(Command);
                           long juhu = jay + 1;
                           MySQL.update("stats", "command", String.valueOf(juhu), "command", String.valueOf(jay));
                       } else if (event.getMessage().getContentRaw().startsWith(PREFIX)) {
                            beheaded = event.getMessage().getContentRaw().replaceFirst(Pattern.quote(PREFIX), "");
                            commandHandler.handleCommand(commandHandler.parser.parse(event.getMessage().getContentRaw(), event));
                            logger.info(event.getAuthor().getName() + " mit ID " + event.getAuthor().getId() + " auf " + event.getGuild().getName() + " hat den Command genutzt: " + event.getMessage().getContentRaw());
                            String Command = MySQL.get1("stats", "1", "command");
                           Main.shardManager.getGuildById(DATA.BBNS).getTextChannelById(DATA.BBNLOG).sendMessage(new EmbedBuilder().setAuthor(event.getAuthor().getName()+event.getAuthor().getId(), event.getAuthor().getAvatarUrl(), event.getAuthor().getAvatarUrl()).setDescription(event.getMessage().getContentRaw()).build()).queue();
                            long jay = Long.parseLong(Command);
                            long juhu = jay + 1;
                            MySQL.update("stats", "command", String.valueOf(juhu), "command", String.valueOf(jay));
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
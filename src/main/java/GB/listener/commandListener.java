package GB.listener;

import GB.Handler;
import GB.core.Main;
import GB.core.commandHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import GB.stuff.DATA;

import java.util.regex.Pattern;

public class commandListener extends ListenerAdapter {
    public static String beheaded;
    private static Logger logger = LoggerFactory.getLogger(commandListener.class);

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            if (!event.getAuthor().isBot()) {
                Handler handler = new Handler();
                if (new Handler().getMySQL().get("blacklist", "id", event.getAuthor().getId(), "id")==null) {
                   if (event.getChannelType().equals(ChannelType.TEXT)) {
                       String PREFIX = new Handler().getMySQL().get("server", "ID", event.getGuild().getId(), "prefix");
                       if (event.getMessage().getContentRaw().replaceFirst("!", "").startsWith(event.getJDA().getSelfUser().getAsMention())) {
                            handle(event);
                       } else if (event.getMessage().getContentRaw().startsWith(PREFIX)) {
                            handle(event);
                       }
                    }
                }
            }

        } catch (Exception e) {
            new Handler().getError().gethandle(e);
        }
    }
    public static void handle(MessageReceivedEvent event) {
        try {
            Handler handler = new Handler();
            String PREFIX = new Handler().getMySQL().get("server", "ID", event.getGuild().getId(), "prefix");
            beheaded = event.getMessage().getContentRaw().replaceFirst(Pattern.quote(PREFIX), "");
            commandHandler.handleCommand(commandHandler.parser.parse(event.getMessage().getContentRaw(), event));
            logger.info(event.getAuthor().getName() + " mit ID " + event.getAuthor().getId() + " auf " + event.getGuild().getName() + " hat den Command genutzt: " + event.getMessage().getContentRaw());
            String Command = handler.getMySQL().getfirst("stats", "1", "command");
            Main.shardManager.getGuildById(DATA.BBNS).getTextChannelById(DATA.BBNLOG).sendMessage(new EmbedBuilder().setAuthor(event.getAuthor().getName() + event.getAuthor().getId(), event.getAuthor().getAvatarUrl(), event.getAuthor().getAvatarUrl()).setDescription(event.getMessage().getContentRaw()).build()).queue();
            long jay = Long.parseLong(Command);
            long juhu = jay + 1;
            new Handler().getMySQL().update("stats", "command", String.valueOf(juhu), "command", String.valueOf(jay));
        } catch (Exception e) {
            new Handler().getError().gethandle(e);
        }
    }
}

package listener;

import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Message extends ListenerAdapter  {
    private static Logger logger = LoggerFactory.getLogger(Message.class);
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannelType().isGuild()) {
            //registeruser
            if (!event.getAuthor().isBot()) {
                if (MySQL.get("user", "id", event.getAuthor().getId(), "id") == null) {
                    MySQL.insert("user", "id", event.getAuthor().getId() + "");
                    logger.info("neuer User in database Name: " + event.getAuthor().getName() + " ID: " + event.getAuthor().getId() + " von " + event.getGuild().getName());
                }
            }
            //registeruser premium
            if (!event.getAuthor().isBot()) {
                if (MySQL.get("premium", "id", event.getAuthor().getId(), "id") == null) {
                    MySQL.insert("premium", "id", event.getAuthor().getId() + "");
                }
            }
            // Mention
            if (event.getMessage().getContentRaw().replace("!", "").equals(event.getJDA().getSelfUser().getAsMention())) {
                event.getChannel().sendMessage(MessageHandler.getEmbed("listener.Mention1", "listener.Mention2", "", "sucess", event)).queue();
                logger.info(event.getAuthor().getName() + " mit ID " + event.getAuthor().getId() + " auf " + event.getGuild().getName() + " hat mich erwähnt! ");
            }
            // registerserver
            if (MySQL.get("server", "id", event.getGuild().getId(), "id") == null) {
                MySQL.insert("server", "id", event.getGuild().getId());
                logger.info("neuer Server: Name: " + event.getGuild().getName() + " ID: " + event.getGuild().getId() + " Member: " + event.getGuild().getMembers().size());
                int i = 0;
                while (event.getGuild().getMembers().size() - 1 >= i) {
                    if (MySQL.get("user", "id", event.getGuild().getMembers().get(i).getUser().getId(), "id") == null) {
                        MySQL.insert("user", "id", event.getGuild().getMembers().get(i).getUser().getId() + "");
                        logger.info("neuer User in database Name: " + event.getGuild().getMembers().get(i).getUser().getName() + " ID: " + event.getGuild().getMembers().get(i).getUser().getId() + " von " + event.getGuild().getName());
                    }
                    i++;
                }
            }
            //stats
            long Mesasge = Long.parseLong(MySQL.get1("stats", "1", "message"));
            Mesasge++;
            MySQL.update("stats", "message", String.valueOf(Mesasge), "message", MySQL.get1("stats", "1", "message"));
            //lvl
            if (!event.getAuthor().isBot()) {
                long xp = Long.parseLong(MySQL.get("user", "id", event.getAuthor().getId(), "xp"));
                xp++;
                MySQL.update("user", "xp", String.valueOf(xp), "ID", event.getAuthor().getId());
                long level = Long.parseLong(MySQL.get("user", "ID", event.getAuthor().getId(), "level"));
                long xpmax = Long.parseLong(MySQL.get("lvl", "lvl", String.valueOf(level + 1), "xp"));
                if (xp >= xpmax) {
                    MySQL.update("user", "level", String.valueOf(level + 1), "ID", event.getAuthor().getId());
                    MySQL.update("user", "xp", "0", "ID", event.getAuthor().getId());
                    if (MySQL.get("user", "id", event.getAuthor().getId(), "lvlmessage").equals("true")) {
                        event.getAuthor().openPrivateChannel().complete().sendMessage(MessageHandler.getEmbed("listener.levelup1", "listener.levelup2", String.valueOf(level+1), "sucess", event)).queue();
                    }
                }

            }
            // Verification
            if (!event.getAuthor().isBot()) {
                if (!MySQL.get("server", "id", event.getGuild().getId(), "verification").equals("none") && MySQL.get("server", "id", event.getGuild().getId(), "verificationart").equals("text")) {
                    String Message = MySQL.get("server", "id", event.getGuild().getId(), "verification");
                    if (event.getChannel().getId().equals(Message)) {
                        if (event.getMessage().getContentRaw().equalsIgnoreCase(MySQL.get("server", "id", event.getGuild().getId(), "verificationmessage")) && event.getChannel().getId().equals(MySQL.get("server", "id", event.getGuild().getId(), "verification"))) {
                            event.getGuild().getController().addSingleRoleToMember(event.getMember(), event.getGuild().getRoleById(MySQL.get("server", "id", event.getGuild().getId(), "verificationrole"))).queue();
                            event.getMessage().delete().queueAfter(2, TimeUnit.SECONDS);
                        }
                    }
                }
            }
        }
    }
}

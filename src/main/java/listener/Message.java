package listener;

import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class Message extends ListenerAdapter {
    private static Logger logger = LoggerFactory.getLogger(Message.class);
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        //registeruser
        if (MySQL.get("user", "id", event.getAuthor().getId(), "id")==null) {
            MySQL.insert("user", "id", event.getAuthor().getId()+"");
            logger.info("neuer User in database Name: " + event.getAuthor().getName() + " ID: " + event.getAuthor().getId() + " von " + event.getGuild().getName());
        }
        // Mention
        if (event.getMessage().getContentRaw().replace("!", "").equals(event.getJDA().getSelfUser().getAsMention())) {
            event.getChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("mentiontitel"))
                    .setDescription(MessageHandler.get(event.getAuthor()).getString("mentiontext").replaceAll("gb.", MessageHandler.getprefix(event.getGuild()))).setColor(Color.CYAN).build()).queue();
            logger.info(event.getAuthor().getName()+" mit ID "+ event.getAuthor().getId()+" auf "+event.getGuild().getName()+" hat mich erwähnt! ");
        }
        // registerserver
        if (MySQL.get("server", "id", event.getGuild().getId(), "id")==null) {
            MySQL.insert("server", "id", event.getGuild().getId());
            logger.info("neuer Server: Name: "+event.getGuild().getName()+" ID: "+event.getGuild().getId()+" Member: "+event.getGuild().getMembers().size());
            int i = 0;
            while (event.getGuild().getMembers().size()-1>=i) {
                if (MySQL.get("user", "id", event.getGuild().getMembers().get(i).getUser().getId(), "id")==null) {
                    MySQL.insert("user", "id", event.getGuild().getMembers().get(i).getUser().getId()+"");
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

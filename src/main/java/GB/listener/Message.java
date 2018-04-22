package GB.listener;

import GB.Handler;
import GB.MessageHandler;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Message extends ListenerAdapter  {
    private static Logger logger = LoggerFactory.getLogger(Message.class);
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannelType().isGuild()) {
            Handler handler = new Handler();
            //registeruser
            if (!event.getAuthor().isBot()) {
                if (new Handler().getMySQL().get("user", "id", event.getAuthor().getId(), "id") == null) {
                    new Handler().getMySQL().insert("user", "id", event.getAuthor().getId() + "");
                    logger.info("neuer User in database Name: " + event.getAuthor().getName() + " ID: " + event.getAuthor().getId() + " von " + event.getGuild().getName());
                }
            }
            // Mention
            if (event.getMessage().getContentRaw().replace("!", "").equals(event.getJDA().getSelfUser().getAsMention())) {
                event.getChannel().sendMessage(MessageHandler.getEmbed("listener.Mention1", "listener.Mention2", "", "sucess", event)).queue();
                logger.info(event.getAuthor().getName() + " mit ID " + event.getAuthor().getId() + " auf " + event.getGuild().getName() + " hat mich erwähnt! ");
            }
            // registerserver
            if (new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "id") == null) {
                new Handler().getMySQL().insert("server", "id", event.getGuild().getId());
                logger.info("neuer Server: Name: " + event.getGuild().getName() + " ID: " + event.getGuild().getId() + " Member: " + event.getGuild().getMembers().size());
                int i = 0;
                while (event.getGuild().getMembers().size() - 1 >= i) {
                    if (new Handler().getMySQL().get("user", "id", event.getGuild().getMembers().get(i).getUser().getId(), "id") == null) {
                        new Handler().getMySQL().insert("user", "id", event.getGuild().getMembers().get(i).getUser().getId() + "");
                        logger.info("neuer User in database Name: " + event.getGuild().getMembers().get(i).getUser().getName() + " ID: " + event.getGuild().getMembers().get(i).getUser().getId() + " von " + event.getGuild().getName());
                    }
                    i++;
                }
            }
            //stats
            long Mesasge = Long.parseLong(new Handler().getMySQL().getfirst("stats", "1", "message"));
            Mesasge++;
            new Handler().getMySQL().update("stats", "message", String.valueOf(Mesasge), "message", new Handler().getMySQL().getfirst("stats", "1", "message"));
            //lvl
            if (!event.getAuthor().isBot()) {
                long xp = Long.parseLong(new Handler().getMySQL().get("user", "id", event.getAuthor().getId(), "xp"));
                xp++;
                new Handler().getMySQL().update("user", "xp", String.valueOf(xp), "ID", event.getAuthor().getId());
                long level = Long.parseLong(new Handler().getMySQL().get("user", "ID", event.getAuthor().getId(), "level"));
                long xpmax = Long.parseLong(new Handler().getMySQL().get("lvl", "lvl", String.valueOf(level + 1), "xp"));
                if (xp >= xpmax) {
                    new Handler().getMySQL().update("user", "level", String.valueOf(level + 1), "ID", event.getAuthor().getId());
                    new Handler().getMySQL().update("user", "xp", "0", "ID", event.getAuthor().getId());
                    if (new Handler().getMySQL().get("user", "id", event.getAuthor().getId(), "lvlmessage").equals("true")) {
                        event.getAuthor().openPrivateChannel().complete().sendMessage(MessageHandler.getEmbed("listener.levelup1", "listener.levelup2", String.valueOf(level+1), "sucess", event)).queue();
                    }
                }

            }
            // Verification
            if (!event.getAuthor().isBot()) {
                if (!new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "verification").equals("none") && new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "verificationart").equals("text")) {
                    String Message = new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "verification");
                    if (event.getChannel().getId().equals(Message)) {
                        if (event.getMessage().getContentRaw().equalsIgnoreCase(new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "verificationmessage")) && event.getChannel().getId().equals(new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "verification"))) {
                            event.getGuild().getController().addSingleRoleToMember(event.getMember(), event.getGuild().getRoleById(new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "verificationrole"))).queue();
                            event.getMessage().delete().queueAfter(2, TimeUnit.SECONDS);
                        }
                    }
                }
            }
        }
    }
}

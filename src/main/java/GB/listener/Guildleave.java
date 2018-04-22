package GB.listener;

import GB.Handler;
import GB.listener.commandListener;
import net.dv8tion.jda.core.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Guildleave extends ListenerAdapter {
    private static Logger logger = LoggerFactory.getLogger(commandListener.class);
    public void onGuildLeave(GuildLeaveEvent event) {
        new Handler().getMySQL().delete("server", "ID", event.getGuild().getId());
        logger.info("ein Server weniger: Name: "+event.getGuild().getName()+" ID: "+event.getGuild().getId()+" Member: "+event.getGuild().getMembers().size());



    }
}

package GB.listener;

import GB.Handler;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Guildjoin extends ListenerAdapter  {
    private static Logger logger = LoggerFactory.getLogger(Guildjoin.class);
    public void onGuildJoin(GuildJoinEvent event) {
        try {
                if (new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "id")==null) {
                    new Handler().getMySQL().insertServer(event.getGuild().getId());
                }
               // if (new GB.GB.Handler().getMySQL().get("log", "serverid", event.getGuild().getId(), "id")==null) { new GB.GB.Handler().getMySQL().insert("log", "serverid", event.getGuild().getId()); }
            logger.info("ein neuer Server: Name: "+event.getGuild().getName()+" ID: "+event.getGuild().getId()+" Member: "+event.getGuild().getMembers().size());
                int i =0;
            while (event.getGuild().getMembers().size()-1>=i) {
                if (new Handler().getMySQL().get("user", "id", event.getGuild().getMembers().get(i).getUser().getId(), "id")==null) {
                    new Handler().getMySQL().insertUser(event.getGuild().getMembers().get(i).getUser().getId());
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

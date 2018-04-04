package listener;

import core.MySQL;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Guildjoin extends ListenerAdapter  {
    private static Logger logger = LoggerFactory.getLogger(commandListener.class);
    public void onGuildJoin(GuildJoinEvent event) {
        try {
                if (MySQL.get("server", "id", event.getGuild().getId(), "id")==null) {
                    MySQL.insert("server", "id", event.getGuild().getId());
                    logger.info("ein neuer Server: Name: "+event.getGuild().getName()+" ID: "+event.getGuild().getId()+" Member: "+event.getGuild().getMembers().size());
                }

            int i = 0;
            while (event.getGuild().getMembers().size()-1>=i) {
                if (MySQL.get("user", "id", event.getGuild().getMembers().get(i).getUser().getId(), "id")==null) {
                    MySQL.insert("user", "id", event.getGuild().getMembers().get(i).getUser().getId()+"");
                    logger.info("neuer User in database Name: " + event.getGuild().getMembers().get(i).getUser().getName() + " ID: " + event.getGuild().getMembers().get(i).getUser().getId() + " von " + event.getGuild().getName());
                }
                i++;
            }




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

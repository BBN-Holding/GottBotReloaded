package listener;

import core.MySQL;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Memberjoin extends ListenerAdapter {
    private static Logger logger = LoggerFactory.getLogger(Memberjoin.class);

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        if (MySQL.get("user", "ID", event.getUser().getId(), "ID")==null) {
            MySQL.insert("user", "ID", event.getUser().getId());
        }
    }

}

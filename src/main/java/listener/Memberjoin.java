package listener;

import core.Main;
import core.MySQL;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stuff.DATA;

public class Memberjoin extends ListenerAdapter {
    private static Logger logger = LoggerFactory.getLogger(Memberjoin.class);

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        if (MySQL.get("user", "ID", event.getUser().getId(), "ID")==null) {
            MySQL.insert("user", "ID", event.getUser().getId());
        }
        if (!MySQL.get("user", "id", event.getUser().getId(), "premium").equals("none")) {
            Guild bbn = Main.shardManager.getGuildById(DATA.BBNS);
            if (event.getGuild().getId().equals(bbn.getId()))
            bbn.getController().addSingleRoleToMember(bbn.getMember(event.getUser()), bbn.getRoleById(408660274103451649L)).queue();
        }
    }

}

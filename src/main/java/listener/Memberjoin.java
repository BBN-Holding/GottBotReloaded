package listener;

import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stuff.SECRETS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static stuff.DATA.url;

public class Memberjoin extends ListenerAdapter {
    private static Logger logger = LoggerFactory.getLogger(Memberjoin.class);

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        if (!MySQL.get("user", "ID", event.getUser().getId(), "ID").isEmpty()) {
            MySQL.insert("user", "ID", event.getUser().getId());
            logger.info("neuer User in database Name: " + event.getMember().getUser().getName() + " ID: " + event.getUser().getId() + " von " + event.getGuild().getName());
        }
    }

}

package listener;

import core.MySQL;
import net.dv8tion.jda.core.events.guild.GuildBanEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class LogListener extends ListenerAdapter  {


    @Override
    public void onGuildBan(GuildBanEvent event) {
        if (!MySQL.get("server", "id", event.getGuild().getId(), "logchannel").equals("none")) {
            event.getJDA().getTextChannelById(MySQL.get("server", "id", event.getGuild().getId(), "logchannel")).sendMessage("BYE BYE BRO" + event.getUser().getName() + "#" + event.getUser().getDiscriminator()).queue();

        }
    }



}

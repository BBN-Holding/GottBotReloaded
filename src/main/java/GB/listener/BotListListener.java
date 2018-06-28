package gb.listener;

import gb.GottBot;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.json.JSONObject;
import space.botlist.api.BotlistSpaceClient;

public class BotListListener extends ListenerAdapter {



    private JSONObject json = new JSONObject();

    @Override
    public void onGuildJoin(GuildJoinEvent event) {

        BotlistSpaceClient botlistspace = new BotlistSpaceClient(GottBot.getConfig().BotLists.get("BotListSpace"), GottBot.shardManager.getGuilds().get(0).getSelfMember().getUser().getId());




    }

}

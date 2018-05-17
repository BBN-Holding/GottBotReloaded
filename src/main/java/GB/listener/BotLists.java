package GB.listener;

import GB.GottBot;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.discordbots.api.client.DiscordBotListAPI;
import org.json.JSONObject;
import space.botlist.api.*;

import java.io.IOException;

import static GB.GottBot.getConfig;


public class BotLists extends ListenerAdapter {

    @Override
    public void onGuildJoin(GuildJoinEvent event) {

        DiscordBotListAPI api = new DiscordBotListAPI.Builder()
                .token(getConfig().BotLists.get("DiscordBots"))
                .build();

        api.setStats("407189087649398795", GottBot.shardManager.getShards().get(0).getGuilds().size(), GottBot.shardManager.getShards().get(0).getShardInfo().getShardId(), GottBot.shardManager.getShards().size());
        api.setStats("407189087649398795", GottBot.shardManager.getShards().get(1).getGuilds().size(), GottBot.shardManager.getShards().get(1).getShardInfo().getShardId(), GottBot.shardManager.getShards().size());


    }


    @Override
    public void onGuildLeave(GuildLeaveEvent event) {



    }
}

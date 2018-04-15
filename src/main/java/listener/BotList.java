package listener;

import core.Main;
import de.foryasee.httprequest.HttpRequestBuilder;
import de.foryasee.httprequest.RequestHeader;
import de.foryasee.httprequest.RequestResponse;
import de.foryasee.httprequest.RequestType;
import org.discordbots.api.client.DiscordBotListAPI;
import stuff.SECRETS;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import static core.Main.jda;

public class BotList extends ListenerAdapter {

    String botlistspace_url = "https://botlist.space/api/bots/407189087649398795";
    String botsfordiscord_url = "https://botsfordiscord.com/api/v1/bots/407189087649398795";


    JSONObject data = new JSONObject();

    @Override
    public void onGuildJoin(GuildJoinEvent event) {

        data.put("shard_id", event.getJDA().getShardInfo().getShardId());
        data.put("shard_count", Main.shardManager.getShardsTotal());
        data.put("server_count", Main.shardManager.getGuilds().size());
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), data.toString());

        Request botlistspace = new Request.Builder()
                .url(botlistspace_url)
                .post(body)
                .addHeader("User-Agent", "DiscordBot")
                .addHeader("Authorization", SECRETS.botlistspace)
                .build();
        try {
            new OkHttpClient().newCall(botlistspace).execute().close();
            System.out.println("Successfully posted count for botlist.space!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void onGuildLeave(GuildLeaveEvent event) {

        data.put("shard_id", event.getJDA().getShardInfo().getShardId());
        data.put("shard_count", Main.shardManager.getShardsTotal());
        data.put("server_count", Main.shardManager.getGuilds().size());
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), data.toString());

        Request botlistspace = new Request.Builder()
                .url(botlistspace_url)
                .post(body)
                .addHeader("User-Agent", "DiscordBot")
                .addHeader("Authorization", SECRETS.botlistspace)
                .build();
        try {
            new OkHttpClient().newCall(botlistspace).execute().close();
            System.out.println("Successfully posted count for botlist.space!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
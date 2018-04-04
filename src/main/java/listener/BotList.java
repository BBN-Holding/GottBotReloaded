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
    @Override
    public void onGuildJoin(GuildJoinEvent event) {

        String botlistspace_url = "https://botlist.space/api/bots/407189087649398795";
        JSONObject data = new JSONObject();
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


        String botsfordiscord_url = "https://botsfordiscord.com/api/v1/bots/407189087649398795";

        data.put("shard_id", event.getJDA().getShardInfo().getShardId());
        data.put("shard_count", Main.shardManager.getShardsTotal());
        data.put("server_count", Main.shardManager.getGuilds().size());


        Request botsfordiscord = new Request.Builder()
                .url(botsfordiscord_url)
                .post(body)
                .addHeader("Authorization", SECRETS.botsfordiscord)
                .build();

        try {
            new OkHttpClient().newCall(botsfordiscord).execute().close();
            System.out.println("Successfully posted count for Bots for Discord!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String discordpw_url = "https://bots.discord.pw/api/bots/407189087649398795/stats";

        data.put("shard_id", event.getJDA().getShardInfo().getShardId());
        data.put("shard_count", Main.shardManager.getShardsTotal());
        data.put("server_count", Main.shardManager.getGuilds().size());


        Request discordpw = new Request.Builder()
                .url(discordpw_url)
                .post(body)
                .addHeader("Authorization", SECRETS.discordpw)
                .build();

        try {
            new OkHttpClient().newCall(discordpw).execute().close();
            System.out.println("Successfully posted count for discord.pw!");
        } catch (IOException e) {
            e.printStackTrace();
        }


        String discordbots_url = "https://bots.discord.pw/api/bots/407189087649398795/stats";

        data.put("shard_id", event.getJDA().getShardInfo().getShardId());
        data.put("shard_count", Main.shardManager.getShardsTotal());
        data.put("server_count", Main.shardManager.getGuilds().size());


        Request discordbots = new Request.Builder()
                .url(discordbots_url)
                .post(body)
                .addHeader("Authorization", SECRETS.discordbots)
                .build();

        try {
            new OkHttpClient().newCall(discordbots).execute().close();
            System.out.println("Successfully posted count for discord.pw!");
        } catch (IOException e) {
            e.printStackTrace();
        }


        String discordbotworld_url = "https://discordbot.world/api/bot/407189087649398795/stats";

        data.put("shard_id", event.getJDA().getShardInfo().getShardId());
        data.put("shard_count", Main.shardManager.getShardsTotal());
        data.put("server_count", Main.shardManager.getGuilds().size());


        Request discordbotworld = new Request.Builder()
                .url(discordbotworld_url)
                .post(body)
                .addHeader("Authorization", SECRETS.botworld)
                .build();

        try {
            new OkHttpClient().newCall(discordbotworld).execute().close();
            System.out.println("Successfully posted count for discordbot.world!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void onGuildLeave(GuildLeaveEvent event) {

        String botlistspace_url = "https://botlist.space/api/bots/407189087649398795";
        JSONObject data = new JSONObject();
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


        String botsfordiscord_url = "https://botsfordiscord.com/api/v1/bots/407189087649398795";

        data.put("shard_id", event.getJDA().getShardInfo().getShardId());
        data.put("shard_count", Main.shardManager.getShardsTotal());
        data.put("server_count", Main.shardManager.getGuilds().size());


        Request botsfordiscord = new Request.Builder()
                .url(botsfordiscord_url)
                .post(body)
                .addHeader("Authorization", SECRETS.botsfordiscord)
                .build();

        try {
            new OkHttpClient().newCall(botsfordiscord).execute().close();
            System.out.println("Successfully posted count for Bots for Discord!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String discordpw_url = "https://bots.discord.pw/api/bots/407189087649398795/stats";

        data.put("shard_id", event.getJDA().getShardInfo().getShardId());
        data.put("shard_count", Main.shardManager.getShardsTotal());
        data.put("server_count", Main.shardManager.getGuilds().size());


        Request discordpw = new Request.Builder()
                .url(discordpw_url)
                .post(body)
                .addHeader("Authorization", SECRETS.discordpw)
                .build();

        try {
            new OkHttpClient().newCall(discordpw).execute().close();
            System.out.println("Successfully posted count for discord.pw!");
        } catch (IOException e) {
            e.printStackTrace();
        }


        String discordbots_url = "https://bots.discord.pw/api/bots/407189087649398795/stats";

        data.put("shard_id", event.getJDA().getShardInfo().getShardId());
        data.put("shard_count", Main.shardManager.getShardsTotal());
        data.put("server_count", Main.shardManager.getGuilds().size());


        Request discordbots = new Request.Builder()
                .url(discordbots_url)
                .post(body)
                .addHeader("Authorization", SECRETS.discordbots)
                .build();

        try {
            new OkHttpClient().newCall(discordbots).execute().close();
            System.out.println("Successfully posted count for discord.pw!");
        } catch (IOException e) {
            e.printStackTrace();
        }


        String discordbotworld_url = "https://discordbot.world/api/bot/407189087649398795/stats";

        data.put("shard_id", event.getJDA().getShardInfo().getShardId());
        data.put("shard_count", Main.shardManager.getShardsTotal());
        data.put("server_count", Main.shardManager.getGuilds().size());


        Request discordbotworld = new Request.Builder()
                .url(discordbotworld_url)
                .post(body)
                .addHeader("Authorization", SECRETS.botworld)
                .build();

        try {
            new OkHttpClient().newCall(discordbotworld).execute().close();
            System.out.println("Successfully posted count for discordbot.world!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
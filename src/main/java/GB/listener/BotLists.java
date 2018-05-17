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

    String bfd_url = "https://botsfordiscord.com/api/v1/bots/407189087649398795";
    private String dbw_url = "https://discordbot.world/api/bot/407189087649398795/stats";

    private JSONObject json = new JSONObject();

    @Override
    public void onGuildJoin(GuildJoinEvent event) {

        // Count
        json.put("server_count", GottBot.shardManager.getGuilds().size());
        json.put("guild_count", GottBot.shardManager.getGuilds().size());
        json.put("shard_count", GottBot.shardManager.getShards().size());

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json.toString());


        DiscordBotListAPI api = new DiscordBotListAPI.Builder()
                .token(getConfig().BotLists.get("DiscordBots"))
                .build();

        try {
            api.setStats("407189087649398795", GottBot.shardManager.getShards().get(0).getGuilds().size(), GottBot.shardManager.getShards().get(0).getShardInfo().getShardId(), GottBot.shardManager.getShards().size());
            api.setStats("407189087649398795", GottBot.shardManager.getShards().get(1).getGuilds().size(), GottBot.shardManager.getShards().get(1).getShardInfo().getShardId(), GottBot.shardManager.getShards().size());
            System.out.println("Successfully posted count for DiscordBots!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // BotList.space

        BotlistSpaceClient botlistspace = new BotlistSpaceClient(getConfig().BotLists.get("BotListSpace"), "407189087649398795");
        try {
            botlistspace.postStats(GottBot.shardManager.getGuilds().size());
            System.out.println("Successfully posted count for BotList.Space!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // BotsForDiscord

        Request botsfordiscord = new Request.Builder()
                .url(bfd_url)
                .post(body)
                .addHeader("Authorization", getConfig().BotLists.get("BotsForDiscord"))
                .build();
        try {
            new OkHttpClient().newCall(botsfordiscord).execute().close();
            System.out.println("Successfully posted count for BotsForDiscord!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // DiscordBot.World

        Request discordbotworld = new Request.Builder()
                .url(dbw_url)
                .post(body)
                .addHeader("Authorization", getConfig().BotLists.get("DiscordBotWorld"))
                .build();
        try {
            new OkHttpClient().newCall(discordbotworld).execute().close();
            System.out.println("Successfully posted count for DiscordBot.World!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onGuildLeave(GuildLeaveEvent event) {



    }
}

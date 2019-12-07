package GB.listener;

import GB.core.Main;
import GB.stuff.SECRETS;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.discordbots.api.client.DiscordBotListAPI;
import org.json.JSONObject;
import space.botlist.api.*;

import java.io.IOException;

public class BotList extends ListenerAdapter {

    // URL's
    private String botsfordiscord_url = "https://botsfordiscord.com/api/v1/bots/407189087649398795";
    private String discordbotworld_url = "https://discordbot.world/api/bot/407189087649398795/stats";
    private String BoatList_url = "https://boat-list.glitch.me/api/stats/bot/407189087649398795/" + SECRETS.boatlist;
    private String discordpw_url = "https://bots.discord.pw/api/bots/407189087649398795/stats";
    private String terminal_url = "https://ls.terminal.ink/api/v1/bots/407189087649398795";

    private JSONObject json = new JSONObject();

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        // Count
        json.put("server_count", Main.shardManager.getGuilds().size());
        json.put("guild_count", Main.shardManager.getGuilds().size());
        json.put("shard_count", Main.shardManager.getShards().size());
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json.toString());

        // BotList.space

        BotlistSpaceClient botlistspace = new BotlistSpaceClient(SECRETS.botlistspace, "407189087649398795");
        try {
            botlistspace.postStats(Main.shardManager.getGuilds().size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // BotsForDiscord

        Request botsfordiscord = new Request.Builder()
                .url(botsfordiscord_url)
                .post(body)
                .addHeader("User-Agent", "DiscordBot")
                .addHeader("Authorization", SECRETS.botsfordiscord)
                .build();
        try {
            new OkHttpClient().newCall(botsfordiscord).execute().close();
            System.out.println("Successfully posted count for BotsForDiscord!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // DiscordBot.World

        Request discordbotworld = new Request.Builder()
                .url(discordbotworld_url)
                .post(body)
                .addHeader("User-Agent", "DiscordBot")
                .addHeader("Authorization", SECRETS.discordbotworld)
                .build();
        try {
            new OkHttpClient().newCall(discordbotworld).execute().close();
            System.out.println("Successfully posted count for DiscordBot.World!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // DiscordBots

        Request DiscordBots = new Request.Builder()
                .url(discordpw_url)
                .post(body)
                .addHeader("User-Agent", "DiscordBot")
                .addHeader("Authorization", SECRETS.discordpw)
                .build();
        try {
            new OkHttpClient().newCall(DiscordBots).execute().close();
            System.out.println("Successfully posted count for bots.discord.pw!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ls.terminal.ink

        Request terminal = new Request.Builder()
                .url(terminal_url)
                .post(body)
                .addHeader("User-Agent", "DiscordBot")
                .addHeader("Authorization", SECRETS.terminal)
                .build();
        try {
            new OkHttpClient().newCall(terminal).execute().close();
            System.out.println("Successfully posted count for ls.terminal.ink!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Boat-List

        body = RequestBody.create(null, new byte[0]);
        Request BoatList = new Request.Builder()
                .url(BoatList_url)
                .post(body)
                .build();

        try {
            new OkHttpClient().newCall(BoatList).execute().close();
            System.out.println("Successfully posted count for Boat List!");
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        // Discord Bot List

        DiscordBotListAPI DiscordBotList = new DiscordBotListAPI.Builder()
                .token(SECRETS.discordbotlist)
                .build();

        DiscordBotList.setStats("407189087649398795", Main.shardManager.getShards().get(0).getGuilds().size(), Main.shardManager.getShards().get(0).getShardInfo().getShardId(), Main.shardManager.getShards().size());
        DiscordBotList.setStats("407189087649398795", Main.shardManager.getShards().get(1).getGuilds().size(), Main.shardManager.getShards().get(1).getShardInfo().getShardId(), Main.shardManager.getShards().size());
    }




    @Override
    public void onGuildLeave(GuildLeaveEvent event) {
        // Count
        json.put("server_count", Main.shardManager.getGuilds().size());
        json.put("guild_count", Main.shardManager.getGuilds().size());
        json.put("shard_count", Main.shardManager.getShards().size());
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json.toString());

        // BotList.space

        BotlistSpaceClient botlistspace = new BotlistSpaceClient(SECRETS.botlistspace, "407189087649398795");
        try {
            botlistspace.postStats(Main.shardManager.getGuilds().size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // BotsForDiscord

        Request botsfordiscord = new Request.Builder()
                .url(botsfordiscord_url)
                .post(body)
                .addHeader("User-Agent", "DiscordBot")
                .addHeader("Authorization", SECRETS.botsfordiscord)
                .build();
        try {
            new OkHttpClient().newCall(botsfordiscord).execute().close();
            System.out.println("Successfully posted count for BotsForDiscord!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // DiscordBot.World

        Request discordbotworld = new Request.Builder()
                .url(discordbotworld_url)
                .post(body)
                .addHeader("User-Agent", "DiscordBot")
                .addHeader("Authorization", SECRETS.discordbotworld)
                .build();
        try {
            new OkHttpClient().newCall(discordbotworld).execute().close();
            System.out.println("Successfully posted count for DiscordBot.World!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // DiscordBots

        Request DiscordBots = new Request.Builder()
                .url(discordpw_url)
                .post(body)
                .addHeader("User-Agent", "DiscordBot")
                .addHeader("Authorization", SECRETS.discordpw)
                .build();
        try {
            new OkHttpClient().newCall(DiscordBots).execute().close();
            System.out.println("Successfully posted count for bots.discord.pw!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ls.terminal.ink

        Request terminal = new Request.Builder()
                .url(terminal_url)
                .post(body)
                .addHeader("User-Agent", "DiscordBot")
                .addHeader("Authorization", SECRETS.terminal)
                .build();
        try {
            new OkHttpClient().newCall(terminal).execute().close();
            System.out.println("Successfully posted count for ls.terminal.ink!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Boat-List

        body = RequestBody.create(null, new byte[0]);
        Request BoatList = new Request.Builder()
                .url(BoatList_url)
                .post(body)
                .build();

        try {
            new OkHttpClient().newCall(BoatList).execute().close();
            System.out.println("Successfully posted count for Boat List!");
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        // Discord Bot List

        DiscordBotListAPI DiscordBotList = new DiscordBotListAPI.Builder()
                .token(SECRETS.discordbotlist)
                .build();

        DiscordBotList.setStats("407189087649398795", Main.shardManager.getShards().get(0).getGuilds().size(), Main.shardManager.getShards().get(0).getShardInfo().getShardId(), Main.shardManager.getShards().size());
        DiscordBotList.setStats("407189087649398795", Main.shardManager.getShards().get(1).getGuilds().size(), Main.shardManager.getShards().get(1).getShardInfo().getShardId(), Main.shardManager.getShards().size());

    }
}

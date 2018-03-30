package listener;

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

public class BotList extends ListenerAdapter {
    @Override
    public void onGuildJoin(GuildJoinEvent event) {

        String botlistspace_url = "https://botlist.space/api/bots/407189087649398795";
        JSONObject data = new JSONObject();
        data.put("server_count", event.getJDA().getGuilds().size());
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), data.toString());

        Request botlistspace = new Request.Builder()
                .url(botlistspace_url)
                .post(body)
                .addHeader("User-Agent", "DiscordBot")
                .addHeader("Authorization", SECRETS.botlistspace)
                .build();
        try {
            new OkHttpClient().newCall(botlistspace).execute();
            System.out.println("Successfully posted count for botlist.space!");
        } catch(IOException e) {
            e.printStackTrace();
        }



        String botsfordiscord_url = "https://botsfordiscord.com/api/v1/bots/407189087649398795";

        data.put("server_count", event.getJDA().getGuilds().size());


        Request botsfordiscord = new Request.Builder()
                .url(botsfordiscord_url)
                .post(body)
                .addHeader("Authorization", SECRETS.botsfordiscord)
                .build();

        try {
            new OkHttpClient().newCall(botsfordiscord).execute();
            System.out.println("Successfully posted count for Bots for Discord!");
        } catch(IOException e) {
            e.printStackTrace();
        }

        String discordpw_url = "https://bots.discord.pw/api/bots/407189087649398795/stats";

        data.put("server_count", event.getJDA().getGuilds().size());


        Request discordpw = new Request.Builder()
                .url(discordpw_url)
                .post(body)
                .addHeader("Authorization", SECRETS.discordpw)
                .build();

        try {
            new OkHttpClient().newCall(discordpw).execute();
            System.out.println("Successfully posted count for discord.pw!");
        } catch(IOException e) {
            e.printStackTrace();
        }





        DiscordBotListAPI api = new DiscordBotListAPI.Builder()
                .token(SECRETS.discordbots)
                .build();

        String botId = "407189087649398795";
        int serverCount = event.getJDA().getGuilds().size();

        api.setStats(botId, serverCount);

    }



    public void onGuildLeave(GuildLeaveEvent event) {
        String url = "https://botlist.space/api/bots/407189087649398795";

        JSONObject data = new JSONObject();
        data.put("server_count", event.getJDA().getGuilds().size());

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), data.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("User-Agent", "DiscordBot")
                .addHeader("Authorization", SECRETS.botlistspace)
                .build();

        try {
            new OkHttpClient().newCall(request).execute();
            System.out.println("Successfully posted count for botlist.space!");
        } catch(IOException e) {
            e.printStackTrace();
        }



        String botsfordiscord_url = "https://botsfordiscord.com/api/v1/bots/407189087649398795";

        data.put("server_count", event.getJDA().getGuilds().size());


        Request botsfordiscord = new Request.Builder()
                .url(botsfordiscord_url)
                .post(body)
                .addHeader("Authorization", SECRETS.botsfordiscord)
                .build();

        try {
            new OkHttpClient().newCall(botsfordiscord).execute();
            System.out.println("Successfully posted count for Bots for Discord!");
        } catch(IOException e) {
            e.printStackTrace();
        }
        String discordpw_url = "https://bots.discord.pw/api/bots/407189087649398795/stats";

        data.put("server_count", event.getJDA().getGuilds().size());


        Request discordpw = new Request.Builder()
                .url(discordpw_url)
                .post(body)
                .addHeader("Authorization", SECRETS.discordpw)
                .build();

        try {
            new OkHttpClient().newCall(discordpw).execute();
            System.out.println("Successfully posted count for discord.pw!");
        } catch(IOException e) {
            e.printStackTrace();
        }

        DiscordBotListAPI api = new DiscordBotListAPI.Builder()
                .token(SECRETS.discordbots)
                .build();

        String botId = "407189087649398795";
        int serverCount = event.getJDA().getGuilds().size();

        api.setStats(botId, serverCount);


    }
}
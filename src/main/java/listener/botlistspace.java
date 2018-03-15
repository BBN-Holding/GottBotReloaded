package listener;

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

public class botlistspace extends ListenerAdapter {
    @Override
    public void onGuildJoin(GuildJoinEvent event) {

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
    }
    public void onGuildLeave(GuildLeaveEvent event) {
        String url = "https://botlist.space/api/bots/" + event.getJDA().getSelfUser().getId();

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
    }
}
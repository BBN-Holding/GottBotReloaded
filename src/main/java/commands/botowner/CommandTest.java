package commands.botowner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import commands.Command;
import core.MessageHandler;
import de.foryasee.httprequest.HttpRequestBuilder;
import de.foryasee.httprequest.RequestHeader;
import de.foryasee.httprequest.RequestResponse;
import de.foryasee.httprequest.RequestType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.json.JSONArray;
import org.json.JSONObject;
import util.Embed;

import java.io.IOException;
import java.util.Arrays;

public class CommandTest implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
           new MessageBuilder().setEmbed(Embed.normal(MessageHandler.get(event.getAuthor()).getString("testtitel"), MessageHandler.get(event.getAuthor()).getString("testtext")).build()).build();

            HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder("https://botlist.space/api/bots/407189087649398795", RequestType.GET)
                    .setRequestHeader(new RequestHeader().addField("User-Agent", "DiscordBot"));

            try {
                RequestResponse requestResponse = httpRequestBuilder.sendRequest();
                System.out.println("ResponseCode: " + requestResponse.getResponseCode());
                String out = requestResponse.getResponseMessage();

                JSONObject jsonObject = new JSONObject(out);

                JSONObject chapitresJsonObject = jsonObject.getJSONObject("chapitres");

                JSONArray chapitreJsonArray = chapitresJsonObject.getJSONArray("chapitre");

                for (int i = 0; i < chapitreJsonArray.length(); i++) {

                    JSONObject ChapJsonObject = chapitreJsonArray.getJSONObject(i);

                    String id = ChapJsonObject.getString("author");
                    String name = ChapJsonObject.getString("name");
                    String desc = ChapJsonObject.getString("description");

                    System.out.println(id);
                    System.out.println(name);
                    System.out.println(desc);

                }



            } catch (IOException e) {
                e.printStackTrace();
            }






        } else {
            new MessageBuilder().setEmbed(Embed.error(MessageHandler.get(event.getAuthor()).getString("nopermstitel"), MessageHandler.get(event.getAuthor()).getString("nopermstext")).build()).build();
        }


    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}

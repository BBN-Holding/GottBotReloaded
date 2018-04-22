package commands.tools;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import commands.Command;
import de.foryasee.httprequest.*;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.IOException;

public class CommandBotInfo implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }
    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length == 0 || args.length == 1 ||  args.length == 2) {
            if (event.getMessage().getContentRaw().endsWith("dbw")) {
                event.getTextChannel().sendMessage("Discord Bot World").queue();



                try {
                    HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder("https://discordbot.world/api/bot/" + args[0] + "/info", RequestType.GET);
                    RequestResponse requestResponse = httpRequestBuilder.sendRequest();
                    System.out.println(requestResponse.getResponseMessage());

                    JsonObject jsonObject = new JsonParser().parse(requestResponse.getResponseMessage()).getAsJsonObject();
                    event.getTextChannel().sendMessage(jsonObject.toString()).queue();
                    event.getTextChannel().sendMessage(new EmbedBuilder()
                            .setThumbnail(jsonObject.get("avatar").getAsString())
                            .setTitle("Botinfo: " + jsonObject.get("name").getAsString())
                            .addField("GitHub", jsonObject.get("git").getAsString(), true)
                            .build()).queue();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (event.getMessage().getContentRaw().endsWith("dbl")) {


                event.getTextChannel().sendMessage("Discord Bots").queue();


            } else {


                event.getTextChannel().sendMessage("Nothing :(").queue();


            }

        } else {
            event.getTextChannel().sendMessage("Zu kurz!").queue();
        }
    }
    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}

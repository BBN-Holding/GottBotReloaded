package GB.commands.tools;

import GB.Handler;
import GB.core.Main;
import GB.stuff.SECRETS;
import GB.commands.Command;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import okhttp3.*;
import okhttp3.Request;
import okhttp3.Response;
import org.discordbots.api.client.DiscordBotListAPI;
import GB.stuff.DATA;
import org.discordbots.api.client.entity.SimpleUser;
import space.botlist.api.BotlistSpaceClient;

import java.io.IOException;

import static GB.stuff.DATA.*;

public class CommandUpvoted implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        BotlistSpaceClient botlistspace = new BotlistSpaceClient(SECRETS.botlistspace, "407189087649398795");

        User u = event.getAuthor();
        String BBN = DATA.BBNS;



        try {
            Request request = new Request.Builder()
                    .url("https://botlist.space/api/bots/407189087649398795/upvotes")
                    .addHeader("Authorization", SECRETS.botlistspace)
                    .build();
                Response respose = new OkHttpClient().newCall(request).execute();
                System.out.println(respose);
            if (event.getMessage().getGuild().getId().equals(BBN)) {
                if (u.getId().contains(u.getId())) {

                    event.getJDA().getGuildById("396732579920740352").getController().addSingleRoleToMember(event.getMember(), event.getJDA().getRoleById(Premium)).queue();

                    event.getTextChannel().sendMessage("SUCCES JA LEL").queue();
                    new Handler().getMySQL().insert("premium", "id", u.getId());

                } else
                    event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.error", "tools.upvoted", "https://discordbots.org/bot/407189087649398795/vote", "error", event)).queue();
            } else {

                event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.error", "util.bbnguild", "https://disco.gg/bbn", "error", event)).queue();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}

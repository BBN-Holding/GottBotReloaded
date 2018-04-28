package GB.commands.tools;

import GB.Handler;
import GB.stuff.SECRETS;
import GB.commands.Command;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.discordbots.api.client.DiscordBotListAPI;
import GB.stuff.DATA;
import org.discordbots.api.client.entity.SimpleUser;

import static GB.stuff.DATA.*;

public class CommandUpvoted implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        DiscordBotListAPI api = new DiscordBotListAPI.Builder()
                .token(SECRETS.discordbots)
                .build();

        User u = event.getAuthor();
            String BBN = DATA.BBNS;


                SimpleUser voters = api.getVoters("407189087649398795", 1);

                if (event.getMessage().getGuild().getId().equals(BBN)) {
                    if (voters.toString().contains(u.getId())) {

                        event.getJDA().getGuildById("396732579920740352").getController().addSingleRoleToMember(event.getMember(), event.getJDA().getRoleById(Premium)).queue();

                        event.getTextChannel().sendMessage("SUCCES JA LEL").queue();
                        new Handler().getMySQL().insert("premium", "id", u.getId());

                    } else
                        event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.error", "tools.upvoted", "https://discordbots.org/bot/407189087649398795/vote", "error", event)).queue();
                } else {

                    event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.error", "util.bbnguild", "https://disco.gg/bbn", "error", event)).queue();
                }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}

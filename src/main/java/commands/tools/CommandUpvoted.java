package commands.tools;

import commands.Command;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.discordbots.api.client.DiscordBotListAPI;
import stuff.DATA;
import stuff.SECRETS;

import java.awt.*;
import java.util.List;

import static stuff.DATA.*;

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

            List<String> voters = api.getVoterIds("407189087649398795", 1);


                if (event.getMessage().getGuild().getId().equals(BBN)) {
                    if (voters.contains(u.getId())) {

                        event.getJDA().getGuildById("396732579920740352").getController().addSingleRoleToMember(event.getMember(), event.getJDA().getRoleById(Premium)).queue();

                        event.getTextChannel().sendMessage("SUCCES JA LEL").queue();
                        MySQL.insert("premium", "id", u.getId());

                    } else
                        event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.error", "tools.upvoted", "https://discordbots.org/bot/407189087649398795/vote", "error", event)).queue();
                } else {

                    event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.error", "util.bbnguild", "https://disco.gg/bbn", "error", event)).queue();
                }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}
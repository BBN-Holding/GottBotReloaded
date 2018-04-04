package commands.usercommands;

import commands.Command;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.react.GuildMessageReactionAddEvent;
import stuff.DATA;

import java.awt.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommandPremium implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length!=1) {
            String status;
            if (MySQL.get("user", "id", event.getAuthor().getId(), "premium").equals("none")) status="none";
            else {
                Date date = new Date();
                date.setTime(Long.parseLong(MySQL.get("user", "id", event.getAuthor().getId(), "premium")));
                status= "until "+date.toGMTString();
            }
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Premium").setDescription("Your Premium status is: ``"+ status+"``").build()).queue();
        } else if (args[0].equalsIgnoreCase("buy")) {
            if (MySQL.get("user", "id", event.getAuthor().getId(), "premium").equalsIgnoreCase("none")) {
                long Date = TimeUnit.MILLISECONDS.toDays(new Date().getTime());
                Date= Date+31;
                Date = TimeUnit.DAYS.toMillis(Date);
                if (Long.parseLong(MySQL.get("user", "id", event.getAuthor().getId(), "hashes"))>=750000) {
                    MySQL.update("user", "premium", String.valueOf(Date), "id", event.getAuthor().getId());
                    MySQL.update("user", "hashes", String.valueOf(Long.parseLong(MySQL.get("user", "id", event.getAuthor().getId(), "hashes"))-750000), "id", event.getAuthor().getId());
                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Premium buyed").setDescription("You have buy Premium").build()).queue();
                    Guild bbn = event.getJDA().getGuildById(DATA.BBNS);
                    try {
                        bbn.getController().addSingleRoleToMember(bbn.getMember(event.getAuthor()), bbn.getRoleById(408660274103451649L)).queue();
                    } catch (IllegalArgumentException e) {
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("You must be on the official BigBotNetwork Server https://disco.gg/bbn").build()).queue();
                    }
                } else event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Mine!").setDescription("You don't have enough Hashes... Mine! https://miner.bigbotnetwork.de").build()).queue();
            } else event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("You already have Premium").setColor(Color.RED).build()).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}

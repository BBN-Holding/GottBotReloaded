package commands.usercommands;

import commands.Command;
import core.Main;
import core.MessageHandler;
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
            if (MySQL.get("user", "id", event.getAuthor().getId(), "premium").equals("none")) status= MessageHandler.get(event.getAuthor()).getString("util.none");
            else {
                Date date = new Date();
                date.setTime(Long.parseLong(MySQL.get("user", "id", event.getAuthor().getId(), "premium")));
                status= "until "+date.toGMTString();
            }
            event.getTextChannel().sendMessage(MessageHandler.getEmbed("usercommands.premium.status.title", "usercomamnds.premium.status.text", status,"normal", event)).queue();
        } else if (args[0].equalsIgnoreCase("buy")) {
            Guild bbn = Main.shardManager.getGuildById(DATA.BBNS);
            if (event.getGuild().getId().equals(bbn.getId())) {
                if (MySQL.get("user", "id", event.getAuthor().getId(), "premium").equalsIgnoreCase("none")) {
                    long Date = TimeUnit.MILLISECONDS.toDays(new Date().getTime());
                    Date = Date + 31;
                    Date = TimeUnit.DAYS.toMillis(Date);
                    if (Long.parseLong(MySQL.get("user", "id", event.getAuthor().getId(), "hashes")) >= 750000) {
                        MySQL.update("user", "premium", String.valueOf(Date), "id", event.getAuthor().getId());
                        MySQL.update("user", "hashes", String.valueOf(Long.parseLong(MySQL.get("user", "id", event.getAuthor().getId(), "hashes")) - 750000), "id", event.getAuthor().getId());
                        bbn.getController().addSingleRoleToMember(bbn.getMember(event.getAuthor()), bbn.getRoleById(408660274103451649L)).queue();
                        event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.sucess", "usercomamnds.premium.buyed", "", "sucess", event)).queue();
                    } else
                        event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.error", "util.mine", "https://miner.bigbotnetwork.de/", "error", event)).queue();
                } else
                    event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.error", "usercommands.premium.alreadyhas", "", "error", event)).queue();
            } else event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.error", "util.bbnguild", "https://disco.gg/bbn", "error", event)).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}

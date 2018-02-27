package commands.botowner;


import commands.Command;
import core.MessageHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.entities.Guild;

import java.awt.*;
import java.util.List;

public class guilds implements Command {


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        StringBuilder runningOnServers = new StringBuilder();
        int count_server = 1;

        List<Guild> guild_sublist;
        int SideNumbInput = 1;
        if (args.length > 0) {
            SideNumbInput = Integer.parseInt(args[0]);
            System.out.println(SideNumbInput);
        }

        if (event.getJDA().getGuilds().size() > 20) {
            guild_sublist = event.getJDA().getGuilds().subList((SideNumbInput - 1) * 20, (SideNumbInput - 1) * 20 + 20);
        } else {
            guild_sublist = event.getJDA().getGuilds();
        }


        int sideNumbAll;
        if (event.getJDA().getGuilds().size() >= 20) {
            for (Guild guild : guild_sublist) {
                runningOnServers.append("`\t " + (((SideNumbInput - 1) * 20) + count_server) + ". ").append(guild.getName()).append("(").append(guild.getId()).append(")`\n");
                count_server++;
            }
            sideNumbAll = event.getJDA().getGuilds().size() / 20;
        } else {
            for (Guild guild : guild_sublist) {
                runningOnServers.append("`\t " + count_server + ". ").append(guild.getName()).append("(").append(guild.getId()).append(")`\n");
                count_server++;
            }
            sideNumbAll = 1;
        }


        int sideNumb = SideNumbInput;

        event.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Bot running on following guilds" + "`Total guilds: " + event.getJDA().getGuilds().size() + " - Side " + sideNumb + " / " + sideNumbAll + "`\n\n" + runningOnServers.toString())
                .setTitle("Titel").setColor(Color.MAGENTA).build()).queue();
        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("prefixtitel"))
                .setDescription(MessageHandler.get(event.getAuthor()).getString("prefixtext").replaceAll("gb.", MessageHandler.getprefix(event.getGuild()))).setColor(Color.CYAN).build()).queue();

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }

    @Override
    public String help() {
        return null;
    }
}

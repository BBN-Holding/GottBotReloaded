package commands.botowner;

import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import stuff.DATA;

import java.time.format.DateTimeFormatter;

public class CommandGuild implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<1) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Guilds Information - Help").setDescription("gb.guild [ID]").build()).queue();
        } else if (args.length == 1) {
            Guild guild = event.getJDA().getGuildById(DATA.BBNS);
            if (event.getJDA().getGuildById(args[0]).isAvailable()) {
                guild = event.getJDA().getGuildById(args[0]);
            }
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Guild Information")
                    .addField("Name", guild.getName(), true)
                    .addField("Created", guild.getCreationTime().format(DateTimeFormatter.ofPattern("dd.MM.yy, HH:mm:ss")), true)
                    .addField("Member", String.valueOf(guild.getMembers().size()), true)
                    .addField("Region", guild.getRegionRaw(), true)
                    .addField("Emotes", String.valueOf(guild.getEmotes().size()), true)
                    .addField("Owner", guild.getOwner().getUser().getName(), true)
                    .addField("Roles", String.valueOf(guild.getRoles().size()), true)
                    .addField("TextChannels", String.valueOf(guild.getTextChannels().size()), true)
                    .addField("VoiceChannels", String.valueOf(guild.getVoiceChannels().size()), true)
                    .setThumbnail(guild.getIconUrl())
                    .build()).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}
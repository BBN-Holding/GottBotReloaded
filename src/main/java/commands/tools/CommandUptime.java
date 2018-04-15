package commands.tools;

import commands.Command;
import core.MessageHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

public class CommandUptime implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        long uptime = ManagementFactory.getRuntimeMXBean().getUptime();
        event.getTextChannel().sendMessage(new EmbedBuilder().setDescription(MessageHandler.get("tools.uptime",event.getAuthor(),event.getGuild()) + " " + String.valueOf(TimeUnit.MILLISECONDS.toDays(uptime) + "d " + TimeUnit.MILLISECONDS.toHours(uptime) % 24 + "h " + TimeUnit.MILLISECONDS.toMinutes(uptime) % 60 + "m " + TimeUnit.MILLISECONDS.toSeconds(uptime) % 60 + "s")).setTitle(":clock: "+MessageHandler.get("tools.uptime",event.getAuthor(),event.getGuild())+" :clock:").build()).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}

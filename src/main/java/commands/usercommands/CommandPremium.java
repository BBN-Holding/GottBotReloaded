package commands.usercommands;

import commands.Command;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

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
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Premium").setDescription("Your Premium status is: ``"+ MySQL.get("user", "id", event.getAuthor().getId(), "premium")+"``").build()).queue();
        } else if (args[0].equalsIgnoreCase("buy")) {
            System.out.println(System.currentTimeMillis());
            System.out.println(TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis()));
            if (MySQL.get("user", "id", event.getAuthor().getId(), "premium").equalsIgnoreCase("none")) {
                System.out.println(new Date().getTime());
                long Date = TimeUnit.MILLISECONDS.toDays(new Date().getTime());
                Date= Date+31;
                Date = TimeUnit.DAYS.toMillis(Date);
                if (Long.parseLong(MySQL.get("user", "id", event.getAuthor().getId(), "hashes"))>=750000) {
                    MySQL.update("user", "premium", String.valueOf(Date), "id", event.getAuthor().getId());
                    MySQL.update("user", "hashes", String.valueOf(Long.parseLong(MySQL.get("user", "id", event.getAuthor().getId(), "hashes"))-750000), "id", event.getAuthor().getId());
                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Premium buyed").setDescription("You have buy Premium").build()).queue();
                }
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}

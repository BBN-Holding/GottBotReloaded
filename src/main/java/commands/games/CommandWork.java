package commands.games;

import commands.Command;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;

public class CommandWork implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
             Random random = new Random();
            int value = random.nextInt();
            while (value > 100 || value < 0) {
                value = random.nextInt();
            }
            Long current = Long.parseLong(MySQL.get("user", "id", event.getAuthor().getId(), "hashes"));
            current = current + value;
            MySQL.update("user", "hashes", String.valueOf(current), "id", event.getAuthor().getId());
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("worktitel").replace("User", event.getAuthor().getName()))
                    .setDescription(MessageHandler.get(event.getAuthor()).getString("worktext").replace("User", event.getAuthor().getName()).replace("%v", String.valueOf(value))).build()).queue();

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}

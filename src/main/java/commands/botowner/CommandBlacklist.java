package commands.botowner;

import commands.Command;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandBlacklist implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Handler.get(event.getAuthor())) {
            if (args.length < 1) {
                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Blacklist - Help").setDescription("Do gb.blacklist add @User or gb.blacklist remove @User. DO IT JUST DO IT!").build()).queue();
            } else {
                switch (args[0].toLowerCase()) {
                    case "add":
                        User user = event.getMessage().getMentionedUsers().get(0);
                        MySQL.update("user", "blacklist", "true", "id", user.getId());
                        break;

                    case "remove":
                        User usa = event.getMessage().getMentionedUsers().get(0);
                        MySQL.update("user", "blacklist", "false", "id", usa.getId());
                        break;
                }
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}

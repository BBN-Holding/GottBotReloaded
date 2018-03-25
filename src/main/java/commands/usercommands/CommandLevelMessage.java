package commands.usercommands;

import commands.Command;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandLevelMessage implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<1) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("LevelMessage - Help").setDescription("You can use gb.lvlmessage <true|false> to activate or deactivate the levelmessage.").build()).queue();
        } else {
            if (args[0].equalsIgnoreCase("true")) {
                MySQL.update("user", "lvlmessage", "true", "id", event.getAuthor().getId());
                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Changed").setDescription("You get any Levelmessages in the future").build()).queue();
            } else if (args[0].equalsIgnoreCase("false")) {
                MySQL.update("user", "lvlmessage", "false", "id", event.getAuthor().getId());
                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Changed").setDescription("You don't get any Levelmessages in the future").build()).queue();
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}

package commands.botowner;

import commands.Command;
import core.MessageHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Embed;

public class CommandLeave implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {

            event.getJDA().getGuildById(args[0]).leave().queue();

            event.getTextChannel().sendMessage(Embed.normal("Succesfully lefted", "Left the Server " + event.getJDA().getGuildById(args[0])).build()).queue();

        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}

package commands.tools;

import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandPing implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        event.getTextChannel().sendMessage(new EmbedBuilder().setDescription(":stopwatch: " + event.getJDA().getPing()).build()).queue();


    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }


}

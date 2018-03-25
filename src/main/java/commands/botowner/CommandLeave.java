package commands.botowner;

import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandLeave implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {

            event.getJDA().getGuildById(args[0]).leave().queue();
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Server lefted").setDescription("Left the Server "+event.getJDA().getGuildById(args[0]).getName()).build()).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}

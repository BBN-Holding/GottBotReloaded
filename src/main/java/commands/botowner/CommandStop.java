package commands.botowner;

import commands.Command;
import core.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandStop implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
            event.getMessage().getTextChannel().sendMessage(new EmbedBuilder().setDescription(":white_check_mark:  Bot herruntergefahren  :white_check_mark: ").build()).queue();
            Main.jda.shutdown();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}
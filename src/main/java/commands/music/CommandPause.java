package commands.music;

import commands.Command;
import core.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandPause implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendMessage("TJA GIBTS HALT NOCH NICHT!!!").queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}

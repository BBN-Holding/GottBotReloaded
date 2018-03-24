package commands.botowner;

import commands.Command;
import core.Main;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandRestart implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
            Main.main(Main.args);
            event.getTextChannel().sendMessage("Sucessful restarted").queue();
            Main.jda.shutdown();
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

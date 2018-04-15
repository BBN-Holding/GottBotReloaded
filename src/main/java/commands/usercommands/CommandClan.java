package commands.usercommands;

import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandClan implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length==0) {
            
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}

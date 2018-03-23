package commands.botowner;

import commands.Command;
import core.MySQL;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandPremium implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            if (args.length < 1) {
                event.getTextChannel().sendMessage("ZU KURZ EY").queue();
            } else switch (args[0].toLowerCase()) {
                case "add":
                case "remove":
                    break;
                case "check":
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}

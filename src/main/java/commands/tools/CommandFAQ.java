package commands.tools;

import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


public class CommandFAQ implements Command {
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
                case "messageid":
                    break;
                case "roleid":
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

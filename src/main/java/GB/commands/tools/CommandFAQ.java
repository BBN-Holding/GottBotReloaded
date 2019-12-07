package GB.commands.tools;

import GB.commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


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
                    event.getTextChannel().sendMessage("I am sorry here is no gif :c").queue();
                    break;
                case "roleid":
                    event.getTextChannel().sendMessage("I am sorry here is no gif :c").queue();
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

package GB.commands.botowner;

import GB.Handler;
import GB.MessageHandler;
import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandSetXP implements Command {



    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (commands.botowner.Owner.get(event.getAuthor())) {
            new Handler().getMySQL().update("user", "xp", args[1], "id", args[0]);
            event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("botowner.setlvl.title", "botowner.setlvl.text", "", "normal", event)).queue();
        }
    }
    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}

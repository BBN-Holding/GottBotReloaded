package GB.commands.botowner;

import GB.core.MessageHandler;
import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandLeave implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (commands.botowner.Owner.get(event.getAuthor())) {

            event.getJDA().getGuildById(args[0]).leave().queue();
            event.getTextChannel().sendMessage(MessageHandler.getEmbed("botowner.leave.title", "botowner.leave.text" , String.valueOf(event.getJDA().getGuildById(args[0])), "normal", event)).queue();

        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}

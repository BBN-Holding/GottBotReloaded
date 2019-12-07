package GB.commands.botowner;

import GB.Handler;
import GB.commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public class CommandTest implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        System.out.println("lololololololol");
        if (Owner.get(event.getAuthor())) {
           event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("botowner.test.title", "botowner.test.text", "", "normal", event)).queue();
        } else {
            event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.error", "util.nopermissionuser", "", "error", event)).queue();
        }


    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}

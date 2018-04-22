package GB.commands.usercommands;

import GB.Handler;
import GB.MessageHandler;
import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandLevelMessage implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<1) {
            event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("usercommands.levelmessage.title", "usercommands.levelmessage.description","","normal", event)).queue();
        } else {
            if (args[0].equalsIgnoreCase("true")) {
                new Handler().getMySQL().update("user", "lvlmessage", "true", "id", event.getAuthor().getId());
                event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.sucess","usercomamnds.levelmessage.true","", "sucess", event)).queue();
            } else if (args[0].equalsIgnoreCase("false")) {
                new Handler().getMySQL().update("user", "lvlmessage", "false", "id", event.getAuthor().getId());
                event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.sucess", "usercomamnds.levelmessage.false","", "sucess", event)).queue();
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}

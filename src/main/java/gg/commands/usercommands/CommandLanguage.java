package gb.commands.usercommands;

import gb.GottBot;
import gb.Handler.CommandHandling.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class CommandLanguage implements Command {
    @Override
    public String[] Aliases() {
        return new String[]{"lang", "l", "language"};
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<1) {
            event.getTextChannel().sendMessage(
                    GottBot.getMessage().getEmbed("commands.user.lang.help.title", "commands.user.lang.help.text", Color.CYAN, event)
            ).queue();
        } else {
            String testString=GottBot.getMessage().getString("commands.owner.test", args[0], event);
            if (testString.equals("commands.owner.test not found")) {
                event.getTextChannel().sendMessage(GottBot.getMessage().getEmbed("util.error", "commands.user.lang.notavailible", Color.RED, event)).queue();
            } else {
                GottBot.getDB().update("user", "userid", event.getAuthor().getId(), GottBot.getDB().getR().hashMap("language", args[0]));
                event.getTextChannel().sendMessage(GottBot.getMessage().getEmbed("util.sucess", "commands.user.lang.sucess", Color.GREEN, event)).queue();
            }
        }
    }
}

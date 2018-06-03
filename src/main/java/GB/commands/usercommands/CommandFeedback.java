package GB.commands.usercommands;

import GB.GottBot;
import GB.Handler.CommandHandling.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class CommandFeedback implements Command {
    @Override
    public String[] Aliases() {
        return new String[]{"feedback", "ideas"};
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<1) {
            event.getTextChannel().sendMessage(
                    GottBot.getMessage().getEmbed("commands.user.FeedBackHelptitle", "commands.user.FeedBackHelptitle", Color.CYAN, event)
            ).queue();
        } else {
            String message=event.getMessage().getContentRaw().replace(GottBot.getMessage().getPrefix(event.getGuild().getId()), "");
            for (String string:Aliases()) {
                message = message.replace(string, "");
            }
            GottBot.getShardManager().getTextChannelById(GottBot.getConfig().getFeedBackandBugTextchannelid()).sendMessage(
                    "New FeedBack from "+event.getAuthor().getName()+" ("+event.getAuthor().getId()+")!\n```\n" +
                          message +
                          "\n```"
            ).queue();
            event.getTextChannel().sendMessage(GottBot.getMessage().getEmbed("util.sucess","commands.user.FeedBack.succes", Color.GREEN, event)).queue();
        }
    }
}

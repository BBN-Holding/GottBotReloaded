package gb.commands.moderation;

import com.rethinkdb.RethinkDB;
import gb.GottBot;
import gb.Handler.CommandHandling.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandPrefix implements Command {
    @Override
    public String[] Aliases() {
        return new String[]{"prefix"};
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<1) {
            event.getTextChannel().sendMessage(GottBot.getMessage().getCommandTemplate(Aliases(), "gb.prefix [prefix]", "Change the Preifx")).queue();
        } else {
            GottBot.getDB().update("server", "serverid", event.getGuild().getId(),
                    RethinkDB.r.hashMap("prefix",
                            event.getMessage().getContentRaw().replaceFirst(GottBot.getMessage().getPrefix(
                                    event.getGuild().getId()), "").replaceFirst(event.getGuild().getSelfMember().getAsMention(), "").replaceFirst(
                                    "prefix ", "")
                    )
            );
            event.getTextChannel().sendMessage("Da prefix is now "+GottBot.getMessage().getPrefix(event.getGuild().getId())).queue();
        }
    }
}

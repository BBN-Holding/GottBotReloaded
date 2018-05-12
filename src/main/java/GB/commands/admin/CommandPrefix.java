package GB.commands.admin;

import GB.GottBot;
import GB.Handler.CommandHandling.Command;
import com.rethinkdb.RethinkDB;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandPrefix implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        // TODO: Test it
        if (args.length>0) {
            GottBot.getDB().update(RethinkDB.r.hashMap("CommandPrefix", args[0]));
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Your Prefix is now: "+args[0]).build()).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}

package GB.commands.usercommands;

import GB.Handler;
import GB.commands.Command;
import com.rethinkdb.RethinkDB;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;

public class CommandHelp implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)&&event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_HISTORY)) {
            if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                Message message = event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("usercommands.help.loading", "usercommands.help.loading", "", "normal", event)).complete();
                new Handler().getMySQL().insert("helpmenu", new RethinkDB().hashMap("id", event.getAuthor().getId()).with("message", message.getId()));
                event.getTextChannel().editMessageById(message.getId(), new Handler().getMenuHandler().getMessage("\uD83D\uDD19", new EmbedBuilder().setTitle(new Handler().getMessageHandler().get("Helpmenu.helpmenu", event.getAuthor(),event.getGuild()) + " - ").build(), event.getAuthor(), event.getGuild())).queue();
                List<String> list = new Handler().getMenuHandler().getemote("\uD83D\uDD19", new Handler().getMessageHandler().getEmbed("Helpmenu.helpmenu", "Helpmenu.helpmenu", "", "normal", event), event.getAuthor(), event.getGuild());
                while (list.size() > 0) {
                    message.addReaction(list.get(0)).queue();
                    list.remove(0);
                }
            } else {
                event.getTextChannel().sendMessage("I need Manage Messages Permission").queue();
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}

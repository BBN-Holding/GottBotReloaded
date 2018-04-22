package commands.usercommands;

import commands.Command;
import core.MenuHandlerold;
import core.MessageHandler;
import core.MySQL;
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
                Message message = event.getTextChannel().sendMessage(MessageHandler.getEmbed("usercommands.help.loading", "usercommands.help.loading", "", "normal", event)).complete();
                new Handler().getMySQL().insert("helpmenu", "id`,`message", event.getAuthor().getId() + "','" + message.getId());
                event.getTextChannel().editMessageById(message.getId(), MenuHandlerold.getMessage("\uD83D\uDD19", new EmbedBuilder().setTitle(MessageHandler.get("Helpmenu.helpmenu", event.getAuthor(),event.getGuild()) + " - ").build(), event.getAuthor(), event.getGuild())).queue();
                List<String> list = MenuHandlerold.getemote("\uD83D\uDD19", MessageHandler.getEmbed("Helpmenu.helpmenu", "Helpmenu.helpmenu", "", "normal", event), event.getAuthor(), event.getGuild());
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

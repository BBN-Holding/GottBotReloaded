package GB.commands.moderation;

import GB.Handler;
import GB.commands.Command;
import GB.commands.botowner.Owner;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandPrefix implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getAuthor().getId()==event.getGuild().getOwner().getUser().getId() || event.getMember().hasPermission(Permission.MANAGE_SERVER) || Owner.get(event.getAuthor())) {
            if (args.length < 1) {
                try {
                    event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("moderation.prefix.title", "moderation.prefix.text", "", "normal", event)).queue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    new Handler().getMySQL().update("server", "Prefix", args[0], "ID", event.getGuild().getId());
                    event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.sucess", "moderation.prefix.changed", "", "sucess", event)).queue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.error","util.nopermissionuser", "", "error", event)).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}

package commands.moderation;

import commands.Command;
import commands.botowner.Handler;
import core.MySQL;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandLog implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) { return false;}

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getAuthor().getId() == event.getGuild().getOwner().getUser().getId() || event.getMember().hasPermission(Permission.MANAGE_SERVER) || Handler.get(event.getAuthor())) {

            try {
                if (args.length < 1) {
                    event.getTextChannel().sendMessage("ZU KURZ EY").queue();
                } else switch (args[0].toLowerCase()) {
                    case "disable":
                        MySQL.update("server", "logchannel", "none", "id", event.getGuild().getId());
                        event.getTextChannel().sendMessage("Succesfully disable Log").queue();
                        break;
                    case "enable":

                        String LogChannel = event.getMessage().getMentionedChannels().get(0).getId();
                        MySQL.update("server", "logchannel", LogChannel, "id", event.getGuild().getId());
                        event.getTextChannel().sendMessage("Succesfully enable Log").queue();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            event.getTextChannel().sendMessage("No permissions").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}

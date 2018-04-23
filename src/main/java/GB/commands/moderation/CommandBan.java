package GB.commands.moderation;

import GB.Handler;
import GB.MessageHandler;
import commands.Command;
import commands.botowner.Owner;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandBan implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getAuthor().getId() == event.getGuild().getOwner().getUser().getId() || event.getMember().hasPermission(Permission.BAN_MEMBERS) || Owner.get(event.getAuthor())) {
            Message msg = event.getMessage();

            if (msg.getMentionedUsers().size()== 0) {
                event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("moderation.ban.title", "moderation.ban.text", "", "normal", event)).queue();
            } else {
                Member User = msg.getGuild().getMember(msg.getMentionedUsers().get(0));
                if (!msg.getGuild().getSelfMember().canInteract(User)) {
                    event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.error", "util.nopermissionbot", "", "error", event)).queue();
                } else {
                    if (!User.getUser().isBot()) {
                        PrivateChannel channel = User.getUser().openPrivateChannel().complete();
                        channel.sendMessage(new Handler().getMessageHandler().getEmbed("moderation.ban.ban", "moderation.ban.user", event.getGuild().getName(), "sucess", event)).queue();
                    }
                    if (args.length > 0) {
                        String reason = event.getMessage().getContentRaw().replaceFirst(MessageHandler.getprefix(event.getGuild()), "").replaceFirst("ban", "").replaceFirst(args[0], "");
                        msg.getGuild().getController().ban(User, 1).reason(reason).queue();
                        event.getTextChannel().sendMessage("Banned").queue();
                    } else {
                        msg.getGuild().getController().ban(User, 1).queue();
                        event.getTextChannel().sendMessage("Banned").queue();
                    }

                }
            }
        } else {
            event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.error", "util.nopermissionuser", "", "error", event)).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}

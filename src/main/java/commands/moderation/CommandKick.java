package commands.moderation;

import commands.Command;
import commands.botowner.Owner;
import core.MessageHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class CommandKick implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getAuthor().getId() == event.getGuild().getOwner().getUser().getId() || event.getMember().hasPermission(Permission.KICK_MEMBERS) || Owner.get(event.getAuthor())) {
            Message msg = event.getMessage();
            if (msg.getMentionedUsers().isEmpty()) {
                event.getTextChannel().sendMessage(MessageHandler.getEmbed("moderation.kick.title", "moderation.kick.text", "", "normal", event)).queue();
            } else {
                Member User = msg.getGuild().getMember(msg.getMentionedUsers().get(0));
                if (!msg.getGuild().getSelfMember().canInteract(User)) {
                    event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.error", "util.nopermissionbot", "", "error", event)).queue();
                } else {
                    if (!User.getUser().isBot()) {
                        PrivateChannel channel = User.getUser().openPrivateChannel().complete();
                        channel.sendMessage(MessageHandler.getEmbed("moderation.kick.kick", "moderation.kick.user", event.getGuild().getName(), "sucess", event)).queue();
                    }
                    msg.getGuild().getController().kick(User).queue();
                    event.getTextChannel().sendMessage(MessageHandler.getEmbed("moderation.kick.kick", "moderation.kick.channel", User.getUser().getName(), "sucess", event)).queue();
                }
            }
        } else {
            event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.error", "util.nopermissionuser", "", "error", event)).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}

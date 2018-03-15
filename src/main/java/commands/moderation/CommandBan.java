package commands.moderation;

import android.graphics.Color;
import commands.Command;
import commands.botowner.Handler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class CommandBan implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getAuthor().getId() == event.getGuild().getOwner().getUser().getId() || event.getMember().hasPermission(Permission.BAN_MEMBERS) || Handler.get(event.getAuthor())) {
            Message msg = event.getMessage();
            if (msg.getMentionedUsers().isEmpty()) {
                event.getTextChannel().sendMessage(new EmbedBuilder().setDescription("gb.ban <@User>")
                        .setTitle("Usage").setColor(Color.MAGENTA).build()).queue();
            }
            Member target = msg.getGuild().getMember(msg.getMentionedUsers().get(0));
            if (!msg.getGuild().getSelfMember().canInteract(target)) {
                event.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Sorry I can't ban this User.")
                        .setTitle(":warning: No permissions").setColor(Color.MAGENTA).build()).queue();
            } else {
                if (!target.getUser().isBot()) {
                    PrivateChannel channel = target.getUser().openPrivateChannel().complete();
                    channel.sendMessage(new EmbedBuilder().setDescription("You got banned")
                            .setTitle(":white_check_mark: Banned").setColor(Color.MAGENTA).build()).queue();
                }
                msg.getGuild().getController().ban(target, 7).queue();
                event.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Succesfully banned")
                        .setTitle(":white_check_mark: Banned").setColor(Color.MAGENTA).build()).queue();
            }
        }

    }

        @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}

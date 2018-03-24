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

public class CommandBan implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getAuthor().getId() == event.getGuild().getOwner().getUser().getId() || event.getMember().hasPermission(Permission.BAN_MEMBERS) || Owner.get(event.getAuthor())) {
            Message msg = event.getMessage();
            if (msg.getMentionedUsers().isEmpty()) {
                event.getTextChannel().sendMessage(new EmbedBuilder().setDescription("gb.ban <@User>")
                        .setTitle(MessageHandler.get(event.getAuthor()).getString("bantitel1")).setColor(Color.MAGENTA).build()).queue();
            }
            Member User = msg.getGuild().getMember(msg.getMentionedUsers().get(0));
            if (!msg.getGuild().getSelfMember().canInteract(User)) {
                event.getTextChannel().sendMessage(new EmbedBuilder().setDescription(MessageHandler.get(event.getAuthor()).getString("bandescription1"))
                        .setTitle(MessageHandler.get(event.getAuthor()).getString("bantitel2")).setColor(Color.MAGENTA).build()).queue();
            } else {
                if (!User.getUser().isBot()) {
                    PrivateChannel channel = User.getUser().openPrivateChannel().complete();
                    channel.sendMessage(new EmbedBuilder().setDescription("You got banned")
                            .setTitle(":white_check_mark: Banned").setColor(Color.MAGENTA).build()).queue();
                }
                msg.getGuild().getController().ban(User, 1).queue();
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

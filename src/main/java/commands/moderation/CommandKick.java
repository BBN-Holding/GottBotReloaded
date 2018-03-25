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
                event.getTextChannel().sendMessage(new EmbedBuilder().setDescription(MessageHandler.getprefix(event.getGuild()) + "kick <@User>")
                        .setTitle(MessageHandler.get(event.getAuthor()).getString("kicktitel1")).setColor(Color.YELLOW).build()).queue();
            }
            Member User = msg.getGuild().getMember(msg.getMentionedUsers().get(0));
            if (!msg.getGuild().getSelfMember().canInteract(User)) {
                event.getTextChannel().sendMessage(new EmbedBuilder().setDescription(MessageHandler.get(event.getAuthor()).getString("kickdescription1"))
                        .setTitle(MessageHandler.get(event.getAuthor()).getString("kicktitel2")).setColor(Color.YELLOW).build()).queue();
            } else {
                if (!User.getUser().isBot()) {
                    PrivateChannel channel = User.getUser().openPrivateChannel().complete();
                    channel.sendMessage(new EmbedBuilder().setDescription(MessageHandler.get(event.getAuthor()).getString("kickdescription2") + " " + event.getMessage().getGuild().getName())
                            .setTitle(MessageHandler.get(event.getAuthor()).getString("kicktitel3")).setColor(Color.RED).build()).queue();
                }
                msg.getGuild().getController().kick(User).queue();
                event.getTextChannel().sendMessage(new EmbedBuilder().setDescription(User.getUser().getAsMention() + " " + MessageHandler.get(event.getAuthor()).getString("kickdescription3"))
                        .setTitle(MessageHandler.get(event.getAuthor()).getString("kicktitel4")).setColor(Color.GREEN).build()).queue();
            }
        } else {

            event.getTextChannel().sendMessage(new EmbedBuilder().setDescription(MessageHandler.get(event.getAuthor()).getString("kickdescription4")).setTitle(MessageHandler.get(event.getAuthor()).getString("kicktitel5")).build()).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}

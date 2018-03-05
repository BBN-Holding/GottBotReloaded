package commands.moderation;

import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
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
        Message msg = event.getMessage();
        if (msg.getMentionedUsers().isEmpty()) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setDescription("gb.kick <@User>")
                    .setTitle("Usage").setColor(Color.MAGENTA).build()).queue();
        }
        Member target = msg.getGuild().getMember(msg.getMentionedUsers().get(0));
        if (!msg.getGuild().getSelfMember().canInteract(target)) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Sorry I can't kick this User.")
                    .setTitle(":warning: No permissions").setColor(Color.MAGENTA).build()).queue();
        } else {
            if (!target.getUser().isBot()) {
                PrivateChannel channel = target.getUser().openPrivateChannel().complete();
                channel.sendMessage(new EmbedBuilder().setDescription("You got kicked")
                        .setTitle(":white_check_mark: Kicked").setColor(Color.MAGENTA).build()).queue();
            }
            msg.getGuild().getController().ban(target, 7).queue();
            event.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Succesfully kicked")
                    .setTitle(":white_check_mark: Kicked").setColor(Color.MAGENTA).build()).queue();
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

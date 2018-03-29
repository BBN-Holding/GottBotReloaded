package commands.music;

import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;
import net.dv8tion.jda.core.utils.PermissionUtil;

public class CommandJoin implements Command {


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        AudioManager manager = event.getGuild().getAudioManager();

        if (manager.isConnected()) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setDescription("The bot is already connected to a voice channel").build()).queue();
        } else if (manager.isAttemptingToConnect()) {
            event.getTextChannel().sendMessage("Bot wird gerade verbunden!").queue();
        } else if (!event.getMember().getVoiceState().inVoiceChannel()) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setDescription("You are not in a voice channel").build()).queue();
        } else if (event.getMember().getVoiceState().getChannel().getUserLimit() == event.getMember().getVoiceState().getChannel().getMembers().size()) {
            if (!event.getGuild().getSelfMember().hasPermission(Permission.MANAGE_CHANNEL)) {
                event.getTextChannel().sendMessage("The chnnel is already full").queue();
            }
        } else {
            VoiceChannel channel = event.getMember().getVoiceState().getChannel();
            if (event.getMember().getVoiceState().getChannel().getUserLimit() == event.getMember().getVoiceState().getChannel().getMembers().size()) {
                event.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Cannot play in a full channel").build()).queue();
                return;
            }
            if (PermissionUtil.checkPermission(channel, event.getGuild().getSelfMember(), Permission.VOICE_CONNECT)) {
                manager.openAudioConnection(channel);
                manager.setSelfDeafened(true);
                event.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Channel " + channel.getName() + " joined").build()).queue();
            } else {
                event.getTextChannel().sendMessage(new EmbedBuilder().addField("Missing permission", Permission.VOICE_CONNECT.getName(), false).build()).queue();
            }
        }
    }


    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}

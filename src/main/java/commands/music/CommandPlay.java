package commands.music;

import commands.Command;
import util.Constants;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.utils.PermissionUtil;

public class CommandPlay implements Command {


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getMember().getVoiceState() != null && event.getMember().getVoiceState().inVoiceChannel()) {
            VoiceChannel channel = event.getMember().getVoiceState().getChannel();
            if (!event.getMember().getVoiceState().getChannel().getMembers().contains(event.getGuild().getSelfMember()) && event.getGuild().getAudioManager().getConnectedChannel() != null) {
                event.getTextChannel().sendMessage(new EmbedBuilder().setDescription("You are not in my voice channel").build()).queue();
                return;
            }

            if (event.getMember().getVoiceState().getChannel().getUserLimit() == event.getMember().getVoiceState().getChannel().getMembers().size()) {
                event.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Cannot play in a full channel").build()).queue();
                return;
            }
            if (event.getGuild().getAudioManager().getConnectedChannel() == null) {
                if (PermissionUtil.checkPermission(channel, event.getGuild().getSelfMember(), Permission.VOICE_CONNECT)) {
                    event.getGuild().getAudioManager().openAudioConnection(event.getMember().getVoiceState().getChannel());
                } else {
                    event.getTextChannel().sendMessage(new EmbedBuilder().addField("Missing permission", Permission.VOICE_CONNECT.getName(), false).build()).queue();
                    return;
                }
            }
            if (args.length < 1) {
                event.getTextChannel().sendMessage(new EmbedBuilder().setDescription("!play <URL|Titel").build()).queue();
            } else {
                new MusicManager();
                if (Constants.extractUrls(args[0]).isEmpty()) {
                    MusicManager.loadTrack(event.getTextChannel(), event.getMember().getUser(), "ytsearch:" + String.join(" ", args));
                } else {
                    MusicManager.loadTrack(event.getTextChannel(), event.getMember().getUser(), args[0]);


                }
            }
        } else {
            event.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Your are not in a voicechannel").build()).queue();
        }

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
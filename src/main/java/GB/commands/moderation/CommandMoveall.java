package GB.commands.moderation;

import GB.Handler.CommandHandling.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandMoveall implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        // TODO: Testing
        if (args.length==0) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("moveall [Start: Channel ID | Channel Name | @User in this Channel] [Target: Channel ID | Channel Name | @User in this Channel]").build()).queue();
        } else {
            VoiceChannel channel=null;
            if (event.getMessage().getMentionedMembers().size()==1){
                if (event.getMessage().getMentionedMembers().get(0).getVoiceState().inVoiceChannel()) {
                    channel=event.getMessage().getMentionedMembers().get(0).getVoiceState().getChannel();
                }
            } else if (event.getGuild().getVoiceChannelsByName(args[0], true).size()!=0) {
                channel=event.getGuild().getVoiceChannelsByName(args[0], true).get(0);
            } else if (event.getGuild().getVoiceChannelById(args[0])!=null) {
                channel=event.getGuild().getVoiceChannelById(args[0]);
            }
            VoiceChannel channel2=null;
            if (event.getMessage().getMentionedMembers().size()==1){
                if (event.getMessage().getMentionedMembers().get(0).getVoiceState().inVoiceChannel()) {
                    channel2=event.getMessage().getMentionedMembers().get(0).getVoiceState().getChannel();
                }
            } else if (event.getGuild().getVoiceChannelsByName(args[0], true).size()!=0) {
                channel2=event.getGuild().getVoiceChannelsByName(args[0], true).get(0);
            } else if (event.getGuild().getVoiceChannelById(args[0])!=null) {
                channel2=event.getGuild().getVoiceChannelById(args[0]);
            }
            while (channel.getMembers().size()>0) {
                event.getGuild().getController().moveVoiceMember(channel.getMembers().get(0), channel2).queue();
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}

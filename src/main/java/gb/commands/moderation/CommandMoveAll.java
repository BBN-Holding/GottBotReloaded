package gb.commands.moderation;

import gb.GottBot;
import gb.Handler.CommandHandling.Command;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandMoveAll implements Command {
    @Override
    public String[] Aliases() {
        return new String[]{"moveall", "ma"};
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<1) {
            event.getTextChannel().sendMessage(GottBot.getMessage().getCommandTemplate(Aliases(), "gb.moveall [VoiceChannelname]", "Move all member in your VoiceChannel in another VoiceChannel")).queue();
        } else {
            if (event.getGuild().getVoiceChannelsByName(args[0], true).size()>0) {
                VoiceChannel voiceChannel=event.getGuild().getVoiceChannelsByName(args[0], true).get(0);
                for (Member member:event.getMember().getVoiceState().getChannel().getMembers()) {
                    event.getGuild().getController().moveVoiceMember(member, voiceChannel).queue();
                }
            }
        }
    }
}

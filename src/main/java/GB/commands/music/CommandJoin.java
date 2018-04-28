package GB.commands.music;

import GB.commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.PermissionException;

public class CommandJoin implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            if (!event.getGuild().getAudioManager().isConnected() && !event.getGuild().getAudioManager().isAttemptingToConnect()) {
                if (event.getGuild().getAudioManager().getGuild().getVoiceChannels().contains(event.getMember().getVoiceState().getChannel())) {
                    event.getGuild().getAudioManager().openAudioConnection(event.getMember().getVoiceState().getChannel());
                } else {
                    event.getTextChannel().sendMessage("unknown error (eigentlich nicht)").queue();
                }
            } else {
                event.getTextChannel().sendMessage("I am already connected!").queue();
            }
        } catch (PermissionException ignored) {

        }
        event.getGuild().getAudioManager().setSelfDeafened(true);
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}

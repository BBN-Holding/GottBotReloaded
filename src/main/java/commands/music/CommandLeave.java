package commands.music;

import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandLeave implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getGuild().getAudioManager().isConnected() || event.getGuild().getAudioManager().isAttemptingToConnect()) {
            event.getGuild().getAudioManager().closeAudioConnection();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}

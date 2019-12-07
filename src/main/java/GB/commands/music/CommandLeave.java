package GB.commands.music;

import GB.commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandLeave implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getGuild().getAudioManager().isConnected() || event.getGuild().getAudioManager().isAttemptingToConnect()) {
            event.getGuild().getAudioManager().closeAudioConnection();
        } else {
            event.getTextChannel().sendMessage("I cant leave when i am not in a voice channel").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}

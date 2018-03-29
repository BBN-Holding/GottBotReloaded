package commands.music;

import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

public class CommandLeave implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        AudioManager manager = event.getGuild().getAudioManager();
        if (!manager.isConnected()) {
            event.getTextChannel().sendMessage("Bot ist not connected to a voice channel").queue();
        } else if (!event.getMember().getVoiceState().getChannel().equals(manager.getConnectedChannel())) {
            event.getTextChannel().sendMessage("You must be in the same voice channel").queue();
        } else {
            manager.closeAudioConnection();
            event.getTextChannel().sendMessage("Succesfully closed voice connection").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}

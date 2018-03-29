package commands.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class GuildMusicManager {

    public final AudioPlayer player;
    public final TrackScheduler scheduler;
    private final SendAudioHandler sendHandler;

    /**
     * Constructs a new GuildMusicManager.
     *
     * @param manager Audio player manager to use for creating the player.
     */
    public GuildMusicManager(AudioPlayerManager manager) {
        player = manager.createPlayer();
        scheduler = new TrackScheduler(player);
        sendHandler = new SendAudioHandler(player);
        player.addListener(scheduler);
    }

    public SendAudioHandler getSendHandler() {
        return sendHandler;
    }

}

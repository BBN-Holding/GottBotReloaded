package commands.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.core.audio.AudioSendHandler;

public class SendAudioHandler implements AudioSendHandler {
    private final AudioPlayer audioPlayer;
    private AudioFrame Frame;

    SendAudioHandler(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public boolean canProvide() {
        if (Frame == null) {
            Frame = audioPlayer.provide();
        }
        return Frame != null;
    }

    @Override
    public byte[] provide20MsAudio() {
        if (Frame == null) {
            Frame = audioPlayer.provide();
        }
        byte[] daten = Frame != null ? Frame.data : null;
        Frame = null;
        return daten;
    }

    @Override
    public boolean isOpus() {
        return true;
    }
}

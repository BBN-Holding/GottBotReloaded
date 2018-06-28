package gb.core;

import gb.GottBot;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameAnimator {
    public static ArrayList<String> games;
    public static boolean On=true;
    public static void start() {
        // games.add("online:playing:Testing new Features:https://twitch.tv/bigbotnetwork");
        new Thread(() -> {
            if (On) {
                // keine ahnung warum int[] frag java :D
                final int[] i = {0};
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (games.size() > i[0]) {
                            String[] strings = games.get(i[0]).split(":");
                            GottBot.getShardManager().getShards().get(0).getPresence().setPresence(OnlineStatus.valueOf(strings[0]), Game.of(Game.GameType.valueOf(strings[1]), strings[2]));
                            i[0]++;
                        }
                    }
                }, 30000, 30000);
            }
        }).start();
    }
}

package util;

import core.Main;
import net.dv8tion.jda.core.entities.Game;

public class GameAnimator {

    private static Thread timer;
    private static boolean running = false;
    private static int currentGame = 0;

    private static final String[] gameAnimations = {
            "",
            "rubicon.fun"

    };

    public static synchronized void start() {
        if (!running) {
            timer = new Thread(() -> {
                long last = 0;
                while (running) {
                    if (System.currentTimeMillis() >= last + 30000) {
                            String playStat = "f";
                            if (!playStat.equals("0") && !playStat.equals("")) {
                                Main.jda.getPresence().setGame(Game.playing(playStat));
                                last = System.currentTimeMillis();
                            } else {
                                Main.jda.getPresence().setGame(Game.streaming("@GottBot | " + gameAnimations[currentGame], "https://twitch.tv/bigbotnetwork"));

                                if (currentGame == gameAnimations.length - 1)
                                    currentGame = 0;
                                else
                                    currentGame += 1;
                                last = System.currentTimeMillis();
                            }
                    }
                }
            });
            timer.setName("GameAnimator");
            running = true;
            timer.start();
        }
    }

    public static synchronized void stop() {
        if (running) {
            try {
                running = false;
                timer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
package commands.fun;

import commands.Command;
import core.Constants;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Timer;
import java.util.TimerTask;


public class countdown implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length != 1) {
            return;
        }
        int i = 10;


        try {

            i = Constants.clamp(1, 60, Integer.valueOf(args[0]));
        } catch (Exception e) {
            event.getMessage().getTextChannel().sendMessage(args[0] + " isn't a valid number. Using 10");
        }
        int f = i;
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            int j = f;

            public void run() {
                j--;
                event.getMessage().getTextChannel().sendMessage(j + "").queue();
                if (j < 1) {
                    t.cancel();
                }
            }
        }, 1000, 1000);
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}

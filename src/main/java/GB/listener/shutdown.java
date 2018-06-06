package GB.listener;

import GB.GottBot;
import net.dv8tion.jda.core.events.ShutdownEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class shutdown extends ListenerAdapter {
    @Override
    public void onShutdown(ShutdownEvent event) {
        if (!GottBot.getDev()) {
            GottBot.sendToServer().println("Disconnected! Add Shard " + event.getJDA().getShardInfo().getShardId());
            GottBot.sendToServer().flush();
        }
        System.out.println("GottBot stopped... Exiting...");
        System.exit(0);
    }
}

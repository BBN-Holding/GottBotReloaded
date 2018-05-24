package GB.Pluginmanager;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface Plugin {
    public String registercommandname();
    public void onCommand(String[] args, MessageReceivedEvent event);
}

package de.GottBot.commands.Owner;

import de.GottBot.commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * @author GregTCLTK
 * @time 01:27 01.08.2018
 * @project GottBotReloaded
 * @package de.GottBot.commands.Owner
 * @class CommandShutdown
 **/

public class CommandShutdown implements Command {

    @Override
    public void executed(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendMessage("Shutdown...");
        event.getJDA().shutdown();
        System.exit(69);
    }

}

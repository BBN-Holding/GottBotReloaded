package de.GottBot.listener;

import de.GottBot.core.CommandHandler;
import de.GottBot.core.CommandParser;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


/**
 * @author GregTCLTK
 * @time 00:53 01.08.2018
 * @project GottBotReloaded
 * @package de.de.GottBot.GottBot.listener
 * @class CommandListener
 **/

public class CommandListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getMessage().getContentRaw().startsWith("gb.")) {
            CommandHandler.handleCommand(CommandParser.parser(event.getMessage().getContentRaw(), event));
        }
    }
}

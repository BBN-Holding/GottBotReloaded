package de.GottBot.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * @author GregTCLTK
 * @time 01:11 01.08.2018
 * @project GottBotReloaded
 * @package de.GottBot.commands
 * @class Command
 **/

public interface Command {
    void executed(String[] args, MessageReceivedEvent event);
}
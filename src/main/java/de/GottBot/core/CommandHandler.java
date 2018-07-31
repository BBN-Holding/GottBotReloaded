package de.GottBot.core;

import de.GottBot.commands.Command;

import java.util.HashMap;

/**
 * @author GregTCLTK
 * @time 01:18 01.08.2018
 * @project GottBotReloaded
 * @package de.GottBot.core
 * @class CommandHandler
 **/

public class CommandHandler {


    public static HashMap<String, Command> commands = new HashMap<>();

    public static void handleCommand(CommandParser.commandContainer cmd) {

        if(commands.containsKey(cmd.invoke)) {

                commands.get(cmd.invoke).executed(cmd.args, cmd.event);

        }

    }
}

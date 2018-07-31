package de.GottBot;

import de.GottBot.commands.Owner.CommandShutdown;
import de.GottBot.core.CommandHandler;
import de.GottBot.listener.CommandListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.utils.SessionController;


import javax.security.auth.login.LoginException;


/**
 * @author GregTCLTK
 * @time 00:38 01.08.2018
 * @project GottBotReloaded
 * @class de.GottBot.GottBot
 **/

public class GottBot {
    private static JDABuilder builder = new JDABuilder(AccountType.BOT);
    public static SessionController sessionController;

    public static void main(String[] args) {

        builder.setToken("");

        CommandHandler.commands.put("stop", new CommandShutdown());
        CommandHandler.commands.put("shutdown", new CommandShutdown());

        builder.setSessionController(sessionController);
        builder.addEventListener(new CommandListener());
        try {
            builder.buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

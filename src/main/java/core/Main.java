package core;

import commands.botowner.*;
import commands.fun.CommandCountdown;
import commands.usercommands.*;
import listener.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stuff.SECRETS;

public class Main {
    public static JDABuilder builder;
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("------------------start Bot----------------------");
        logger.info("read Token and logins");
        MySQL.connect();
        builder = new JDABuilder(AccountType.BOT).setToken(SECRETS.TOKEN).setAutoReconnect(true).setStatus(OnlineStatus.ONLINE);
        builder.addEventListener(new commandListener());
        builder.addEventListener(new Guildjoin());
        builder.addEventListener(new Message());
        builder.addEventListener(new Memberjoin());
        logger.info("loaded all listeners");
        commandHandler.commands.put("CommandLanguage", new CommandLanguage());
        commandHandler.commands.put("CommandTest", new CommandTest());
        commandHandler.commands.put("CommandPrefix", new CommandPrefix());
        commandHandler.commands.put("CommandHelp", new CommandHelp());
        commandHandler.commands.put("CommandBug", new CommandBug());
        commandHandler.commands.put("CommandProfile", new CommandProfile());
        commandHandler.commands.put("clan", new CommandClan());
        commandHandler.commands.put("CommandGiveHashes", new CommandGiveHashes());
        commandHandler.commands.put("CommandGuilds", new CommandGuilds());
        commandHandler.commands.put("CommandResgisterUser", new CommandResgisterUser());
        commandHandler.commands.put("invite", new CommandInvite());
        commandHandler.commands.put("CommandClyde", new CommandClyde());
        commandHandler.commands.put("CommandEval", new CommandEval());
        commandHandler.commands.put("CommandCountdown", new CommandCountdown());
        logger.info("loaded all commands");
        try {
            JDA jda = builder.buildBlocking();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

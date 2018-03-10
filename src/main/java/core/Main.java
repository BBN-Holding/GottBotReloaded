package core;

import commands.botowner.*;
import commands.fun.*;
import commands.moderation.*;
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
        commandHandler.commands.put("language", new CommandLanguage());
        commandHandler.commands.put("test", new CommandTest());
        commandHandler.commands.put("prefix", new CommandPrefix());
        commandHandler.commands.put("help", new CommandHelp());
        commandHandler.commands.put("bug", new CommandBug());
        commandHandler.commands.put("profile", new CommandProfile());
        commandHandler.commands.put("clan", new CommandClan());
        commandHandler.commands.put("givehashes", new CommandGiveHashes());
        commandHandler.commands.put("guilds", new CommandGuilds());
        commandHandler.commands.put("registeruser", new CommandResgisterUser());
        commandHandler.commands.put("invite", new CommandInvite());
        commandHandler.commands.put("clyde", new CommandClyde());
        commandHandler.commands.put("eval", new CommandEval());
        commandHandler.commands.put("countdown", new CommandCountdown());
        commandHandler.commands.put("ban", new CommandBan());
        commandHandler.commands.put("kick", new CommandKick());
        commandHandler.commands.put("question", new CommandQuestion());
        logger.info("loaded all commands");
        try {
            JDA jda = builder.buildBlocking();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

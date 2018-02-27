package core;

import commands.*;
import commands.botowner.givehashes;
import commands.botowner.test;
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
        commandHandler.commands.put("language", new language());
        commandHandler.commands.put("test", new test());
        commandHandler.commands.put("prefix", new prefix());
        commandHandler.commands.put("help", new help());
        commandHandler.commands.put("bug", new bug());
        commandHandler.commands.put("profile", new profile());
        commandHandler.commands.put("clan", new Clan());
        commandHandler.commands.put("givehashes", new givehashes());
        commandHandler.commands.put("guilds", new guilds());
        logger.info("loaded all commands");
        try {
            JDA jda = builder.buildBlocking();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

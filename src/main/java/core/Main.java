package core;

import commands.*;
import listener.Guildjoin;
import listener.Message;
import listener.commandListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stuff.SECRETS;

import javax.security.auth.login.LoginException;

public class Main {
    public static JDABuilder builder;
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        builder = new JDABuilder(AccountType.BOT).setToken(SECRETS.TOKEN).setAutoReconnect(true).setStatus(OnlineStatus.ONLINE);
        builder.addEventListener(new commandListener());
        builder.addEventListener(new Guildjoin());
        builder.addEventListener(new Message());
        logger.info("loaded all listeners");
        commandHandler.commands.put("language", new language());
        commandHandler.commands.put("test", new test());
        commandHandler.commands.put("prefix", new prefix());
        commandHandler.commands.put("help", new help());
        commandHandler.commands.put("bug", new bug());
        commandHandler.commands.put("profile", new profile());
        logger.info("loaded all commands");
        try {
            JDA jda = builder.buildBlocking();
            logger.info("starting Bot");
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

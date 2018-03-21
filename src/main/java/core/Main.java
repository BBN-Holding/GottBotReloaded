package core;

import commands.botowner.*;
import commands.games.CommandWork;
import commands.moderation.*;
import commands.tools.CommandFAQ;
import commands.tools.CommandGitHub;
import commands.tools.CommandPing;
import commands.tools.CommandProfile;
import commands.usercommands.*;
import listener.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stuff.SECRETS;

import javax.security.auth.login.LoginException;

public class Main {
    public static JDABuilder builder;
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("------------------start Bot----------------------");
        logger.info("read Token and logins");
        MySQL.connect();
        builder = new JDABuilder(AccountType.BOT).setToken(SECRETS.TOKEN).setAutoReconnect(true).setStatus(OnlineStatus.ONLINE).setGame(Game.streaming( "@GottBot", "https://www.twitch.tv/bigbotnetwork"));
        builder.addEventListener(new commandListener());
        builder.addEventListener(new Guildjoin());
        builder.addEventListener(new Message());
        builder.addEventListener(new Memberjoin());
        builder.addEventListener(new Reaction());
        builder.addEventListener(new Guildleave());
        // builder.addEventListener(new BotList());
        logger.info("loaded all listeners");
        commandHandler.commands.put("language", new CommandLanguage());
        commandHandler.commands.put("test", new CommandTest());
        commandHandler.commands.put("prefix", new CommandPrefix());
        commandHandler.commands.put("help", new CommandHelp());
        commandHandler.commands.put("bug", new CommandBug());
        commandHandler.commands.put("profile", new CommandProfile());
        commandHandler.commands.put("givehashes", new CommandGiveHashes());
        commandHandler.commands.put("registeruser", new CommandRegisterUser());
        commandHandler.commands.put("registerserver", new CommandRegisterServer());
        commandHandler.commands.put("invite", new CommandInvite());
        commandHandler.commands.put("eval", new CommandEval());
        commandHandler.commands.put("ban", new CommandBan());
        commandHandler.commands.put("kick", new CommandKick());
        commandHandler.commands.put("github", new CommandGitHub());
        commandHandler.commands.put("stop", new CommandStop());
        commandHandler.commands.put("setlvl", new CommandSetLevel());
        commandHandler.commands.put("setxp", new CommandSetXP());
        commandHandler.commands.put("work", new CommandWork());
        commandHandler.commands.put("clyde", new CommandClyde());
        commandHandler.commands.put("ping", new CommandPing());
        commandHandler.commands.put("leave", new CommandLeave());
        commandHandler.commands.put("stats", new CommandStats());
        commandHandler.commands.put("verification", new CommandVerification());
        commandHandler.commands.put("say", new CommandSay());
        commandHandler.commands.put("blacklist", new CommandBlacklist());
        commandHandler.commands.put("guilds", new CommandGuilds());
        commandHandler.commands.put("lvlmessage", new CommandLevelMessage());
        commandHandler.commands.put("faq", new CommandFAQ());
        logger.info("loaded all commands");
        try {
            JDA jda = builder.buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}

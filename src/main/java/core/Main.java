package core;

import commands.botowner.*;
import commands.moderation.*;
import commands.tools.*;
import commands.usercommands.*;
import commands.usercommands.CommandHelp;
import commands.botowner.CommandInfo;
import commands.usercommands.CommandPremium;
import listener.*;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.utils.SessionController;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stuff.SECRETS;

import java.io.File;
import java.io.FileInputStream;

public class Main {
    public static DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    public static JDA jda;
    public static String[] args;
    public static boolean dev = true;
    public static SessionController sessionController;
    public static ShardManager shardManager;

    public static void main(String[] args2) {
        try {
            logger.info("------------------start Bot----------------------");
            if (!new File("Gott.log").exists()) {
                new File("Gott.log").createNewFile();
                logger.info("created File Gott.log");
            }
            if (!dev) {

                FTPClient client = new FTPClient();
                FileInputStream fis = null;
                client.connect("ftp.bigbotnetwork.de");
                client.login(SECRETS.FTPUSER, SECRETS.FTPPW);
                String filename = "Gott.log";
                fis = new FileInputStream(filename);
                client.storeFile(filename, fis);
                client.logout();
                builder.addEventListeners(new BotList());
                builder.setShardsTotal(2);
            } else {
                builder.setShardsTotal(1);
                logger.info("Dev Mode activated - Don't load Botlist listener - Don't upload the Log file");
                builder.setAutoReconnect(true)
                        .setGame(Game.playing("Hax is a coooooollll boi"));
            }
            logger.info("read Token and logins");
            MySQL.connect();
            builder.setToken(SECRETS.TOKEN);
            builder.addEventListeners(
                new commandListener(),
                new Guildjoin(),
                new Message(),
                new Memberjoin(),
                new Reaction(),
                new PrivateMessage()
             );
            logger.info("loaded all listeners");
            commandHandler.commands.put("language", new CommandLanguage());
            commandHandler.commands.put("test", new CommandTest());
            commandHandler.commands.put("prefix", new CommandPrefix());
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
            commandHandler.commands.put("clyde", new CommandClyde());
            commandHandler.commands.put("ping", new CommandPing());
            commandHandler.commands.put("leave", new CommandLeave());
            commandHandler.commands.put("stats", new CommandStats());
            commandHandler.commands.put("verification", new CommandVerification());
            commandHandler.commands.put("say", new CommandSay());
            commandHandler.commands.put("blacklist", new CommandBlacklist());
            commandHandler.commands.put("guilds", new CommandGuilds());
            commandHandler.commands.put("lvlmessage", new CommandLevelMessage());
            commandHandler.commands.put("guild", new CommandGuild());
            commandHandler.commands.put("help", new CommandHelp());
            commandHandler.commands.put("info", new CommandInfo());
            commandHandler.commands.put("warn", new CommandWarn());
            commandHandler.commands.put("token", new CommandToken());
            commandHandler.commands.put("log", new CommandLog());
            commandHandler.commands.put("play", new CommandPlay());
            commandHandler.commands.put("dm", new CommandDM());
            commandHandler.commands.put("miner", new CommandMiner());
            commandHandler.commands.put("premium", new CommandPremium());
            commandHandler.commands.put("setpremium", new CommandSetPremium());
            commandHandler.commands.put("clear", new CommandClear());
            commandHandler.commands.put("s", new CommandShard());
            commandHandler.commands.put("shard", new CommandShard());
            commandHandler.commands.put("uptime", new CommandUptime());
            args = args2;
            logger.info("loaded all commands");
            logger.info("Starting the Bot...");
            builder.setSessionController(sessionController);
            shardManager = builder.build();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
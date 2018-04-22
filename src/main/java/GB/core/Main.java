package GB.core;

import GB.Handler;
import GB.commands.botowner.CommandGiveHashes;
import GB.commands.botowner.CommandSetLevel;
import GB.commands.botowner.CommandTest;
import GB.commands.moderation.CommandPrefix;
import GB.commands.tools.CommandProfile;
import GB.commands.usercommands.*;
import GB.listener.*;
import GB.stuff.SECRETS;
import commands.music.CommandJoin;
import commands.botowner.CommandInfo;
import listener.BotList;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.utils.SessionController;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;

import static GB.core.commandHandler.commands;

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
            //if (!new File("Gott.log").exists()) { new File("Gott.log").createNewFile();logger.info("created File Gott.log"); }
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
                builder.setAutoReconnect(true);
            }
            logger.info("read Token and logins");
            new Handler().getMySQL().connect();
            builder.setToken(SECRETS.TOKEN);
            builder.addEventListeners(
                new commandListener(),
                new listener.Guildjoin(),
                new Message(),
                new Memberjoin(),
                new Reaction(),
                new listener.PrivateMessage(),
                    new Channel()
             );
            logger.info("loaded all listeners");
            commands.put("language", new CommandLanguage());
            commands.put("test", new CommandTest());
            commands.put("prefix", new CommandPrefix());
            commands.put("bug", new CommandBug());
            commands.put("profile", new CommandProfile());
            commands.put("givehashes", new CommandGiveHashes());
            commands.put("registeruser", new commands.botowner.CommandRegisterUser());
            commands.put("registerserver", new commands.botowner.CommandRegisterServer());
            commands.put("invite", new commands.botowner.CommandInvite());
            commands.put("eval", new commands.botowner.CommandEval());
            commands.put("ban", new commands.moderation.CommandBan());
            commands.put("kick", new commands.moderation.CommandKick());
            commands.put("github", new commands.tools.CommandGitHub());
            commands.put("stop", new commands.botowner.CommandStop());
            commands.put("setlvl", new CommandSetLevel());
            commands.put("setxp", new commands.botowner.CommandSetXP());
            commands.put("clyde", new commands.botowner.CommandClyde());
            commands.put("ping", new commands.tools.CommandPing());
            commands.put("guildleave", new commands.botowner.CommandLeave());
            commands.put("stats", new commands.usercommands.CommandStats());
            commands.put("verification", new commands.moderation.CommandVerification());
            commands.put("say", new commands.usercommands.CommandSay());
            commands.put("blacklist", new commands.botowner.CommandBlacklist());
            commands.put("guilds", new commands.botowner.CommandGuilds());
            commands.put("levelmessage", new commands.usercommands.CommandLevelMessage());
            commands.put("guild", new commands.botowner.CommandGuild());
            commands.put("help", new CommandHelp());
            commands.put("info", new CommandInfo());
            commands.put("warn", new commands.moderation.CommandWarn());
            commands.put("token", new commands.tools.CommandToken());
            commands.put("log", new commands.moderation.CommandLog());
            commands.put("play", new commands.botowner.CommandPlay());
            commands.put("dm", new commands.botowner.CommandDM());
            commands.put("miner", new CommandMiner());
            commands.put("premium", new CommandPremium());
            commands.put("setpremium", new commands.botowner.CommandSetPremium());
            commands.put("clear", new commands.moderation.CommandClear());
            commands.put("s", new commands.botowner.CommandShard());
            commands.put("shard", new commands.botowner.CommandShard());
            commands.put("uptime", new commands.tools.CommandUptime());
            commands.put("role", new commands.moderation.CommandRole());
            commands.put("botinfo", new commands.tools.CommandBotInfo());
            commands.put("privatechannel", new commands.tools.CommandPrivatechannel());
            commands.put("clan", new CommandClan());
            /*MUSIC*/
            commands.put("join", new CommandJoin());
            commands.put("leave", new commands.music.CommandLeave());
            args = args2;
            logger.info("loaded all commands");
            logger.info("Starting the Bot...");
            builder.setSessionController(sessionController);
            shardManager = builder.build();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Jojo");
        }


    }

}
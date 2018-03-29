package core;

import commands.botowner.*;
import commands.games.CommandWork;
import commands.moderation.*;
import commands.music.CommandJoin;
import commands.music.CommandLeave;
import commands.music.CommandPause;
import commands.tools.CommandGitHub;
import commands.tools.CommandPing;
import commands.tools.CommandProfile;
import commands.tools.CommandToken;
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

import java.io.File;

public class Main {
    public static JDABuilder builder;
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    public static JDA jda;
    public static String[] args;
    static boolean dev=false;

    public static void main(String[] args2) {
        try {
            if (!new File("Gott.log").exists()) {
                new File("Gott.log").createNewFile();
            }
            logger.info("------------------start Bot----------------------");
            logger.info("read Token and logins");
            MySQL.connect();
            builder = new JDABuilder(AccountType.BOT).setToken(SECRETS.TOKEN).setAutoReconnect(true).useSharding(jda.getShardInfo().getShardId(), 1);
            builder.setGame(Game.playing("Starting...."));
            builder.setStatus(OnlineStatus.DO_NOT_DISTURB);

            builder.addEventListener(new commandListener());
            builder.addEventListener(new Guildjoin());
            builder.addEventListener(new Message());
            builder.addEventListener(new Memberjoin());
            builder.addEventListener(new Reaction());
            builder.addEventListener(new BotList());
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
            commandHandler.commands.put("guildleave", new CommandGuildLeave());
            commandHandler.commands.put("stats", new CommandStats());
            commandHandler.commands.put("verification", new CommandVerification());
            commandHandler.commands.put("say", new CommandSay());
            commandHandler.commands.put("blacklist", new CommandBlacklist());
            commandHandler.commands.put("guilds", new CommandGuilds());
            commandHandler.commands.put("lvlmessage", new CommandLevelMessage());
            commandHandler.commands.put("guild", new CommandGuild());
            commandHandler.commands.put("help2", new CommandHelp2());
            commandHandler.commands.put("help3", new CommandHelp3());
            commandHandler.commands.put("info", new CommandInfo());
            commandHandler.commands.put("restart", new CommandRestart());
            commandHandler.commands.put("warn", new CommandWarn());
            commandHandler.commands.put("token", new CommandToken());
            commandHandler.commands.put("log", new CommandLog());
            commandHandler.commands.put("play", new CommandPlay());
            commandHandler.commands.put("dm", new CommandDM());
            commandHandler.commands.put("role", new CommandRole());

            // MUSIC
            commandHandler.commands.put("join", new CommandJoin());
            commandHandler.commands.put("leave", new CommandLeave());
            commandHandler.commands.put("pause", new CommandPause());
            args = args2;
            logger.info("loaded all commands");
            if (!dev) {
                logger.error("dev Mode - don't load the botlist stats");
                builder.removeEventListener(new BotList());
            }
            jda = builder.buildBlocking();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
package GB;

import GB.Handler.CommandHandling.commandHandler;
import GB.Handler.CommandHandling.commandListener;
import GB.Handler.DB;
import GB.Handler.Info;
import GB.Handler.Logger;
import GB.Pluginmanager.Plugin;
import GB.Pluginmanager.PluginLoader;
import GB.listener.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import net.dv8tion.jda.bot.sharding.*;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.utils.SessionController;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.util.Collection;

import GB.Handler.Config;

public class GottBot {
    private static DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
    public static ShardManager shardManager;
    private static SessionController sessionController;
    private static Config config;
    private static Collection<Integer> shards;
    private static boolean streaming=true;


    public static void main(String[] args) {
        Startall();
    }

    private static void Startall() {
        read();
        DB.Connect();
        startBot();
        getLogger().setup();
    }

    private static void startBot() {
            registerListener();
            registerCommands();
            builder.setShardsTotal(Integer.parseInt(getConfig().getShards()));
            builder.setToken(getConfig().getToken());
            builder.setSessionController(sessionController);
            builder.setAutoReconnect(true);
            builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
            builder.setGame(Game.playing("Starting"));
            try {
                shardManager = builder.build();
            } catch (LoginException e) {
                e.printStackTrace();
            }
    }


    private static void read() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            config=mapper.readValue(new File("config.yml"), Config.class);
            String yml = ReflectionToStringBuilder.toString(config, ToStringStyle.MULTI_LINE_STYLE);
            if (!streaming)
                System.out.println(yml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void registerListener() {
        builder.addEventListeners(
          new commandListener(),
                new Message()
        );
    }

    private static void registerCommands() {
        try {
            Class<?>[] classes=PluginLoader.loadDirectory(new File("plugins"));
            Plugin[] plugins=PluginLoader.initAsPlugin(classes);
            for (int i=0; i<plugins.length; i++) {
                commandHandler.commands.put(plugins[i].registercommandname(), plugins[i]);
                System.out.println(plugins[i].registercommandname()+ " enabled!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Config getConfig() {
        return config;
    }

    public static DB getDB() {
        return new DB();
    }

    public static Info getInfo() {
        return new Info();
    }

    public static Logger getLogger() {
        return new Logger();
    }

}

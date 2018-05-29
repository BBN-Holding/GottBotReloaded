package GB;

import GB.Handler.CommandHandling.commandHandler;
import GB.Handler.CommandHandling.ListenerCommand;
import GB.Handler.DB;
import GB.Handler.Info;
import GB.Handler.Logger;
import GB.Pluginmanager.Plugin;
import GB.Pluginmanager.PluginLoader;
import GB.Pluginmanager.eventlistener;
import GB.listener.BotLists;
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
import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;

import GB.Handler.Config;

public class GottBot {
    private static DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
    public static ShardManager shardManager;
    private static SessionController sessionController;
    private static Config config;
    private static boolean streaming=true;
    private static String MaxShards;
    private static String Shard;
    public static void main(String[] args) {
        Startall();
    }

    private static void Startall() {
        read();
        DB.Connect();
        getLogger().setup();
        ServerConnect();
    }

    private static void ServerConnect() {
        new Thread(() -> {
            try {
                Socket socket = new Socket(getConfig().getServerIP(), Integer.parseInt(getConfig().getServerPort()));
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                printWriter.println("Password: "+getConfig().getServerPassword());
                printWriter.flush();
                printWriter.println("Connected! I need a Shard!");
                printWriter.flush();
                System.out.println("Connected to Server");
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains("Start with Shard ")) {
                        String[] temp = line.replace("Start with Shard ", "").split(" Max Shards: ");
                        Shard = temp[0];
                        MaxShards = temp[1];
                        System.out.println("Starting Bot");
                        startBot();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void startBot() {
            registerListener();
            EnablePlugins();
            builder.setShardsTotal(Integer.parseInt(MaxShards));
            builder.setShards(Integer.parseInt(Shard));
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

    private static void EnablePlugins() {
        try {
            Class<?>[] classes = PluginLoader.loadDirectory(new File("plugins"));
            Plugin[] plugins = PluginLoader.initAsPlugin(classes);
            for (int i = 0; i < plugins.length; i++) {
                if (plugins[i].hascommand()) {
                    commandHandler.commands.add(plugins[i]);
                }
                if (plugins[i].haslistener()) {
                    eventlistener.plugins.add(plugins[i]);
                }
                plugins[i].onEnable();
                System.out.println(classes[i].getName()+" Enabled!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void registerListener() {
        builder.addEventListeners(
          new ListenerCommand(),
                new Message(),
                new BotLists(),
                new eventlistener()
        );
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
    public static SessionController getSessionController() {
        return sessionController;
    }

}

package GB;

import GB.Handler.*;
import GB.Handler.CommandHandling.Command;
import GB.Handler.CommandHandling.commandHandler;
import GB.Handler.CommandHandling.commandListener;
import GB.commands.moderation.CommandLobby;
import GB.commands.moderation.CommandMoveAll;
import GB.commands.moderation.CommandPrefix;
import GB.commands.owner.*;
import GB.commands.usercommands.*;
import GB.listener.Lobbylistener;
import GB.listener.MentionListener;
import GB.listener.Shardlistener;
import GB.listener.shutdown;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import net.dv8tion.jda.bot.sharding.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.utils.SessionController;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;

public class GottBot {
    private static DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
    public static ShardManager shardManager;
    private static SessionController sessionController;
    private static Config config;
    private static boolean streaming=true;
    private static boolean dev=true;
    private static String MaxShards;
    private static String Shard;
    private static PrintWriter printWriter;
    private static BufferedReader bufferedReader;
    private static ArrayList<Server> serverclasses=new ArrayList<>();
    private static boolean debug=true;
    private static HashMap<String, Command[]> commands;
    private static ArrayList<String> commandlists;
    private static JDA oneShardBot;

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
                if (!dev) {
                    Socket socket = new Socket(getConfig().getServerIP(), Integer.parseInt(getConfig().getServerPort()));
                    printWriter = new PrintWriter(socket.getOutputStream());
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    printWriter.println("Password: " + getConfig().getServerPassword());
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
                        } else if (line.equals("ShardManager - inforequest")) {
                            JDA jda = shardManager.getShards().get(0);
                            printWriter.println("ShardManager - Inforesult ShardID:" + jda.getShardInfo().getShardId() + " Guilds:" + jda.getGuilds().size() + " Users:" + jda.getUsers().size() + " TextChannels:" + jda.getTextChannels().size() + " VoiceChannels:" + jda.getVoiceChannels().size() + " Categories:" + jda.getCategories().size());
                            printWriter.flush();
                        } else if (line.equals("Stop!")) {
                            shardManager.shutdown();
                        } else {
                            for (int i = 0; serverclasses.size() > i; i++) {
                                serverclasses.get(i).onMessage(line);
                            }
                        }
                    }
                } else {
                    Shard="0";
                    MaxShards="1";
                    startBot();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    private static void startBot() {
            registerListener();
            registerCommands();
            registerServerClasses();
            startOneShardBot();
            builder.setShardsTotal(Integer.parseInt(MaxShards));
            builder.setShards(Integer.parseInt(Shard));
            builder.setToken(getConfig().getToken());
            builder.setSessionController(sessionController);
            builder.setAutoReconnect(true);
            try {
                shardManager = builder.build();
            } catch (LoginException e) {
                e.printStackTrace();
            }
    }
    private static void startOneShardBot() {
        try {
            oneShardBot = new JDABuilder(AccountType.BOT).setToken(getConfig().getToken()).addEventListener(new Lobbylistener(), new Shardlistener()).setAutoReconnect(true).buildAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void registerServerClasses() {
        Server[] servers = {new CommandShardManager(), new CommandGameAnimator()};
        for (int i =0;i<servers.length; i++) {
            serverclasses.add(servers[i]);
            System.out.println("[Shardlistener] "+servers[i].getClass().getSimpleName());
        }
    }
    private static void registerCommands() {
        Command[] OwnerCommands = {
                new CommandShardManager(),
                new Test(),
                new Shutdown(),
                new CommandProfile(),
                new CommandGameAnimator()
        };
        Command[] ModerationComamnds = {
            new CommandMoveAll(), new CommandLobby(), new CommandPrefix()
        };
        Command[] Usercomamnds = {
                new CommandHelp(),
                new CommandFeedback(),
                new CommandLanguage(),
                new CommandGuildInfo(),
                new CommandQuote(),
                new CommandGottCoin()
        };
        commands = new HashMap<>();
        commands.put("owner", OwnerCommands);
        commands.put("user", Usercomamnds);
        commands.put("moderation", ModerationComamnds);
        commandlists = new ArrayList<>();
        commandlists.add("owner");
        commandlists.add("user");
        commandlists.add("moderation");
        for (String list:commandlists) {
            for (Command cmd:commands.get(list)) {
                for (String alias:cmd.Aliases()) {
                    if (commandHandler.commands.get(alias)==null) {
                        commandHandler.commands.put(alias, cmd);
                        if (debug) {
                            System.out.println("[Command] " + cmd.getClass().getSimpleName() + " alias " + alias);
                        }
                    } else {
                        System.out.println("Multiple Aliases Found! : "+alias+" in "+cmd.getClass().getName()+ " and "+
                                commandHandler.commands.get(alias).getClass().getName()+"... Exiting...");
                        System.exit(1);
                    }
                }
            }
        }
    }
    private static void read() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            File file = new File("config.yml");
            if (!file.exists()) {
                URL website = new URL("https://bigbotnetwork.de/example.config.yml");
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                FileOutputStream fos = new FileOutputStream("config.yml");
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                System.out.println("Please fill the config.yml. Exiting...");
                System.exit(0);
            }
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
                new GB.listener.Message(),
                new shutdown(),
                new MentionListener()
                // ,new BotLists()
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
    public static Message getMessage() {
        return new Message();
    }
    public static PrintWriter sendToServer() {
        return printWriter;
    }
    public static boolean getDev() {
        return dev;
    }
    public static ShardManager getShardManager() {
        return shardManager;
    }
    public static SessionController getSessionController() {
        return sessionController;
    }
    public static ArrayList<String> getCommandlists() {
        return commandlists;
    }
    public static HashMap<String, Command[]> getCommands() {
        return commands;
    }
    public static JDA getOneShardBot() {
        return oneShardBot;
    }

}

package GB;

import GB.Handler.DB;
import GB.Handler.Info;
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

import GB.Handler.Config;

public class GottBot {
    private static DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
    public static ShardManager shardManager;
    private static SessionController sessionController;
    private static Config config;


    public static void main(String[] args) {
        Startall();
    }

    private static void Startall() {
        read();
        DB.Connect();
        startBot();
    }

    private static void startBot() {
            builder.setShardsTotal(2);
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
            System.out.println(ReflectionToStringBuilder.toString(config, ToStringStyle.MULTI_LINE_STYLE));

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

}

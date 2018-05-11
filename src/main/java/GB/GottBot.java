package GB;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import net.dv8tion.jda.bot.sharding.*;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.utils.SessionController;

import javax.security.auth.login.LoginException;
import java.io.File;
import GB.Handler.Config;

public class GottBot {
    private static DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
    public static ShardManager shardManager;
    private static SessionController sessionController;


    public static void main(String[] args) {
        read();
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
            mapper.readValue(new File("config.yml"), Config.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Config getConfig() {
        return new Config();
    }

}

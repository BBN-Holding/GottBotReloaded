package commands.botowner;

import commands.Command;
import core.Main;
import core.MySQL;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandRegisterServer implements Command {
    private static Logger logger = LoggerFactory.getLogger(CommandRegisterServer.class);
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
            Thread t = new Thread(() -> {
                int i = 0;
                int i2 = 0;
                ShardManager shardManager = Main.shardManager;
                while (shardManager.getGuilds().size() - 1 >= i) {
                    if (MySQL.get("server", "id", shardManager.getGuilds().get(i).getId(), "id") == null) {
                        MySQL.insert("server", "id", shardManager.getGuilds().get(i).getId() + "");
                        logger.info("neuer Server in database Name: " + shardManager.getGuilds().get(i).getName() + " ID: " + shardManager.getGuilds().get(i).getId());
                        i2++;
                    }
                    i++;
                }
                event.getTextChannel().sendMessage("Succesfully registered " + i2 + " server").queue();
            });
            t.setName("registerserver");
            t.start();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}

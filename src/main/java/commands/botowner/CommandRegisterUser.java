package commands.botowner;

import commands.Command;
import core.MySQL;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandRegisterUser implements Command {
    private static Logger logger = LoggerFactory.getLogger(CommandRegisterUser.class);
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
            Thread t = new Thread(() -> {
                int i2 = 0;
                while (event.getJDA().getGuilds().size() - 1 >= i2) {
                    int i = 0;
                    while (event.getJDA().getGuilds().get(i2).getMembers().size() - 1 >= i) {
                        if (MySQL.get("user", "id", event.getJDA().getGuilds().get(i2).getMembers().get(i).getUser().getId(), "id") == null) {
                            MySQL.insert("user", "id", event.getJDA().getGuilds().get(i2).getMembers().get(i).getUser().getId() + "");
                            logger.info("neuer User in database Name: " + event.getJDA().getGuilds().get(i2).getMembers().get(i).getUser().getName() + " ID: " + event.getJDA().getGuilds().get(i2).getMembers().get(i).getUser().getId() + " von " + event.getJDA().getGuilds().get(i2).getName());
                        }
                        i++;
                    }
                    i2++;
                }
                event.getTextChannel().sendMessage("Succesfully registered all users").queue();
            });
            t.setName("registeruser");
            t.start();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}

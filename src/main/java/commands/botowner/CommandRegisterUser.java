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
                int i = 0;
                while (event.getGuild().getMembers().size() - 1 >= i) {
                    if (MySQL.get("user", "id", event.getGuild().getMembers().get(i).getUser().getId(), "id") == null) {
                        MySQL.insert("user", "id", event.getGuild().getMembers().get(i).getUser().getId() + "");
                        logger.info("neuer User in database Name: " + event.getGuild().getMembers().get(i).getUser().getName() + " ID: " + event.getGuild().getMembers().get(i).getUser().getId() + " von " + event.getGuild().getName());
                    }
                    i++;
                }
                event.getTextChannel().sendMessage("Succesfully registered "+i+" user").queue();
            });
            t.setName("registeruser");
            t.start();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}

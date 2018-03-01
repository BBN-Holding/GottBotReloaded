package commands.botowner;

import commands.Command;
import core.MySQL;
import listener.commandListener;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class registeruser implements Command {
    private static Logger logger = LoggerFactory.getLogger(registeruser.class);
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Handler.get(event.getAuthor())) {
            int i = 0;
            while (event.getGuild().getMembers().size()-1>=i) {
                if (MySQL.get("user", "id", event.getGuild().getMembers().get(i).getUser().getId(), "id")==null) {
                    MySQL.insert("user", "id", event.getGuild().getMembers().get(i).getUser().getId()+"");
                    logger.info("neuer User in database Name: " + event.getGuild().getMembers().get(i).getUser().getName() + " ID: " + event.getGuild().getMembers().get(i).getUser().getId() + " von " + event.getGuild().getName());
                }
                i++;
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}

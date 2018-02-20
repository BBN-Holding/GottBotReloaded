package commands;

import core.MySQL;
import listener.Memberjoin;
import listener.commandListener;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stuff.SECRETS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static stuff.DATA.url;

public class registeruser implements Command {
    private static Logger logger = LoggerFactory.getLogger(registeruser.class);
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        int i=0;
        while (event.getGuild().getMembers().size()>=i) {
            if (!MySQL.get("user", "ID", event.getGuild().getMembers().get(i).getUser().getId(), "ID").isEmpty()) {
                MySQL.insert("user", "ID", event.getGuild().getMembers().get(i).getUser().getId());
                logger.info("neuer User in database Name: " + event.getGuild().getMembers().get(i).getUser().getName() + " ID: " + event.getGuild().getMembers().get(i).getUser().getId() + " von " + event.getGuild().getName());
            }
            i++;
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

package listener;

import core.Main;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import core.commandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stuff.SECRETS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

import static stuff.DATA.url;

public class commandListener extends ListenerAdapter {
    public static String beheaded;
    private static Logger logger = LoggerFactory.getLogger(commandListener.class);
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {

            Connection con = DriverManager.getConnection(url + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SECRETS.user, SECRETS.password);
            PreparedStatement pst = con.prepareStatement("Select * FROM `server` WHERE ID=" + event.getGuild().getId());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String PREFIX=rs.getString(2);
                if (event.getMessage().getContentRaw().startsWith(PREFIX) && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()) {
                    beheaded = event.getMessage().getContentRaw().toLowerCase().replaceFirst(Pattern.quote(PREFIX), "");
                    commandHandler.handleCommand(commandHandler.parser.parse(event.getMessage().getContentRaw().toLowerCase(), event));
                    logger.info(event.getAuthor().getName()+" mit ID "+ event.getAuthor().getId()+" auf "+event.getGuild().getName()+" hat den Command genutzt: "+ event.getMessage().getContentRaw());
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
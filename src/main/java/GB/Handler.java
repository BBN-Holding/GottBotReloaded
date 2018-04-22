package GB;

import GB.stuff.SECRETS;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.LoggerFactory;
import stuff.DATA;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.List;

public class Handler {
    public MySQL getMySQL() {
        return new MySQL();
    }
    public Error getError() {
        return new Error();
    }
    public MenuHandler getMenuHandler() {
        return new MenuHandler();
    }
}


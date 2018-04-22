package GB;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.LoggerFactory;
import stuff.DATA;
import stuff.SECRETS;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
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

public class MenuHandler {

    public static MessageEmbed getMessage(String emote, MessageEmbed embed, User user, Guild guild) {
        MessageEmbed out=new EmbedBuilder().setTitle("GB.Error").setDescription("Sorry the Emote was not found! if you are sure that it is a bug use ``gb.bug`` Thanks <3").setColor(Color.RED).build();
        // Overview
        if (embed.getTitle().equals(MessageHandler.get("Helpmenu.helpmenu", user, guild))) {
            switch (emote) {
                case "\uD83D\uDD28":
                    out = new EmbedBuilder().setTitle(MessageHandler.get("Helpmenu.moderation.title", user, guild))
                            .addField(MessageHandler.get("Helpmenu.moderation.ban.title", user, guild), MessageHandler.get("Helpmenu.moderation.ban.description", user, guild), false)
                            .addField(MessageHandler.get("Helpmenu.moderation.kick.title", user,guild), MessageHandler.get("Helpmenu.moderation.kick.description", user, guild), false)
                            .addField(MessageHandler.get("Helpmenu.moderation.prefix.title", user, guild), MessageHandler.get("Helpmenu.moderation.prefix.description", user, guild), false)
                            .addField(MessageHandler.get("Helpmenu.moderation.verification.pre", user, guild), "✅", false)
                            .build();
                    break;

                case "\uD83D\uDEE0":
                    out = new EmbedBuilder().setTitle(MessageHandler.get("Helpmenu.tools.title", user, guild))
                            .addField(MessageHandler.get("Helpmenu.tools.github.title", user, guild), MessageHandler.get("Helpmenu.tools.github.description", user, guild), true)
                            .addField(MessageHandler.get("Helpmenu.tools.ping.title", user, guild), MessageHandler.get("Helpmenu.tools.ping.description", user, guild), true)
                            .addField(MessageHandler.get("Helpmenu.tools.profile.title", user, guild), MessageHandler.get("Helpmenu.tools.profile.description", user, guild), true)
                            .build();
                    break;

                case "\uD83D\uDC65":
                    out= new EmbedBuilder().setTitle(MessageHandler.get("Helpmenu.usercommands.title", user, guild))
                            .addField(MessageHandler.get("Helpmenu.usercommands.bug.title", user, guild), MessageHandler.get("Helpmenu.usercommands.bug.description", user, guild), true)
                            .addField(MessageHandler.get("Helpmenu.usercommands.help.title", user, guild), MessageHandler.get("Helpmenu.usercommands.help.description", user, guild), true)
                            .addField(MessageHandler.get("Helpmenu.usercommands.language.title", user, guild), MessageHandler.get("Helpmenu.usercommands.language.description", user, guild), true)
                            .addField(MessageHandler.get("Helpmenu.usercommands.levelmessage.title", user, guild), MessageHandler.get("Helpmenu.usercommands.levelmessage.description", user, guild), true)
                            .addField(MessageHandler.get("Helpmenu.usercommands.say.title", user, guild), MessageHandler.get("Helpmenu.usercommands.say.description", user, guild), true)
                            .addField(MessageHandler.get("Helpmenu.usercommands.stats.title", user, guild), MessageHandler.get("Helpmenu.usercommands.stats.description", user, guild), true)
                            .build();
                    break;
            }
        } else if (embed.getTitle().equals(MessageHandler.get("Helpmenu.moderation.title", user, guild))) {
            switch (emote) {
                case "✅":
                    out = new EmbedBuilder().setTitle(MessageHandler.get("Helpmenu.moderation.verification.title", user, guild))
                            .addField("Usage", "gb.verification", false)
                            .addField("Description", "Set up a Verfixation Message", false)
                            .build();
                    break;
            }
        }
        System.out.println(embed.getTitle());
        if (embed.getTitle().replaceFirst(MessageHandler.get("Helpmenu.helpmenu.mod.title", user, guild), "").replaceFirst(MessageHandler.get("Helpmenu.helpmenu.tools.title", user, guild), "").replaceFirst(MessageHandler.get("Helpmenu.helpmenu.usercommands.title", user, guild), "").equals(MessageHandler.get("Helpmenu.helpmenu", user, guild)+" - ")&&emote.equals("\uD83D\uDD19")) {
            out = new EmbedBuilder().setTitle(MessageHandler.get("Helpmenu.helpmenu", user, guild)).setDescription(MessageHandler.get("Helpmenu.helpmenu.description", user, guild))
                    .addField(MessageHandler.get("Helpmenu.helpmenu.mod.title", user, guild), "\uD83D\uDD28", true)
                    .addField(MessageHandler.get("Helpmenu.helpmenu.tools.title", user, guild), "\uD83D\uDEE0", true)
                    .addField(MessageHandler.get("Helpmenu.helpmenu.usercommands.title", user, guild), "\uD83D\uDC65", true)
                    .build();


        } else if (emote.equals("◀")&&embed.getTitle().equals(MessageHandler.get("Helpmenu.moderation.verification.title", user, guild))) {
            out= MenuHandlerold.getMessage("\uD83D\uDD28", new EmbedBuilder().setTitle(MessageHandler.get("Helpmenu.helpmenu", user, guild)).build(), user, guild);
        }
        return out;
    }

    public static java.util.List<String> getemote(String emote, MessageEmbed embed, User user, Guild guild) {
        List<String> list = new ArrayList<>();


        if (embed.getTitle().equals(MessageHandler.get("Helpmenu.helpmenu", user, guild))&&emote.equals("\uD83D\uDD19")) {
            list.add("\uD83D\uDD28");
            list.add("\uD83D\uDEE0");
            list.add("\uD83D\uDC65");
        }

        if (embed.getTitle().equals(MessageHandler.get("Helpmenu.moderation.title", user, guild))) {
            list.add("✅");
        }

        if (embed.getTitle().contains(MessageHandler.get("Helpmenu.helpmenu", user, guild)+" - ")) {
            list.add("\uD83D\uDD19");
        }

        if (embed.getTitle().replaceFirst(MessageHandler.get("Helpmenu.helpmenu", user, guild)+" - ", "").contains(" - ")) {

            list.add("◀");

        }


        return list;
    }


}

public class Error {
    private String errorString(Exception e) {
        String content="";
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos, true, "utf-8");
            e.printStackTrace(ps);
            content = new String(baos.toByteArray(), StandardCharsets.UTF_8);
            ps.close();
        } catch (Exception er) {
            e.printStackTrace();
        }
        return content;
    }
    public String geterrorString(Exception e) {
        return errorString(e);
    }

    private boolean handle(Exception e) {
        try {
            String error = geterrorString(e);
            String substring="";
            String filename = "GB.Error-"+new Date().toGMTString().replaceAll(" ", "-").replaceAll(":", "-")+".txt";
            substring += "An error gönn dir: https://bigbotnetwork.de/errors/"+filename;
            new File(filename).createNewFile();
            PrintWriter pWriter = null;
            try {
                pWriter = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
                pWriter.print(error);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                if (pWriter != null){
                    pWriter.flush();
                    pWriter.close();
                }
            }
            FTPClient client = new FTPClient();
            FileInputStream fis = null;
            client.connect("ftp.bigbotnetwork.de");
            client.login(SECRETS.FTPUSER, SECRETS.FTPPW);
            fis = new FileInputStream(filename);
            client.storeFile("httpdocs/errors/"+filename, fis);
            client.logout();
            System.out.println(substring);
            Main.shardManager.getGuildById(DATA.BBNS).getTextChannelById(DATA.BBNLOG).sendMessage(new EmbedBuilder().setTitle(":warning: GB.Error :warning:").setDescription("<@401817301919465482> <@261083609148948488> A ERROR: "+substring).build()).queue();
        } catch (Exception er) {
            er.printStackTrace();
        }
        return true;
    }

    public boolean gethandle(Exception e) {
        return handle(e);
    }

}

public class MySQL {


    private static Connection connection;
    private static org.slf4j.Logger Logger = LoggerFactory.getLogger(MySQL.class);

    public static Connection getConnection() {
        return connection;
    }


    private boolean connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/gottbot?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SECRETS.user, SECRETS.password);
            Logger.info("GB.MySQL connection success");
        } catch (SQLException e) {
            Logger.error(e.toString());
            Logger.error("GB.MySQL connection failed");
        }
        return true;
    }

    public static void disconnect() {
        try {
            connection.close();
            System.out.println("disconnected from MYSQL");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String get(String table, String where, String wherevalue, String spalte) {
        String out="";
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `"+table+"` WHERE `"+where+"`=?");
            ps.setString(1, wherevalue);
            ResultSet rs = ps.executeQuery();
            // Only returning one result

            if (rs.next()) {
                out = rs.getString(spalte);
            } else out=null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    public static String getfirst(String table, String wherevalue, String spalte) {
        String out="";
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `"+table+"` WHERE ?");
            ps.setString(1, wherevalue);
            ResultSet rs = ps.executeQuery();
            // Only returning one result

            if (rs.next()) {
                out = rs.getString(spalte);
            } else out=null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    public List<String> getall(String table, String where, String wherevalue, String spalte) {
        List<String> List = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `"+table+"` WHERE `"+where+"`=?");
            ps.setString(1,wherevalue);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List.add(rs.getString(spalte));
            }
            return List;
        } catch (Exception ex) {
            Logger.error(ex.toString());
        }
        return List;
    }

    public List<String> getallwithoutwhere(String table, String spalte) {
        List<String> List = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `"+table+"`");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List.add(rs.getString(spalte));
            }
            return List;
        } catch (Exception ex) {
            Logger.error(ex.toString());
        }

        return List;
    }

    public String getallstring(String table, String spalte) {
        String out="";
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `" + table + "`");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                out += rs.getString(spalte)+" ";
            }
            return out;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public String update(String table, String what, String whatvalue, String where, String wherevalue) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE `"+table+"` SET `"+what+"`=? WHERE `"+where+"`=?");
            ps.setString(1, whatvalue);
            ps.setString(2, wherevalue);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String insert(String table, String what, String whatvalue) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `"+table+"`(`"+what+"`) VALUES ('"+whatvalue+"')");
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(String table, String where, String wherevalue) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM `"+table+"` WHERE `"+where+"`=?");
            ps.setString(1, wherevalue);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exist(String table, String where) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `"+table+"` WHERE `"+where+"` = ?");
            ps.execute();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

}


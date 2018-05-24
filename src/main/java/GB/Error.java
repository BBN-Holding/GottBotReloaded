package GB;

import GB.core.Main;
import GB.stuff.SECRETS;
import net.dv8tion.jda.core.EmbedBuilder;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;

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
            if (Main.uploaderrors) {
                String error = geterrorString(e);
                String substring = "";
                String filename = "GB.Error-" + new Date().toGMTString().replaceAll(" ", "-").replaceAll(":", "-") + ".txt";
                substring += "An error gönn dir: https://bigbotnetwork.de/errors/" + filename;
                new File(filename).createNewFile();
                PrintWriter pWriter = null;
                try {
                    pWriter = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
                    pWriter.print(error);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } finally {
                    if (pWriter != null) {
                        pWriter.flush();
                        pWriter.close();
                    }
                }
                FTPClient client = new FTPClient();
                FileInputStream fis = null;
                client.connect("ftp.bigbotnetwork.de");
                client.login(SECRETS.FTPUSER, SECRETS.FTPPW);
                fis = new FileInputStream(filename);
                client.storeFile("httpdocs/errors/" + filename, fis);
                client.logout();
                System.out.println(substring);
                Main.shardManager.getGuildById(GB.stuff.DATA.BBNS).getTextChannelById(GB.stuff.DATA.BBNLOG).sendMessage(new EmbedBuilder().setTitle(":warning: GB.Error :warning:").setDescription("<@401817301919465482> <@261083609148948488> A ERROR: " + substring).build()).queue();
                Main.shardManager.getGuildById(GB.stuff.DATA.BBNS).getTextChannelById(GB.stuff.DATA.BBNLOG).sendMessage("<@401817301919465482> <@261083609148948488> ^").queue();
            } else e.printStackTrace();
        } catch (Exception er) {
            er.printStackTrace();
        }
        return true;
    }

    public boolean gethandle(Exception e) {
        return handle(e);
    }

}

package core;

import net.dv8tion.jda.core.entities.Guild;
import stuff.SECRETS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static stuff.DATA.url;

public class MessageHandler {

    public static String Message;
    public static String Titel;

    public static void get(String lang, String message, Guild guild){
        try {
            Connection con = DriverManager.getConnection(url + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SECRETS.user, SECRETS.password);
            PreparedStatement pst = con.prepareStatement("SELECT * FROM `server` WHERE ID='"+guild.getId()+"'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                // Bugsucess
                if (message.equals("bugsucess")) {
                    if (lang.equals("english")) {
                        Titel="Bug reported";
                        Message="Successfully send the bug to the developers.";
                    } else if (lang.equals("german")) {
                        Titel="Bug gemeldet";
                        Message="Der Bug wurde erfolgreich zu den Entwicklern geschickt.";
                    }
                }
                // noperms
                if (message.equals("nopers")) {
                    if (lang.equals("english")) {
                        Titel="No permissions";
                        Message="You need the GBOwner Role to use the Command";
                    } else if (lang.equals("german")) {
                        Titel="Keine Rechte";
                        Message="Du brauchst die GBOwner Rolle damit du den Command benutzen darfst";
                    }
                }
                // help
                if (message.equals("help")) {
                    if (lang.equals("english")) {
                        Titel="Help Menu";
                        Message="Here comes the Help for you!\n" +
                                "``"+rs.getString(2)+"help`` - You become Help\n" +
                                "``"+rs.getString(2)+"language`` - You can change your language\n" +
                                "``"+rs.getString(2)+"prefix`` - [GBOwner Role only] You can edit the Server Prefix\n" +
                                "``"+rs.getString(2)+"test`` - A Test";
                    } else if (lang.equals("german")) {
                        Titel="Hilfe Menü";
                        Message="Hier kommt Hilfe für dich!\n" +
                                "``"+rs.getString(2)+"help`` - Damit kommst du hier hin\n" +
                                "``"+rs.getString(2)+"language`` - Damit kannst du deine Sprache ändern\n" +
                                "``"+rs.getString(2)+"prefix`` - [nur GBOwner Role] Damit kannst du den Prefix ändern\n" +
                                "``"+rs.getString(2)+"test`` - Ein Test";
                    }
                }
                // lang
                if (message.equals("lang")) {
                    if (lang.equals("english")) {
                        Titel = "Your language";
                        Message = "Your language is english\n" +
                                "You can edit your language with " + rs.getString(2) + "language <german|english>";
                    } else if (lang.equals("german")) {
                        Titel = "Deine Sprache";
                        Message = "Deine Sprache ist derzeit German\n" +
                                "Umstellen kannst du sie mit " + rs.getString(2) + "language <german|english>";
                    }
                }
                // Langedit
                if (message.equals("langedit")) {
                    if (lang.equals("english")) {
                        Titel = "Your language was edited";
                        Message = "Your language is now english!";
                    } else if (lang.equals("german")) {
                        Titel = "Deine Sprache wurde geändert";
                        Message = "Deine Sprache ist nun Deutsch!";
                    }
                }
                // prefix
                if (message.equals("prefix")) {
                    if (lang.equals("english")) {
                        Titel="Prefix";
                        Message="The Prefix on this Server is: "+rs.getString(2)+"\nTo set a new Prefix write: "+rs.getString(2)+"prefix <New Prefix>";
                    } else if (lang.equals("german")) {
                        Titel="Prefix";
                        Message="Der Prefix auf diesem Server ist: "+rs.getString(2)+"\nWenn du einen neuen setzen möchtest schreibe: "+rs.getString(2)+"prefix <Neuer Prefix>";
                    }
                }
                // prefixchanged
                if (message.equals("prefixchanged")) {
                    if (lang.equals("english")) {
                        Titel="Prefix changed";
                        Message="The Prefix on this Server is now: "+rs.getString(2);
                    } else if (lang.equals("german")) {
                        Titel="Prefix geändert";
                        Message="Der Prefix auf diesem Server ist jetzt: "+rs.getString(2);
                    }
                }
                //prefixerror1
                if (message.equals("prefixerror1")) {
                    if (lang.equals("english")) {
                        Titel = "Error";
                        Message = "This Prefix is not Supported by the Bot. Please take another Prefix";
                    } else if (lang.equals("german")) {
                        Titel="Fehler";
                        Message="Dieser Prefix wird von unserem Bot nicht unterstützt. Bitte benutze einen anderen";
                    }
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

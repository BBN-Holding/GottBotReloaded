package core;

import listener.Message;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import stuff.SECRETS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static stuff.DATA.url;

public class MessageHandler {

    public static String Message;
    public static String Titel;
    public static String language;
    public static void in(User user, boolean MessageHandler, String message, Guild guild) {
        try {
            System.out.println("adadasd");
            language = MySQL.get("user", "ID", user.getId()+"", "language");
            System.out.println(language);
            System.out.println("sasafasdfasf");
            if (MessageHandler) {
                core.MessageHandler.get(language, message, guild);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void get(String lang, String message, Guild guild){
        try {
            String Prefix = MySQL.get("server", "ID", guild.getId(), "prefix");
                // ClanList
                if (message.equals("clanlist")) {
                    if (lang.equals("english")) {
                        Message="All Clans: \n";
                    } else if (lang.equals("german")) {
                        Message="Alle Clans: \n";
                    }
                }
                // Clan
                if (message.equals("clan")) {
                    if (lang.equals("english")) {
                        Titel="Clan - Help";
                        Message="Here are all Clan Commands:\n"+Prefix+"clan list - Get a list from all Clans on your Server";
                    } else if (lang.equals("german")) {
                        Titel="Clan - Hilfe";
                        Message="Hier sind alle Clan Commands:\n"+Prefix+"clan list - Bekomme eine Liste mit allen Clans auf diesem Server";
                    }
                }
                // Test
                if (message.equals("test")) {
                    if (lang.equals("english")) {
                        Titel="Test";
                        Message="Testt english";
                    } else if (lang.equals("german")) {
                        Titel="Test";
                        Message="Test german :D";
                    }
                }
                // joinmessagesucess
                if (message.equals("joinmessagesucess")) {
                    if (lang.equals("englsih")) {
                        Titel="Sucess";
                        Message="The Message is now edited. The Message is now: ";
                    } else if (lang.equals("german")) {
                        Titel="Erfolgreich!";
                        Message="Die Nachricht wurde erfolgreich geändert! Sie lautet nun: ";
                    }
                }
                // joinmessage
                if (message.equals("joinmessage")) {
                    if (lang.equals("english")) {
                        Titel="Joinmessage";
                        Message="You can edit the joinmessage with ``"+Prefix+"joinmessage [#channel] [Message] (%s = Servername, %m = Membername)``. Your Joinmessage is: ";
                    } else if (lang.equals("german")) {
                        Titel="Betretungs Nachricht";
                        Message="Du kannst die Nachricht ändern mit ``"+Prefix+"joinmessage [#Kanal] [Nachricht] (%s = Servername, %m = Nutzername) ``. Deine betretungsNachricht ist: ";
                    }
                }
                // Profile
                if (message.equals("profile")) {
                    if (lang.equals("english")) {
                        Titel="Your Profile";
                    } else if (lang.equals("german")) {
                        Titel="Dein Profil";
                    }
                }
                // Mention
                if (message.equals("mention")) {
                    if (lang.equals("english")) {
                        Titel="Hi!";
                        Message="I'm the GottBot. My Prefix is ``"+Prefix+"`` Write ``"+Prefix+"help`` :smile:";
                    } else if (lang.equals("german")) {
                        Titel="Hi!";
                        Message="Ich bin der GottBot. Mein Prefix ist ``"+Prefix+"`` Schreibe ``"+Prefix+"help`` :smile:";
                    }
                }
                // Bug
                if (message.equals("bug")) {
                    if (lang.equals("english")) {
                        Titel="Bug - Usage";
                        Message=Prefix+"bug [Message min. 3 words]";
                    } else if (lang.equals("german")) {
                        Titel="Bug - Verwendung";
                        Message=Prefix+"bug [Nachricht min. 3 Wörter]";
                    }
                }
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
                                "``"+Prefix+"help`` - You become Help\n" +
                                "``"+Prefix+"language`` - You can change your language\n" +
                                "``"+Prefix+"prefix`` - [GBOwner Role only] You can edit the Server Prefix\n" +
                                "``"+Prefix+"test`` - A Test";
                    } else if (lang.equals("german")) {
                        Titel="Hilfe Menü";
                        Message="Hier kommt Hilfe für dich!\n" +
                                "``"+Prefix+"help`` - Damit kommst du hier hin\n" +
                                "``"+Prefix+"language`` - Damit kannst du deine Sprache ändern\n" +
                                "``"+Prefix+"prefix`` - [nur GBOwner Role] Damit kannst du den Prefix ändern\n" +
                                "``"+Prefix+"test`` - Ein Test";
                    }
                }
                // lang
                if (message.equals("lang")) {
                    if (lang.equals("english")) {
                        Titel = "Your language";
                        Message = "Your language is english\n" +
                                "You can edit your language with " + Prefix + "language <german|english>";
                    } else if (lang.equals("german")) {
                        Titel = "Deine Sprache";
                        Message = "Deine Sprache ist derzeit German\n" +
                                "Umstellen kannst du sie mit " + Prefix + "language <german|english>";
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
                        Message="The Prefix on this Server is: "+Prefix+"\nTo set a new Prefix write: "+Prefix+"prefix <New Prefix>";
                    } else if (lang.equals("german")) {
                        Titel="Prefix";
                        Message="Der Prefix auf diesem Server ist: "+Prefix+"\nWenn du einen neuen setzen möchtest schreibe: "+Prefix+"prefix <Neuer Prefix>";
                    }
                }
                // prefixchanged
                if (message.equals("prefixchanged")) {
                    if (lang.equals("english")) {
                        Titel="Prefix changed";
                        Message="The Prefix on this Server is now: "+Prefix;
                    } else if (lang.equals("german")) {
                        Titel="Prefix geändert";
                        Message="Der Prefix auf diesem Server ist jetzt: "+Prefix;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package core;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import stuff.SECRETS;

public class Main {

    public static JDABuilder builder;

    public static void main(String[] args) {
        builder = new JDABuilder(AccountType.BOT).setToken(SECRETS.TOKEN).setAutoReconnect(true).setStatus(OnlineStatus.ONLINE);


        try {
            JDA jda = builder.buildBlocking();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

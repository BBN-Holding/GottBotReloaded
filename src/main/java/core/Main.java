package core;

import net.dv8tion.jda.core.JDABuilder;
import stuff.SECRETS;

public class Main {

    public static JDABuilder builder;

    public static void main(String[] args) {
        builder.setToken(SECRETS.TOKEN)
    }

}

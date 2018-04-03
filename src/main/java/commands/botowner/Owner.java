package commands.botowner;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.User;
import stuff.DATA;
import util.Embed;

public class Owner {

    public static Boolean get(User user) {
        boolean yes=false;
        if (user.getId().equals(DATA.Skidder)||user.getId().equals(DATA.Hax)) {
            yes=true;
        }
        return yes;
    }

}

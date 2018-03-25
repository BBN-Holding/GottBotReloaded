package commands.botowner;

import net.dv8tion.jda.core.entities.User;
import stuff.DATA;

public class Handler {

    public static Boolean get(User user) {
        boolean yes=false;
        if (user.getId().equals(DATA.Skidder)||user.getId().equals(DATA.Hax)) {
            yes=true;
        }
        return yes;
    }
}

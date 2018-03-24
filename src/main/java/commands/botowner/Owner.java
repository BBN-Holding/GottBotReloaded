package commands.botowner;

import net.dv8tion.jda.core.entities.User;

public class Owner {

    public static Boolean get(User user) {
        boolean yes=false;
        if (user.getId().equals("362270177712275491")||user.getId().equals("261083609148948488")) {
            yes=true;
        }
        return yes;
    }

}

package commands.botowner;

import net.dv8tion.jda.core.entities.User;

public class Handler {

    public static Boolean get(User user) {
        boolean yes=false;
        if (user.getId().equals("362270177712275491")||user.getId().equals("261083609148948488")) {
            System.out.println("Yes");
            yes=true;
        } else System.out.println("keine perm's");
        return yes;
    }

}

package GB.Handler;

import GB.GottBot;
import net.dv8tion.jda.core.entities.User;

public class Language {

    public void getString(User user) {
        GottBot.getDB().get("user", "userid", user.getId(), "language");
    }

}

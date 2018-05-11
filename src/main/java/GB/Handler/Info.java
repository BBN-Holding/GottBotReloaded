package GB.Handler;

import GB.GottBot;

public class Info {

    public String getPrefix(String id) {
        return GottBot.getDB().get("server", "serverid", id, "prefix");
    }

}

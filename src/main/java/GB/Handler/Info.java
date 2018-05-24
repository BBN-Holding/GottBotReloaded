package GB.Handler;

import GB.GottBot;

import java.util.Collection;
import java.util.LinkedList;

public class Info {

    public String getPrefix(String id) {
        return GottBot.getDB().get("server", "serverid", id, "prefix");
    }
}

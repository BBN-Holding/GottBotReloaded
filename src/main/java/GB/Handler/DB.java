package GB.Handler;

import GB.GottBot;
import com.rethinkdb.RethinkDB;

public class DB {
    private static RethinkDB r = RethinkDB.r;
    public static void Connect() {
        r.connection()
                .hostname(GottBot.getConfig().getDB().get("Hostname"))
                .db(GottBot.getConfig().getDB().get("Database"))
                .port(Integer.parseInt(GottBot.getConfig().getDB().get("Port")))
        .connect();
    }

}

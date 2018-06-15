package GB.Handler;

import GB.GottBot;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.model.MapObject;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class DB {
    private static RethinkDB r = RethinkDB.r;
    private static Connection conn;

    public Connection getConn() {
        return conn;
    }

    public RethinkDB getR() {
        return r;
    }

    public static void Connect() {
        conn = r.connection()
                .hostname(GottBot.getConfig().getDB().get("Hostname"))
                .db(GottBot.getConfig().getDB().get("Database"))
                .port(Integer.parseInt(GottBot.getConfig().getDB().get("Port")))
                .user(GottBot.getConfig().getDB().get("User"), GottBot.getConfig().getDB().get("Password"))
                .connect();
        System.out.println("CONNECTED! " + conn.isOpen());
        System.out.println(r.tableList().run(conn).toString());
        ArrayList<String> tables = new ArrayList<>() {{
            add("user");
            add("server");
            add("serveruser");
            add("gottcoin");
        }};
        for (String table:tables) {
            if (!r.tableList().run(conn).toString().contains(table)) {
                r.tableCreate(table).run(conn);
                System.out.println("Created table "+table);
            }
        }
    }

    public HashMap<String, String> getMap(String table, String where, String wherevalue, String field1, String field2) {
        Cursor cursor = r.table(table).filter(row -> row.g(where).equals(wherevalue)).getField(field1).run(conn);
        Cursor cursor2 = r.table(table).filter(row -> row.g(where).equals(wherevalue)).getField(field2).run(conn);
        HashMap<String, String> strings=new HashMap<>();
        while (cursor.hasNext()&&cursor2.hasNext()) {
            strings.put(cursor.next().toString(), cursor2.next().toString());
        }
        return strings;
    }

    public String getAll(String table, String field) {
        Cursor cursor = r.table(table).getField(field).run(conn);
        return cursor.next().toString();
    }

    public String get(String table, String where, String wherevalue, String field) {
        String jsonString = "";
        try {
            Cursor cursor = r.table(table).filter(row -> row.g(where).eq(wherevalue)).getField(field).run(conn);
            if (cursor.hasNext()) {
                jsonString = cursor.next().toString();
            } else jsonString = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public String insert(String table, MapObject Hashmap) {
        String result = r.table(table).insert(Hashmap).run(conn).toString();
        return result;
    }

    public String insertUser(String id) {
        String result = r.table("user").insert(
                r.hashMap("userid", id)
                .with("language", "en")
        ).run(conn).toString();
        return result;
    }

    public String insertServer(String id) {
        String result = r.table("server").insert(
                r.hashMap("serverid", id)
                        .with("prefix", "gb.")
                        .with("lobby", "none")
        ).run(conn).toString();
        return result;
    }

    public String insertServerUser(String userid, String serverid) {
        String defaultperms = get("server", "serverid", serverid, "defaultperms");
        if (defaultperms == null) {
            insertServer(serverid);
        }
        String result = r.table("serveruser").insert(
                r.hashMap("serveruserid", serverid+" "+userid)
                        .with("perms", defaultperms)
        ).run(conn).toString();
        return result;
    }


    public String update(String table,String where, String wherevalue, MapObject hashmap) {
        String string = r.table(table).filter(row -> row.g(where).eq(wherevalue)).update(hashmap).run(conn).toString();
        return string;
    }

}

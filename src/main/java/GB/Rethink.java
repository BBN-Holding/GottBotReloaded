package GB;

import GB.stuff.SECRETS;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Rethink {
    private static org.slf4j.Logger Logger = LoggerFactory.getLogger(Rethink.class);
    private RethinkDB r = RethinkDB.r;
    private Connection conn;

    public Connection getConn() {
        return conn;
    }

    public boolean connect() {
        try {
            conn = r.connection().hostname(SECRETS.host).port(SECRETS.port).connect();
            Logger.info("GB.Rethink connection success");
        } catch (Exception e) {
            Logger.error(e.toString());
            Logger.error("GB.Rethink connection failed");
        }
        return true;
    }

    public void disconnect() {
        conn.close();
        System.out.println("disconnected from MYSQL");
    }

    public String get(String table, String where, String wherevalue, String spalte) {
        Cursor cursor = r.table(table).filter(row -> row.g(where).eq(wherevalue)).run(conn);
        JsonObject jsonObject=new JsonParser().parse(cursor.toString()).getAsJsonObject();
        return jsonObject.get(spalte).getAsString();
    }

    public String getAll(String table, String spalte) {
        String out ="";
        Cursor cursor = r.table(table).run(conn);
        for (Object doc : cursor) {
            out+=new JsonParser().parse(doc.toString()).getAsJsonObject().get(spalte)+" ";
        }
        return out;
    }

    public String update(String table, String what, String whatvalue, String where, String wherevalue) {
        Cursor cursor = r.table(table).filter(row -> row.g(where).eq(whatvalue)).update(r.hashMap(what, whatvalue)).run(conn);
        return cursor.toString();
    }

    public String insert(String table, String what, String whatvalue) {
            Cursor cursor=r.table(table).insert(r.hashMap(what, whatvalue)).run(conn);
        return cursor.toString();
    }

    public String delete(String table, String where, String wherevalue) {
        Cursor cursor=r.table(table).filter(row -> row.g(where).eq(wherevalue)).delete().run(conn);
        return cursor.toString();
    }

}

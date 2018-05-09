package GB;

import GB.stuff.SECRETS;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.model.MapObject;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Rethink {
    private static org.slf4j.Logger Logger = LoggerFactory.getLogger(Rethink.class);
    private RethinkDB r = RethinkDB.r;
    static Connection conn;

    public boolean connect() {
        try {
            conn= r.connection().hostname(SECRETS.host).db(SECRETS.DB).port(SECRETS.port).connect();
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
        try {
            // TODO: Remove "" e.g. you must do "gb."test not gb.test
            Cursor cursor = r.table(table).filter(row -> row.g(where.toLowerCase()).eq(wherevalue)).run(conn);
            JsonObject json=new JsonParser().parse(cursor.next().toString()).getAsJsonObject();
            return json.get(spalte).toString();
        } catch (NoSuchElementException e) {
            return null;
        }
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
        String out="";
        try {
            Cursor cursor = r.table(table).filter(row -> row.g(where).eq(whatvalue)).update(r.hashMap(what, whatvalue)).run(conn);
            out=cursor.toString();
        } catch (ClassCastException ignored) {}
        return out;
    }

    public String insert(String table, MapObject hashmap) {
            Cursor cursor=r.table(table).insert(hashmap).run(conn);
        return cursor.toString();
    }

    public String insertUser(String id) {
        String out="";
        try {
            Cursor cursor = r.table("user").insert(r.hashMap("id", id)
                    .with("language", "en")
                    .with("level", "0")
                    .with("xp", "0")
                    .with("hashes", "0")
                    .with("withdrawnhashes", "0")
                    .with("lvlmessage", "none")
                    .with("github", "none")
                    .with("premium", "none")
                    .with("clan", "none")
            ).run(conn);
            out= cursor.next().toString();
        } catch (ClassCastException ignored) {}
        return out;
    }

    public String insertServer(String id) {
        String out="";
        try {
        Cursor cursor=r.table("server")
                .insert(r.hashMap("id", id)
                        .with("prefix", "gb.")
                        .with("clancategory", "none")
                        .with("verification", "none")
                        .with("verificationrole", "none")
                        .with("verificationart", "none")
                        .with("verificationmessage", "none")
                        .with("privatechannel", "none")
                ).run(conn);
        out=cursor.next().toString();
        } catch (ClassCastException ignored) {}
        return out;
    }

    public String delete(String table, String where, String wherevalue) {
        Cursor cursor=r.table(table).filter(row -> row.g(where).eq(wherevalue)).delete().run(conn);
        return cursor.toString();
    }

}

package core;

import org.slf4j.LoggerFactory;
import stuff.SECRETS;

import java.lang.reflect.Executable;
import java.sql.*;

public class MySQL {
    private static Connection connection;
    private static org.slf4j.Logger Logger = LoggerFactory.getLogger(MySQL.class);
    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + SECRETS.host + ":" + SECRETS.port + "/" + SECRETS.database + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SECRETS.user, SECRETS.password);
            Logger.info("MySQL connection success");
        } catch (SQLException e) {
            Logger.error(e.toString());
            Logger.error("MySQL connection failed");
        }
    }

    public void disconnect() {
        try {
            connection.close();
            System.out.println("disconnected from MYSQL");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String get(String table, String where, String wherevalue, String spalte) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ? WHERE ?=?");
            ps.setString(1, table);
            ps.setString(2, where);
            ps.setString(3, wherevalue);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getString(spalte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String update(String table, String what, String whatvalue, String where, String wherevalue, String spalte) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE `?` SET `?`=? WHERE ?=?");
            ps.setString(1, table);
            ps.setString(2, what);
            ps.setString(3, whatvalue);
            ps.setString(4, where);
            ps.setString(5, wherevalue);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String insert() {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `user`(`ID`) VALUES ([value-1])");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

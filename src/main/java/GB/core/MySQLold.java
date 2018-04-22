package GB.core;

import stuff.SECRETS;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLold {


    private static Connection connection;
    private static org.slf4j.Logger Logger = LoggerFactory.getLogger(MySQLold.class);

    @Deprecated
    public static Connection getConnection() {
        return connection;
    }


    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/gottbot?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SECRETS.user, SECRETS.password);
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

    public String get(String table, String where, String wherevalue, String spalte) {
        String out="";
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `"+table+"` WHERE `"+where+"`=?");
            ps.setString(1, wherevalue);
            ResultSet rs = ps.executeQuery();
            // Only returning one result

            if (rs.next()) {
                out = rs.getString(spalte);
            } else out=null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    public String get1(String table, String wherevalue, String spalte) {
        String out="";
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `"+table+"` WHERE ?");
            ps.setString(1, wherevalue);
            ResultSet rs = ps.executeQuery();
            // Only returning one result

            if (rs.next()) {
                out = rs.getString(spalte);
            } else out=null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    public List<String> getall(String table, String where, String wherevalue, String spalte) {
        List<String> List = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `"+table+"` WHERE `"+where+"`=?");
            ps.setString(1,wherevalue);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List.add(rs.getString(spalte));
            }
            return List;
        } catch (Exception ex) {
            Logger.error(ex.toString());
        }
        return List;
    }

    public List<String> getallwithoutwhere(String table, String spalte) {
        List<String> List = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `"+table+"`");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List.add(rs.getString(spalte));
            }
            return List;
        } catch (Exception ex) {
            Logger.error(ex.toString());
        }

        return List;
    }

    public String getallstring(String table, String spalte) {
        String out="";
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `" + table + "`");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                out += rs.getString(spalte)+" ";
            }
            return out;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public String update(String table, String what, String whatvalue, String where, String wherevalue) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE `"+table+"` SET `"+what+"`=? WHERE `"+where+"`=?");
            ps.setString(1, whatvalue);
            ps.setString(2, wherevalue);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String insert(String table, String what, String whatvalue) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `"+table+"`(`"+what+"`) VALUES ('"+whatvalue+"')");
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(String table, String where, String wherevalue) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM `"+table+"` WHERE `"+where+"`=?");
            ps.setString(1, wherevalue);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exist(String table, String where) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `"+table+"` WHERE `"+where+"` = ?");
            ps.execute();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

}

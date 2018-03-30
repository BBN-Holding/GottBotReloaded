package core;



public class UserSQL {
    public static boolean isPremium(String user) {
        if (MySQL.get("server", "id", user, "id") == null) {
            return false;
        } else {
            return true;
        }
    }
}

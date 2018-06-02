package GB.Handler;

import java.util.Map;

public class Config {

    public String Token;
    public String OwnerID;
    public String Shards;
    public Map<String, String> DB;
    public Map<String, String> BotLists;
    public String ServerIP;
    public String ServerPort;
    public String ServerPassword;

    public String getToken() {
        return Token;
    }
    public String getOwnerID() {
        return OwnerID;
    }
    public String getShards() {
        return Shards;
    }

    public Map<String, String> getDB() {
        return DB;
    }

    public Map<String, String> getBotLists(){return BotLists;}
    public String getServerIP() {
        return ServerIP;
    }
    public String getServerPort() {
        return ServerPort;
    }
    public String getServerPassword() {
        return ServerPassword;
    }

}

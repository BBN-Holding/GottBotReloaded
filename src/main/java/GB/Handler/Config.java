package GB.Handler;

import java.util.Map;

public class Config {

    public String Token;
    public String Shards;
    public Map<String, String> DB;

    public String getToken() {
        return Token;
    }
    public String getShards() {
        return Shards;
    }

    public Map<String, String> getDB() {
        return DB;
    }

}

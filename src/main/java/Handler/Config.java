package Handler;

import java.util.Map;

public class Config {

    private String Token;
    private Map<String, String> BotListTokens;
    private Map<String, String> DB;

    public String getToken() {
        return Token;
    }

    public Map<String, String> getBotListTokens() {
        return BotListTokens;
    }

    public Map<String, String> getDB() {
        return DB;
    }
    
}

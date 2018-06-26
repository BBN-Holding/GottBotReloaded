package gb.Entitites.GottCoin;

import gb.GottBot;

import java.util.HashMap;

public class User {

    private static String id;
    private static String userid;
    private static String gottcoins;
    private static String gottcoinsmined;
    private static String[] miner;
    private static String type;

    public User(String DBInput) {
        String userstring = DBInput.replace("{", "").replace("}", "");
        String datas[] = userstring.split(", ");
        HashMap<String, String> saveddata = new HashMap<>();
        for (String data:datas) {
            if (data.contains("=")) {
                saveddata.put(data.split("=")[0], data.split("=")[1]);
            }
        }
        new User(saveddata.get("id"), saveddata.get("userid"), saveddata.get("gottcoins"),
                saveddata.get("gottcoinsmined"),
                GottBot.getDB().get("gottcoin", "userid", saveddata.get("userid"), "miner")
                        .replace("[", "").replace("]", "").split(", "),
                saveddata.get("type"));
    }

    public User(String id, String userid, String gottcoins, String gottcoinsmined, String[] miner, String type) {
        this.id = id;
        this.userid = userid;
        this.gottcoins = gottcoins;
        this.gottcoinsmined = gottcoinsmined;
        this.miner = miner;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGottcoins() {
        return gottcoins;
    }

    public void setGottcoins(String gottcoins) {
        this.gottcoins = gottcoins;
    }

    public String getGottcoinsmined() {
        return gottcoinsmined;
    }

    public void setGottcoinsmined(String gottcoinsmined) {
        this.gottcoinsmined = gottcoinsmined;
    }

    public String[] getMiner() {
        return miner;
    }

    public void setMiner(String[] miner) {
        this.miner = miner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

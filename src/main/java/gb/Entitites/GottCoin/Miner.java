package gb.Entitites.GottCoin;

import gb.GottBot;

public class Miner {

    String id;
    String chance;
    String author;
    String gottcoins;
    String gottcoinsmined;
    String pool;
    String type;

    public Miner(String id) {
        String miner = GottBot.getDB().getByIDwithoutField("gottcoin", id);
        String[] minerinfos = miner.split(", ");
        System.out.println(miner);

        new Miner(minerinfos[4], minerinfos[1], minerinfos[2], minerinfos[6], minerinfos[0], minerinfos[3], minerinfos[5]);
    }

    public Miner(String id, String chance, String author, String gottcoins, String gottcoinsmined, String pool, String type) {
        this.id = id;
        this.chance = chance;
        this.author = author;
        this.gottcoins = gottcoins;
        this.gottcoinsmined = gottcoinsmined;
        this.pool = pool;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getChance() {
        return chance;
    }

    public String getAuthor() {
        return author;
    }

    public String getGottcoins() {
        return gottcoins;
    }

    public String getGottcoinsmined() {
        return gottcoinsmined;
    }

    public String getPool() {
        return pool;
    }

    public String getType() {
        return type;
    }
}

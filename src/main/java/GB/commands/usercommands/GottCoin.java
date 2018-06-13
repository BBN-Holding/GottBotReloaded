package GB.commands.usercommands;

import java.util.ArrayList;
import java.util.Arrays;

public class GottCoin {
    public static Product getProduct() {
        return new Product();
    }
    public static Miner getMiner(String allinone) {
        return new Miner(allinone);
    }
    public static Miner getMiner(String id, String pool, String chance, String[] components, String costedmoney, String gcmined) {
        return new Miner(id, pool, chance, components, costedmoney, gcmined);
    }
}

class Miner {
    private String id;
    private String pool;
    private String chance;
    private String[] components;
    private String costedmoney;
    private String gcmined;
    private Miner miner;

    public Miner(String id, String pool, String chance, String[] components, String costedmoney, String gcmined) {
        this.id = id;
        this.pool = pool;
        this.chance = chance;
        this.components = components;
        this.costedmoney = costedmoney;
        this.gcmined = gcmined;
    }

    public Miner(String allinone) {
        String[] all = allinone.split("::");
        String[] components = all[3].split("//");
        new Miner(all[0], all[1], all[2], components, all[4], all[5]);
    }

    public String getId() {
        return id;
    }

    public String getPool() {
        return pool;
    }

    public String getChance() {
        return chance;
    }

    public String[] getComponents() {
        return components;
    }

    public String getCostedmoney() {
        return costedmoney;
    }

    public String getGcmined() {
        return gcmined;
    }
    // TODO: TOSTRING MACHEN
}

class Product {
    private static ArrayList<GottCoinProduct> gottCoinProducts = new ArrayList<>() {{
        add(new GottCoinProduct("1", "testprodukt", "100"));
    }};

    public static GottCoinProduct getByID(String id) {
        for (GottCoinProduct product:gottCoinProducts) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public static ArrayList<GottCoinProduct> getAll() {
        return gottCoinProducts;
    }
}

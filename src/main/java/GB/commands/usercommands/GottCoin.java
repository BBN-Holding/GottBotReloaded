package GB.commands.usercommands;

import java.util.ArrayList;
import java.util.Arrays;

public class GottCoin {
    public static Product getProduct() {
        return new Product();
    }
}

class Product {
    private static ArrayList<GottCoinProduct> gottCoinProducts = new ArrayList<>() {{
        add(new GottCoinProduct("1", "testprodukt", "100"));
    }};

    public GottCoinProduct getByID(String id) {
        for (GottCoinProduct product:gottCoinProducts) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public ArrayList<GottCoinProduct> getAll() {
        return gottCoinProducts;
    }
}

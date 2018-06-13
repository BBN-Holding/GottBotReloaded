package GB.commands.usercommands;

public class GottCoinProduct {

    private GottCoinProduct gottCoinProduct;
    private String id;
    private String name;
    private String price;

    public GottCoinProduct(String id, String name, String price) {
        this.id=id;
        this.name=name;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public long getPriceLong() {
        return Long.parseLong(price);
    }

    public int getPriceInt() {
        return Integer.parseInt(price);
    }

    public String getId() {
        return id;
    }

    public Long getIdLong() {
        return Long.parseLong(id);
    }

    public Integer getIdInt() {
        return Integer.parseInt(id);
    }
}

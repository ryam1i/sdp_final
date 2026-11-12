package catalog;

import common.Product;

public class TShirt implements Product {
    private String id;
    private String size;
    private String color;
    private double price;

    public TShirt(String id, String size, String color) {
        this.id = id;
        this.size = size;
        this.color = color;
        this.price = 8000;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return "T-Shirt";
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return "T-Shirt size " + size + ", " + color;
    }
}

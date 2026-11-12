package catalog;

import common.Product;

public class ClothingFactory extends ProductFactory {

    protected Product createProduct(String id) {
        return new TShirt(id, "M", "White");
    }
}

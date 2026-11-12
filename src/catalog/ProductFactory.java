package catalog;

import common.Product;

public abstract class ProductFactory {

    protected abstract Product createProduct(String id);

    public Product getProduct(String id) {
        Product product = createProduct(id);
        System.out.println("Created: " + product.getDescription() + " - " + product.getPrice());
        return product;
    }
}

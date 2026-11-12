package catalog;

import common.Product;

public class ElectronicsFactory extends ProductFactory {

    protected Product createProduct(String id) {
        return new Laptop.Builder(id).build();
    }

    public Product createLaptop(String id, int ram, int storage, String color) {
        return new Laptop.Builder(id)
                .setRam(ram)
                .setStorage(storage)
                .setColor(color)
                .addWarranty()
                .build();
    }
}

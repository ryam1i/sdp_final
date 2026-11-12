package catalog;

import common.Product;

public class CatalogDemo {
    public static void main(String[] args) {
        ElectronicsFactory electronics = new ElectronicsFactory();
        ProductFactory clothing = new ClothingFactory();

        System.out.println("--- Factory Method ---");
        electronics.getProduct("L1");
        clothing.getProduct("T1");

        System.out.println();
        System.out.println("--- Builder ---");
        Product gaming = new Laptop.Builder("L2")
                .setName("Gaming Laptop")
                .setRam(32)
                .setStorage(512)
                .setColor("Black")
                .build();
        System.out.println(gaming.getDescription() + " - " + gaming.getPrice());

        System.out.println();
        System.out.println("--- Factory Method + Builder ---");
        Product custom = electronics.createLaptop("L3", 16, 1024, "Silver");
        System.out.println(custom.getDescription() + " - " + custom.getPrice());
    }
}

package catalog;

import common.Product;

public class Laptop implements Product {
    private String id;
    private String name;
    private int ram;
    private int storage;
    private String color;
    private boolean warranty;
    private double price;

    private Laptop(Builder b) {
        this.id = b.id;
        this.name = b.name;
        this.ram = b.ram;
        this.storage = b.storage;
        this.color = b.color;
        this.warranty = b.warranty;
        this.price = 250000 + (ram - 8) * 8000 + (storage - 256) * 100;
        if (warranty) {
            this.price = this.price + 20000;
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        String text = name + " " + ram + "GB RAM, " + storage + "GB SSD, " + color;
        if (warranty) {
            text = text + ", warranty";
        }
        return text;
    }

    public static class Builder {
        private String id;
        private String name = "Laptop";
        private int ram = 8;
        private int storage = 256;
        private String color = "Gray";
        private boolean warranty = false;

        public Builder(String id) {
            this.id = id;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setRam(int ram) {
            this.ram = ram;
            return this;
        }

        public Builder setStorage(int storage) {
            this.storage = storage;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder addWarranty() {
            this.warranty = true;
            return this;
        }

        public Laptop build() {
            return new Laptop(this);
        }
    }
}

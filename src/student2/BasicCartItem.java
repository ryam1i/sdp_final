package student2;

import common.CartItem;
import common.InventoryObserver;
import common.Product;

public class BasicCartItem implements CartItem, InventoryObserver {
    private final Product product;
    private final int qty;
    private double unitPrice;
    private Integer knownStock;

    public BasicCartItem(Product product, int qty) {
        if (product == null || qty <= 0) {
            throw new IllegalArgumentException("Product and positive quantity required");
        }
        this.product = product;
        this.qty = qty;
        this.unitPrice = product.getPrice();
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public int getQty() {
        return qty;
    }

    @Override
    public double getPrice() {
        return unitPrice * qty;
    }

    @Override
    public String getDescription() {
        return product.getDescription();
    }

    public Integer getKnownStock() {
        return knownStock;
    }

    @Override
    public void onPriceChanged(String productId, double newPrice) {
        if (product.getId().equals(productId)) {
            if (!Double.isFinite(newPrice) || newPrice < 0) {
                throw new IllegalArgumentException("Invalid price");
            }
            unitPrice = newPrice;
        }
    }

    @Override
    public void onStockChanged(String productId, int newStock) {
        if (product.getId().equals(productId)) {
            if (newStock < 0) {
                throw new IllegalArgumentException("Invalid stock");
            }
            knownStock = newStock;
        }
    }
}

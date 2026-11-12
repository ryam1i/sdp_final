package student2.decorator;

import common.CartItem;
import common.InventoryObserver;
import common.Product;

public abstract class ProductDecorator implements CartItem, InventoryObserver {
    protected final CartItem item;

    public ProductDecorator(CartItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item required");
        }
        this.item = item;
    }

    @Override
    public Product getProduct() {
        return item.getProduct();
    }

    @Override
    public int getQty() {
        return item.getQty();
    }

    @Override
    public double getPrice() {
        return item.getPrice();
    }

    @Override
    public String getDescription() {
        return item.getDescription();
    }

    @Override
    public void onPriceChanged(String productId, double newPrice) {
        if (item instanceof InventoryObserver) {
            ((InventoryObserver) item).onPriceChanged(productId, newPrice);
        }
    }

    @Override
    public void onStockChanged(String productId, int newStock) {
        if (item instanceof InventoryObserver) {
            ((InventoryObserver) item).onStockChanged(productId, newStock);
        }
    }
}

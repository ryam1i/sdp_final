package student2;

import common.CartItem;
import common.DiscountStrategy;
import common.InventoryObserver;
import common.Product;
import student2.strategy.PercentageDiscount;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart implements InventoryObserver {
    private final List<CartItem> items = new ArrayList<>();
    private DiscountStrategy discountStrategy = new PercentageDiscount(0);

    public void addItem(CartItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item required");
        }
        items.add(item);
    }

    public void addProduct(Product product) {
        addItem(new BasicCartItem(product, 1));
    }

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        if (discountStrategy == null) {
            throw new IllegalArgumentException("Discount required");
        }
        this.discountStrategy = discountStrategy;
    }

    public double getSubtotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public double getTotal() {
        return discountStrategy.apply(getSubtotal());
    }

    @Override
    public void onPriceChanged(String productId, double newPrice) {
        for (CartItem item : items) {
            if (item instanceof InventoryObserver) {
                ((InventoryObserver) item).onPriceChanged(productId, newPrice);
            }
        }
    }

    @Override
    public void onStockChanged(String productId, int newStock) {
        for (CartItem item : items) {
            if (item instanceof InventoryObserver) {
                ((InventoryObserver) item).onStockChanged(productId, newStock);
            }
        }
    }

    public void printCart() {
        for (CartItem item : items) {
            System.out.printf(Locale.US, "%s x%d: %.2f KZT%n",
                    item.getDescription(), item.getQty(), item.getPrice());
        }
        System.out.printf(Locale.US, "Subtotal: %.2f KZT%n", getSubtotal());
        System.out.printf(Locale.US, "Total: %.2f KZT%n", getTotal());
    }
}

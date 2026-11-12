package student3.observer;

import common.InventoryObserver;
import common.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Observer pattern: the Subject.
 * Owns the actual prices and stock levels and notifies every subscribed
 * {@link InventoryObserver} (Cart, AdminDashboard, ...) when they change.
 */
public class Inventory {
    private final Map<String, Double> prices = new HashMap<>();
    private final Map<String, Integer> stock = new HashMap<>();
    private final List<InventoryObserver> observers = new CopyOnWriteArrayList<>();

    public void subscribe(InventoryObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer required");
        }
        observers.add(observer);
    }

    public void unsubscribe(InventoryObserver observer) {
        observers.remove(observer);
    }

    public void register(Product product, int initialStock) {
        if (product == null || initialStock < 0) {
            throw new IllegalArgumentException("Product and non-negative stock required");
        }
        prices.put(product.getId(), product.getPrice());
        stock.put(product.getId(), initialStock);
    }

    public void updatePrice(String productId, double newPrice) {
        requireRegistered(productId);
        if (!Double.isFinite(newPrice) || newPrice < 0) {
            throw new IllegalArgumentException("Invalid price");
        }
        prices.put(productId, newPrice);
        observers.forEach(o -> o.onPriceChanged(productId, newPrice));
    }

    public void updateStock(String productId, int newStock) {
        requireRegistered(productId);
        if (newStock < 0) {
            throw new IllegalArgumentException("Invalid stock");
        }
        stock.put(productId, newStock);
        observers.forEach(o -> o.onStockChanged(productId, newStock));
    }

    public void removeStock(String productId, int qty) {
        if (qty <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        int available = getStock(productId);
        if (available < qty) {
            throw new OutOfStockException(productId, qty, available);
        }
        updateStock(productId, available - qty);
    }

    public boolean isAvailable(String productId, int qty) {
        return stock.containsKey(productId) && stock.get(productId) >= qty;
    }

    public int getStock(String productId) {
        requireRegistered(productId);
        return stock.get(productId);
    }

    public double getPrice(String productId) {
        requireRegistered(productId);
        return prices.get(productId);
    }

    private void requireRegistered(String productId) {
        if (!stock.containsKey(productId)) {
            throw new IllegalArgumentException("Unknown product: " + productId);
        }
    }
}

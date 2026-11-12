package student3.observer;

import common.InventoryObserver;

import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class AdminDashboard implements InventoryObserver {
    private static final int LOW_STOCK_THRESHOLD = 3;

    private final Map<String, Double> lastPrices = new TreeMap<>();
    private final Map<String, Integer> lastStocks = new TreeMap<>();

    public void track(String productId, double price, int stock) {
        lastPrices.put(productId, price);
        lastStocks.put(productId, stock);
    }

    @Override
    public void onPriceChanged(String productId, double newPrice) {
        Double old = lastPrices.put(productId, newPrice);
        String before = old == null ? "n/a" : String.format(Locale.US, "%.2f", old);
        System.out.printf(Locale.US, "[Dashboard] Price of %s: %s -> %.2f KZT%n",
                productId, before, newPrice);
    }

    @Override
    public void onStockChanged(String productId, int newStock) {
        lastStocks.put(productId, newStock);
        System.out.printf("[Dashboard] Stock of %s: %d%n", productId, newStock);
        if (newStock <= LOW_STOCK_THRESHOLD) {
            System.out.printf("[Dashboard] WARNING: low stock for %s (%d left)%n",
                    productId, newStock);
        }
    }

    public void printSnapshot() {
        System.out.println("[Dashboard] Stock snapshot: " + lastStocks);
    }
}

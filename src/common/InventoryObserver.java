package common;

public interface InventoryObserver {
    void onPriceChanged(String productId, double newPrice);
    void onStockChanged(String productId, int newStock);
}

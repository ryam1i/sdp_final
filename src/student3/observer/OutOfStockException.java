package student3.observer;

public class OutOfStockException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OutOfStockException(String productId, int requested, int available) {
        super("Not enough stock for " + productId
                + ": requested " + requested + ", available " + available);
    }
}

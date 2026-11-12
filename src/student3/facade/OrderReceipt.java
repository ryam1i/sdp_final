package student3.facade;

import java.util.List;
import java.util.Locale;

public class OrderReceipt {
    private final List<String> lines;
    private final double subtotal;
    private final double total;
    private final String trackingNumber;

    public OrderReceipt(List<String> lines, double subtotal, double total, String trackingNumber) {
        this.lines = List.copyOf(lines);
        this.subtotal = subtotal;
        this.total = total;
        this.trackingNumber = trackingNumber;
    }

    public double getTotal() {
        return total;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("===== ORDER RECEIPT =====\n");
        lines.forEach(line -> sb.append(line).append('\n'));
        sb.append(String.format(Locale.US, "Subtotal: %.2f KZT%n", subtotal));
        sb.append(String.format(Locale.US, "Total:    %.2f KZT%n", total));
        sb.append("Tracking: ").append(trackingNumber).append('\n');
        sb.append("=========================");
        return sb.toString();
    }
}

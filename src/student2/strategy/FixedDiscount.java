package student2.strategy;

import common.DiscountStrategy;

public class FixedDiscount implements DiscountStrategy {
    private final double amount;

    public FixedDiscount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Invalid discount");
        }
        this.amount = amount;
    }

    @Override
    public double apply(double total) {
        return Math.max(0, total - amount);
    }
}

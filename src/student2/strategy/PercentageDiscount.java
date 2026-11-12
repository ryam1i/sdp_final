package student2.strategy;

import common.DiscountStrategy;

public class PercentageDiscount implements DiscountStrategy {
    private final double percent;

    public PercentageDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Invalid percent");
        }
        this.percent = percent;
    }

    @Override
    public double apply(double total) {
        return total * (1 - percent / 100);
    }
}

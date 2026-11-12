package student2.strategy;

import common.DiscountStrategy;

public class ThresholdDiscount implements DiscountStrategy {
    @Override
    public double apply(double total) {
        if (total >= 100000) {
            return total * 0.85;
        }
        return total;
    }
}
